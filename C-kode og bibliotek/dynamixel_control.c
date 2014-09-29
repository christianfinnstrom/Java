/*
*
*
*	This program is based entirely on Robotis' code for controlling
*	the Dynamixel AX-12+ electrical servo actuator. 
*
*	The subroutines in this program allows control and communication with 
*	the actuator by reading and setting its 50 parameters through instruction
*	and status packets.
*
*	Instruction packet: Command data that Main controller sends to the Dynamixel
*	actuator. Structure of the packet: 
*	[0XFF 0XFF ID LENGTH INSTRUCTION PARAMETER1 ... PARAMETER N CHECK-SUM]. E.g., 
*	ID is at index 2 (zero based indexing) of the structure packet.
*
*	Status packet: The Dynamixel actuator reports its status through Status
*	packets. Structure of the packet:
*	[0XFF 0XFF ID LENGTH ERROR PARAMETER1 ... PARAMETER N CHECK-SUM]
*
*	0XFF - notifies the beginning of the packet
*	ID - Dynamixel ID (which actuator to send or receive data to/from)
*	LENGTH - number of parameters (N) + 2
*	INSTRUCTION - (instruction packet only) gives an instruction to the the Dynamixel 
*	(parameter address on the Dynamixel actuator). See Dynamixel control table.
*	ERROR - (status packet only) displays the error status occured during the operation
*	(parameter address on the Dynamixel actuator). See Dynamixel error table.
*	PARAMETER - used when instruction needs additional parameters
*	CHECK-SUM - used to check if the packet is damaged during communication
*
*	The program utilizes "dxl_hal.c" for serial communication.
*
*	Comments and subroutine explanations by:
*	Christian Finnstrom, Sigbjorn Aukland, NTNU, Trondheim 23.09.2014.

*/


#include "dxl_hal.h"
#include "dynamixel_control.h"
#include <stdio.h>


/*	The following definitions are used to index communication packets.
*	The number in parenthesis represents the index. */
#define ID					(2)  
#define LENGTH				(3)
#define INSTRUCTION			(4)
#define ERRBIT				(4)
#define PARAMETER			(5)
#define DEFAULT_BAUDNUMBER	(1)


//	Declare communication packets:
unsigned char gbInstructionPacket[MAXNUM_TXPARAM+10] = {0};
unsigned char gbStatusPacket[MAXNUM_RXPARAM+10] = {0};
unsigned char gbRxPacketLength = 0;
unsigned char gbRxGetLength = 0;
int gbCommStatus = COMM_RXSUCCESS;
int giBusUsing = 0;







/*
*	DEVICE CONTROL METHODS - subroutines to control the 
*	communication devices.
*/

/*	Attempts to initialize the communication devices. 
*	Returns status. */
int dxl_initialize( int devIndex, int baudnum )
{
	float baudrate;	
	baudrate = 2000000.0f / (float)(baudnum + 1);
	
	if( dxl_hal_open(devIndex, baudrate) == 0 )
		return 0;

	gbCommStatus = COMM_RXSUCCESS;
	giBusUsing = 0;
	return 1;
}


/*	Terminates the communication devices.*/
void dxl_terminate()
{
	dxl_hal_close();
}







/*
*	PACKET COMMUNICATION METHODS - subroutines used to transmit
*	and receive packets.
*/

/*	Transmits an instruction packet to the Dynamixel */
void dxl_tx_packet()
{
	unsigned char i;
	unsigned char TxNumByte, RealTxNumByte;
	unsigned char checksum = 0;

	if( giBusUsing == 1 )
		return;
	
	giBusUsing = 1;

	// check that the instruction packet doesn't exceed maximum size
	if( gbInstructionPacket[LENGTH] > (MAXNUM_TXPARAM+2) )
	{
		gbCommStatus = COMM_TXERROR;
		giBusUsing = 0;
		return;
	}
	
	// check if instruction is set to a valid instruction
	if( gbInstructionPacket[INSTRUCTION] != INST_PING
		&& gbInstructionPacket[INSTRUCTION] != INST_READ
		&& gbInstructionPacket[INSTRUCTION] != INST_WRITE
		&& gbInstructionPacket[INSTRUCTION] != INST_REG_WRITE
		&& gbInstructionPacket[INSTRUCTION] != INST_ACTION
		&& gbInstructionPacket[INSTRUCTION] != INST_RESET
		&& gbInstructionPacket[INSTRUCTION] != INST_SYNC_WRITE )
	{
		gbCommStatus = COMM_TXERROR;
		giBusUsing = 0;
		return;
	}
	// initialize the beginning of the instruction packet:
	gbInstructionPacket[0] = 0xff;
	gbInstructionPacket[1] = 0xff;

	// calculate check-sum:
	for( i=0; i<(gbInstructionPacket[LENGTH]+1); i++ )
		checksum += gbInstructionPacket[i+2];

	// set the ckeck-sum value in the instruction packet to bitwise NOT checksum value
	gbInstructionPacket[gbInstructionPacket[LENGTH]+3] = ~checksum;  
	
	// clears the communication buffer if the communication status is not valid
	if( gbCommStatus == COMM_RXTIMEOUT || gbCommStatus == COMM_RXCORRUPT )
		dxl_hal_clear();

	TxNumByte = gbInstructionPacket[LENGTH] + 4; // number of bytes to transfer to Dynamixel
	RealTxNumByte = dxl_hal_tx( (unsigned char*)gbInstructionPacket, TxNumByte ); // number of bytes that were actually transmitted

	if( TxNumByte != RealTxNumByte )
	{
		gbCommStatus = COMM_TXFAIL;
		giBusUsing = 0;
		return;
	}

	// set desired timeout after transmission
	if( gbInstructionPacket[INSTRUCTION] == INST_READ ) // if READ is the instruction
		dxl_hal_set_timeout( gbInstructionPacket[PARAMETER+1] + 6 );
	else
		dxl_hal_set_timeout( 6 );

	gbCommStatus = COMM_TXSUCCESS;
}


/*	Status packet is extracted from Driver buffer */
void dxl_rx_packet()
{
	// variable declarations:
	unsigned char i, j, nRead;
	unsigned char checksum = 0;

	if( giBusUsing == 0 )
		return;

	// checks if the ID in the instruction packet is BROADCAST_ID (universal address).
	// When using BROADCAST_ID, no status packet is returned
	if( gbInstructionPacket[ID] == BROADCAST_ID )
	{
		gbCommStatus = COMM_RXSUCCESS;
		giBusUsing = 0;
		return;
	}
	
	if( gbCommStatus == COMM_TXSUCCESS )
	{
		gbRxGetLength = 0;
		gbRxPacketLength = 6;
	}
	
	// dxl_hal_rx returns the number of data received. Input is pointer to the status packet, and number of data array
	nRead = dxl_hal_rx( (unsigned char*)&gbStatusPacket[gbRxGetLength], gbRxPacketLength - gbRxGetLength );
	gbRxGetLength += nRead;

	// if the number of data received is less than the length of the status packet check for possible errors:
	if( gbRxGetLength < gbRxPacketLength ) 
	{
		// check if timeout
		if( dxl_hal_timeout() == 1 ) 
		{
			if(gbRxGetLength == 0) // if the number of data received is zero, status is set to timeout:
				gbCommStatus = COMM_RXTIMEOUT;
			else // if some data has been received, the communication is corrupt:
				gbCommStatus = COMM_RXCORRUPT; 
			giBusUsing = 0;
			return;
		}
	}
	
	// Find packet header, "i" is set to this index: 
	for( i=0; i<(gbRxGetLength-1); i++ )
	{
		if( gbStatusPacket[i] == 0xff && gbStatusPacket[i+1] == 0xff )
		{
			break;
		}
		else if( i == gbRxGetLength-2 && gbStatusPacket[gbRxGetLength-1] == 0xff )
		{
			break;
		}
	}	

	// if not zero based indexing, fix
	if( i > 0 )
	{
		for( j=0; j<(gbRxGetLength-i); j++ )
			gbStatusPacket[j] = gbStatusPacket[j + i];
			
		gbRxGetLength -= i;		// update the number of data received
	}

	// if the number of data received is still less than the length of the packet
	// --> communication status: waiting for data
	if( gbRxGetLength < gbRxPacketLength )
	{
		gbCommStatus = COMM_RXWAITING;
		return;
	}

	// Check id pairing: 
	// Is the ID of instruction packet receiver NOT the same as the ID of status packet sender?
	if( gbInstructionPacket[ID] != gbStatusPacket[ID])
	{
		gbCommStatus = COMM_RXCORRUPT; // communication is corrupt
		giBusUsing = 0;
		return;
	}
	
	gbRxPacketLength = gbStatusPacket[LENGTH] + 4; // update status packet length

	// if the number of received data is still less than the status packet length:
	if( gbRxGetLength < gbRxPacketLength )
	{
		// update nRead (number of received data) to check if new data has been transmitted:
		nRead = dxl_hal_rx( (unsigned char*)&gbStatusPacket[gbRxGetLength], gbRxPacketLength - gbRxGetLength );
		gbRxGetLength += nRead;

		// check again: if the (updated) number of received data is less than the status packet length
		if( gbRxGetLength < gbRxPacketLength ) 
		{
			gbCommStatus = COMM_RXWAITING; // communication status: waiting
			return;
		}
	}

	// Calculate check-sum:
	for( i=0; i<(gbStatusPacket[LENGTH]+1); i++ )
		checksum += gbStatusPacket[i+2];
	checksum = ~checksum;

	// check if check-sum is correct:
	if( gbStatusPacket[gbStatusPacket[LENGTH]+3] != checksum )
	{
		gbCommStatus = COMM_RXCORRUPT;
		giBusUsing = 0;
		return;
	}
	
	gbCommStatus = COMM_RXSUCCESS; // communication status: success
	giBusUsing = 0;
}



/*	runs dxl_tx_packet (transmits an instruction packet to the Dynamixel)
*	if success: run dxl_rx_packet (status packet is extracted from Driver buffer) */
void dxl_txrx_packet()
{
	dxl_tx_packet();

	if( gbCommStatus != COMM_TXSUCCESS )
		return;	
	
	do{
		dxl_rx_packet();		
	}while( gbCommStatus == COMM_RXWAITING );	
}

/*	Returns the current communication status */
int dxl_get_result()
{
	return gbCommStatus;
}







/*
*	SET/GET PACKET METHODS - subroutines to make and 
*	see the packets.
*/

/*	Sets the instruction packet ID */
void dxl_set_txpacket_id( int id )
{
	gbInstructionPacket[ID] = (unsigned char)id;
}


/*	Sets the instruction packet instruction  */
void dxl_set_txpacket_instruction( int instruction )
{
	gbInstructionPacket[INSTRUCTION] = (unsigned char)instruction;
}

/*	Sets the instruction packet parameter value to the 
*	desired parameter index	*/
void dxl_set_txpacket_parameter( int index, int value )
{
	gbInstructionPacket[PARAMETER+index] = (unsigned char)value;
}

/*	Sets the instruction packet length	*/
void dxl_set_txpacket_length( int length )
{
	gbInstructionPacket[LENGTH] = (unsigned char)length;
}

/*	Checks whether the status packet error equals the inputted error.	
*	Returns 1 if true, 0 if false.	*/
int dxl_get_rxpacket_error( int errbit )
{
	if( gbStatusPacket[ERRBIT] & (unsigned char)errbit )
		return 1;

	return 0;
}

/*	Returns the length of the status packet	*/ 
int dxl_get_rxpacket_length()
{
	return (int)gbStatusPacket[LENGTH];
}

/*	Returns the parameter value of the status packet */
int dxl_get_rxpacket_parameter( int index )
{
	return (int)gbStatusPacket[PARAMETER+index];
}








/*
*	HIGH COMMUNICATION METHODS - subroutines used to functionalize
*	frequently used communication packets for user convenience.
*/


/*	Ping a Dynamixel Actuator to evaluate its existence	*/	
void dxl_ping( int id )
{
	while(giBusUsing);

	gbInstructionPacket[ID] = (unsigned char)id;
	gbInstructionPacket[INSTRUCTION] = INST_PING;
	gbInstructionPacket[LENGTH] = 2;
	
	dxl_txrx_packet();
}

/*	Read one byte from the Dynamixel actuator	*/
int dxl_read_byte( int id, int address )
{
	while(giBusUsing);

	gbInstructionPacket[ID] = (unsigned char)id;
	gbInstructionPacket[INSTRUCTION] = INST_READ;
	gbInstructionPacket[PARAMETER] = (unsigned char)address;
	gbInstructionPacket[PARAMETER+1] = 1;
	gbInstructionPacket[LENGTH] = 4;
	
	dxl_txrx_packet();

	return (int)gbStatusPacket[PARAMETER];
}

/*	Write one byte to the Dynamixel actuator	*/
void dxl_write_byte( int id, int address, int value )
{
	while(giBusUsing);

	gbInstructionPacket[ID] = (unsigned char)id;
	gbInstructionPacket[INSTRUCTION] = INST_WRITE;
	gbInstructionPacket[PARAMETER] = (unsigned char)address;
	gbInstructionPacket[PARAMETER+1] = (unsigned char)value;
	gbInstructionPacket[LENGTH] = 4;
	
	dxl_txrx_packet();
}

/*	Two bytes can be read from the Dynamixel actuator	*/
int dxl_read_word( int id, int address )
{
	while(giBusUsing);

	gbInstructionPacket[ID] = (unsigned char)id;
	gbInstructionPacket[INSTRUCTION] = INST_READ;
	gbInstructionPacket[PARAMETER] = (unsigned char)address;
	gbInstructionPacket[PARAMETER+1] = 2;
	gbInstructionPacket[LENGTH] = 4;
	
	dxl_txrx_packet();

	return dxl_makeword((int)gbStatusPacket[PARAMETER], (int)gbStatusPacket[PARAMETER+1]);
}

/*	Two bytes can be written to the Dynamixel actuator	*/
void dxl_write_word( int id, int address, int value )
{
	while(giBusUsing);

	gbInstructionPacket[ID] = (unsigned char)id;
	gbInstructionPacket[INSTRUCTION] = INST_WRITE;
	gbInstructionPacket[PARAMETER] = (unsigned char)address;
	gbInstructionPacket[PARAMETER+1] = (unsigned char)dxl_get_lowbyte(value);
	gbInstructionPacket[PARAMETER+2] = (unsigned char)dxl_get_highbyte(value);
	gbInstructionPacket[LENGTH] = 5;
	
	dxl_txrx_packet();
}







/*
*	UTILITY METHODS - other useful subroutines
*/

/*	Combines a lowbyte and highbyte and returns a word	*/
int dxl_makeword( int lowbyte, int highbyte )
{
	unsigned short word;

	word = highbyte;
	word = word << 8;
	word = word + lowbyte;
	return (int)word;
}

/*	Returns the lowbyte of a word	*/	
int dxl_get_lowbyte( int word )
{
	unsigned short temp;

	temp = word & 0xff;
	return (int)temp;
}

/*	Returns the highbyte of a word */
int dxl_get_highbyte( int word )
{
	unsigned short temp;

	temp = word & 0xff00;
	temp = temp >> 8;
	return (int)temp;
}
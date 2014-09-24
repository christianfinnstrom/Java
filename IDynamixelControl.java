/*
 * 
 * 
 * 
 * Remember to add jna.jar to the projects' external archives
 * 32 bit is required
 * 
 */

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;


public interface IDynamixelControl extends Library{
	
	IDynamixelControl INSTANCE = (IDynamixelControl) Native.loadLibrary(
			(Platform.isWindows() ? "DynamixelControl" : "DynamixelControl"), IDynamixelControl.class);
	
	
/*
*	DEVICE CONTROL METHODS - subroutines to control the 
*	communication devices.
*/
	
	/**
	 * Attempts to initialize the communication devices
	 * @param devIndex Number of connected communication devices
	 * @param baudnum 
	 * @return 1 if success, 0 if failure
	 */
	int dxl_initialize(int devIndex, int baudnum);
	
	/**
	 * Terminates the communication devices
	 */
	void dxl_terminate();
	

	
	
/*
*	PACKET COMMUNICATION METHODS - subroutines used to transmit
*	and receive packets.
*/
	
	/**
	 *	Transmits instruction packet to Dynamixel 
	 */
	void dxl_tx_packet();
	
	/**
	 *	Extracts status packet from the driver buffer
	 */
	void dxl_rx_packet();
	
	
	/**
	 * Runs dxl_tx_packet (transmits an instruction packet to the Dynamixel).
	 * If success, run dxl_rx_packet (status packet is extracted from driver buffer).
	 */
	void dxl_txrx_packet();
	
	
	/**
	 * Get the current communication status
	 * @return communication status identifier
	 */
	int dxl_get_result();


    
    
    
/*
*	SET/GET PACKET METHODS - subroutines to make and 
*	see the packets.
*/
	/**
	 * Sets the instruction packet ID
	 * @param id Dynamixel ID to transmit instruction packet to
	 */
    public void dxl_set_txpacket_id(int id);
    
    /**
     * Sets the instruction of the instruction packet
     * @param instruction See Control table
     */
    public void dxl_set_txpacket_instruction(int instruction);

    /**
     * Sets the parameter of the instruction packet
     * @param index 
     * @param value
     */
    public void dxl_set_txpacket_parameter(int index, int value);

    public void dxl_set_txpacket_length(int length);

    public int dxl_get_rxpacket_error(int errbit);

    public int dxl_get_rxpacket_length();

    public int dxl_get_rxpacket_parameter(int index);
    
    
    
/*
*	HIGH COMMUNICATION METHODS - subroutines used to functionalize
*	frequently used communication packets for user convenience.
*/
    
   
    /**
     * @method dxl_ping Ping a Dynamixel to evaluate its existence
     * @param id Dynamixel actuator ID
     */
    public void dxl_ping(int id);
    
    public int dxl_read_byte(int id, int address);

    public void dxl_write_byte(int id, int address, int value);

    public int dxl_read_word(int id, int address);

    public void dxl_write_word(int id, int address, int value);
    
    
/*
*	UTILITY METHODS - other useful subroutines
*/
    public int dxl_makeword(int lowbyte, int highbyte);	
    
    public int dxl_get_lowbyte(int word);

    public int dxl_get_highbyte(int word);

}

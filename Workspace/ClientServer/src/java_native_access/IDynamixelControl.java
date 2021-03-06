package java_native_access;
/*
 * 
 * 
 * 
 * Remember to add jna.jar to the projects' external archives
 * 32 bit is required
 * 
 */

import utility.UtilityClass;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

	
public interface IDynamixelControl extends Library{
	
	
//
//	public IDynamixelControl INSTANCE = (IDynamixelControl) Native.loadLibrary(
//			(Platform.isWindows() ? "DynamixelControl64" : "DynamixelControl"), IDynamixelControl.class);
	
	public IDynamixelControl INSTANCE = (IDynamixelControl) Native.loadLibrary(
			(UtilityClass.getDLLFileName()), IDynamixelControl.class);


	

	
	
//	
/*
*	DEVICE CONTROL METHODS - subroutines to control the 
*	communication devices.
*/
	
	/**
	 * Attempts to initialize the communication devices
	 * @param devIndex Number of connected communication devices or default PORTNUM
	 * @param baudnum 
	 * @return 1 if success, 0 if failure
	 */
	public int dxl_initialize(int devIndex, int baudnum);
	
	/**
	 * Terminates the communication devices
	 */
	public void dxl_terminate();
	

	
	
/*
*	PACKET COMMUNICATION METHODS - subroutines used to transmit
*	and receive packets.
*/
	
	/**
	 *	Transmits instruction packet to Dynamixel 
	 */
	public void dxl_tx_packet();
	
	/**
	 *	Extracts status packet from the driver buffer
	 */
	public void dxl_rx_packet();
	
	
	/**
	 * Runs dxl_tx_packet (transmits an instruction packet to the Dynamixel).
	 * If success, run dxl_rx_packet (status packet is extracted from driver buffer).
	 */
	public void dxl_txrx_packet();
	
	
	/**
	 * Get the current communication status
	 * @return communication status identifier
	 */
	public int dxl_get_result();


    
    
    
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
     * @param index The parameter number to be set
     * @param value The instruction value. See control table
     */
    public void dxl_set_txpacket_parameter(int index, int value);

    /**
     * Sets the instruction packet length
     * @param length Length of instruction packet
     */
    public void dxl_set_txpacket_length(int length);

    /**
     * Checks whether the status packet error equals the inputted error
     * @param Bit Flag to check whether errors occur or not
     * @return 1 if true, 0 if false
     */
    public int dxl_get_rxpacket_error(int errbit);

    /**
     * Get length of status packet.
     * @return Length of status packet
     */
    public int dxl_get_rxpacket_length();

    /**
     * Gets the parameter value of the status packet
     * @param is the parameter number to be set
     * @return The parameter value at the "index"th element of status packet
     */
    public int dxl_get_rxpacket_parameter(int index);
    
    
    
/*
*	HIGH COMMUNICATION METHODS - subroutines used to functionalize
*	frequently used communication packets for user convenience.
*/
    
   
    /**
     * Ping a Dynamixel to evaluate its existence
     * @param id Dynamixel actuator ID
     */
    public void dxl_ping(int id);
    
    /**
     * Read one byte from the Dynamixel actuator
     * @param id Dynamixel ID
     * @return The read data value
     */
    public int dxl_read_byte(int id, int address);
    
    /**
     * Write one byte to the Dynamixel actuator
     * @param id Dynamixel ID
     * @param address Location of data to operate on (see Control Table)
     * @param value Value to write on the Dynamixel at the desired address
     */
    public void dxl_write_byte(int id, int address, int value);

    /**
     * Two bytes can be read from the Dynamixel actuator
     * @param id Dynamixel ID
     * @param address Location of data to operate on (see Control Table)
     * @return The read data value
     */
    public int dxl_read_word(int id, int address);

    /**
     * Two bytes can be written to the Dynamixel actuator
     * @param id Dynamixel ID
     * @param address Location of data to operate on (see Control Table)
     * @param value The value to write on the Dynamixel
     */
    public void dxl_write_word(int id, int address, int value);
    
    
/*
*	UTILITY METHODS - other useful subroutines
*/
    /**
     * Combines a lowbyte and highbyte and returns a word
     * @param lowbyte Lower byte to be made of WORD-type
     * @param highbyte Higher byte to be made of WORD-type
     */
    public int dxl_makeword(int lowbyte, int highbyte);	
    
    /**
     * Returns the lowbyte of a word
     * @param word Word-type data to extract lower byte from
     * @return The lower byte extracted from word
     */
    public int dxl_get_lowbyte(int word);
    
    /**
     * Returns the highbyte of a word 
     * @param word Word-type data to extract higher byte from
     * @return The higher byte extracted from word
     */
    public int dxl_get_highbyte(int word);


	
	    
}
	
	




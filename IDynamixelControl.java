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
	
	int dxl_initialize(int devIndex, int baudnum);
	
	void dxl_terminate();
	

/*
*	PACKET COMMUNICATION METHODS - subroutines used to transmit
*	and receive packets.
*/
	
	void dxl_tx_packet();
	
	void dxl_rx_packet();
	
	void dxl_txrx_packet();
	
	int dxl_get_result();


    
    
    
/*
*	SET/GET PACKET METHODS - subroutines to make and 
*	see the packets.
*/
    public void dxl_set_txpacket_id(int id);
    
    public void dxl_set_txpacket_instruction(int instruction);

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
     * @param id 
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

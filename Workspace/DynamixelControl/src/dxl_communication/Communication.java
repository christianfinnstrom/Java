package dxl_communication;

import java_native_access.IDynamixelControl;


/*
 * 
 * The methods in this class are used for communication between the computer and the Dynamixel actuator(s).
 * The methods are called from the 
 * 
 * 
 * 
 * 
 */
public class Communication {
	
	private int DEFAULT_PORTNUM = 3; //com3
	private int DEFAULT_BAUDNUM = 1; //mbps	
	
	public int initialize()	{
		return IDynamixelControl.INSTANCE.dxl_initialize(DEFAULT_PORTNUM, DEFAULT_BAUDNUM);
	}
	
	public void terminate() {
		IDynamixelControl.INSTANCE.dxl_terminate();
	}
	
	public void tx_packet() {
		IDynamixelControl.INSTANCE.dxl_tx_packet();
	}
	
	public void rx_packet() {
		IDynamixelControl.INSTANCE.dxl_rx_packet();
	}
	
	/**
	 * This should automatically choose between byte and word
	 * 
	 * @return
	 */
	public static int readFromDxl(int id, int address, int value){
		
		return 0;  // for now
	}
	
	/**
	 * This should automatically choose between byte and word
	 */
	public static void writeToDxl(int id, int address,int value){
		
	}
	
	public static void writeByteToDxl(int id, int address, int value){
		IDynamixelControl.INSTANCE.dxl_write_byte(id, address, value);
	}
	
	public static void writeWordToDxl(int id, int address, int value){
		IDynamixelControl.INSTANCE.dxl_write_word(id, address, value);
	}
	
	public static int readByteFromDxl(int id, int address){
		return IDynamixelControl.INSTANCE.dxl_read_byte(id, address);
	}
	
	public static int readWordFromDxl(int id, int address){
		return IDynamixelControl.INSTANCE.dxl_read_word(id, address);
	}
	
	

}
package dxl_communication;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java_native_access.IDynamixelControl;


/*
 * 
 * The methods in this class are used for communication between the computer and the Dynamixel actuator(s).
 * The methods are called from the 
 * 
 * 
 * TODO create a dictionary that contains the control table!
 * 
 * 
 */
public class Communication {
	
	private static int DEFAULT_PORTNUM = 3; //com3
	private static int DEFAULT_BAUDNUM = 1; //mbps	
	
	
	// Addresses that can not be read as words:
	public final static int[] singleByteAddresses = {2, 3, 4, 5, 11, 12, 13, 16, 17, 18, 24, 25, 26, 27, 28, 29, 42, 43, 44, 46, 47};
	
	/**
	 * Checks whether this address contains a value that consists of a single byte
	 * @param actuator address to check
	 */
	public static boolean isSingleByteAddress(int address){
		
		boolean isSingle = false;
		for(int i = 0; i < singleByteAddresses.length; ++i){
			if(singleByteAddresses[i] == address)
			{
				isSingle = true;
				break;
			}
		}

		return isSingle;
	}
	
	// Dictionary containing the control table. Key is parameter name, value is address. Rename?
	static Map<String, Integer> dictionary = new HashMap<String, Integer>();
	static{ // dictionary entries: 
		
		
		dictionary.put("model number(l)", 0);
		dictionary.put("model number(h)", 1);
		dictionary.put("version of firmware", 2);
		dictionary.put("id", 3);
		dictionary.put("baud rate", 4);
		dictionary.put("return delay time", 5);
		dictionary.put("cw angle limit(l)", 6);
		dictionary.put("cw angle limit(h)", 7);
		dictionary.put("ccw angle limit(l)", 8);
		dictionary.put("ccw angle limit(h)", 9);
		dictionary.put("the highest limit temperature", 11);
		dictionary.put("the lowest limit voltage", 12);
		dictionary.put("the highest limit voltage", 13);
		dictionary.put("max torque(l)", 14);
		dictionary.put("max torque(h)", 15);
		dictionary.put("status return level", 16);
		dictionary.put("alarm led", 17);
		dictionary.put("alarm shutdown", 18);
		dictionary.put("torque enable", 24);
		dictionary.put("led", 25);
		dictionary.put("cw compliance margin", 26);
		dictionary.put("ccw compliance margin", 27);
		dictionary.put("cw compliance slope", 28);
		dictionary.put("ccw compliance slope", 29);
		dictionary.put("goal position(l)", 30);
		dictionary.put("goal position(h)", 31);
		dictionary.put("moving speed(l)", 32);
		dictionary.put("moving speed(h)", 33);
		dictionary.put("torque limit(l)", 34);
		dictionary.put("torque limit(h)", 35);
		dictionary.put("present position(l)", 36);
		dictionary.put("present position(h)", 37);
		dictionary.put("present speed(l)", 38);
		dictionary.put("present speed(h)", 39);
		dictionary.put("present load(l)", 40);
		dictionary.put("present load(h)", 41);
		dictionary.put("present voltage", 42);
		dictionary.put("present temperature", 43);
		dictionary.put("registered", 44);
		dictionary.put("moving)", 46);
		dictionary.put("lock", 47);
		dictionary.put("punch(l)", 48);
		dictionary.put("punch(h)", 49);
	}

	public static int getModelNumber(int id){
		return readFromDxl(id, dictionary.get("model number(l)"));
	}
	
	public static int getVersionOfFirmware(int id){
		return readFromDxl(id, dictionary.get("version of firmware"));
	}
	
	public static int getID(int id){
		return readFromDxl(id, dictionary.get("id"));
	}
	
	public static void setID(int id, int newID){
		writeToDxl(id, dictionary.get("id"), newID);
	}
	
	public static int getBaudrate(int id){
		return readFromDxl(id, dictionary.get("baud rate"));
	}
	
	public static void setBaudrate(int id, int newBaud){
		writeToDxl(id, dictionary.get("baud rate"), newBaud);
	}
	
	public static int getReturnDelayTime(int id){
		return readFromDxl(id, dictionary.get("return delay time"));
	}
	
	public static void setReturnDelayTime(int id, int newReturnDelayTime){
		writeToDxl(id, dictionary.get("return delay time"), newReturnDelayTime);
	}
	
	public static int getCWAngleLimit(int id){
		return readFromDxl(id, dictionary.get("cw angle limit(l)"));
	}
	
	public static void setCWAngleLimit(int id, int newCWAngleLimit){
		writeToDxl(id, dictionary.get("cw angle limit(l)"), newCWAngleLimit);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static int initialize()	{
		return IDynamixelControl.INSTANCE.dxl_initialize(DEFAULT_PORTNUM, DEFAULT_BAUDNUM);
	}
	/**
	 * 
	 */
	public static void terminate() {
		IDynamixelControl.INSTANCE.dxl_terminate();
	}
	
	/* NOT SURE IF NEEDED HERE
	public static void tx_packet() {
		IDynamixelControl.INSTANCE.dxl_tx_packet();
	}
	
	public static void rx_packet() {
		IDynamixelControl.INSTANCE.dxl_rx_packet();
	}
	*/
	

	
	
	/**
	 * This should automatically choose between byte and word
	 * 
	 * @return
	 */
	public static int readFromDxl(int id, int address){
		if(isSingleByteAddress(address)) return readByteFromDxl(id, address);
		else return readWordFromDxl(id, address);
	}
	
	/**
	 * This should automatically choose between byte and word
	 */
	public static void writeToDxl(int id, int address,int value){
		if(isSingleByteAddress(address)) writeByteToDxl(id, address, value);
		else writeWordToDxl(id, address, value);
		
	}
	
	private static void writeByteToDxl(int id, int address, int value){
		IDynamixelControl.INSTANCE.dxl_write_byte(id, address, value);
	}
	
	public static void writeWordToDxl(int id, int address, int value){
		IDynamixelControl.INSTANCE.dxl_write_word(id, address, value);
	}
	
	private static int readByteFromDxl(int id, int address){
		return IDynamixelControl.INSTANCE.dxl_read_byte(id, address);
	}
	
	private static int readWordFromDxl(int id, int address){
		return IDynamixelControl.INSTANCE.dxl_read_word(id, address);
	}
	
	

}

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
	static Map<String, Integer> controlTableDictionary = new HashMap<String, Integer>();
	static{ // dictionary entries: 
		
		
		controlTableDictionary.put("model number(l)", 0);
		controlTableDictionary.put("model number(h)", 1);
		controlTableDictionary.put("version of firmware", 2);
		controlTableDictionary.put("id", 3);
		controlTableDictionary.put("baud rate", 4);
		controlTableDictionary.put("return delay time", 5);
		controlTableDictionary.put("cw angle limit(l)", 6);
		controlTableDictionary.put("cw angle limit(h)", 7);
		controlTableDictionary.put("ccw angle limit(l)", 8);
		controlTableDictionary.put("ccw angle limit(h)", 9);
		controlTableDictionary.put("the highest limit temperature", 11);
		controlTableDictionary.put("the lowest limit voltage", 12);
		controlTableDictionary.put("the highest limit voltage", 13);
		controlTableDictionary.put("max torque(l)", 14);
		controlTableDictionary.put("max torque(h)", 15);
		controlTableDictionary.put("status return level", 16);
		controlTableDictionary.put("alarm led", 17);
		controlTableDictionary.put("alarm shutdown", 18);
		controlTableDictionary.put("torque enable", 24);
		controlTableDictionary.put("led", 25);
		controlTableDictionary.put("cw compliance margin", 26);
		controlTableDictionary.put("ccw compliance margin", 27);
		controlTableDictionary.put("cw compliance slope", 28);
		controlTableDictionary.put("ccw compliance slope", 29);
		controlTableDictionary.put("goal position(l)", 30);
		controlTableDictionary.put("goal position(h)", 31);
		controlTableDictionary.put("moving speed(l)", 32);
		controlTableDictionary.put("moving speed(h)", 33);
		controlTableDictionary.put("torque limit(l)", 34);
		controlTableDictionary.put("torque limit(h)", 35);
		controlTableDictionary.put("present position(l)", 36);
		controlTableDictionary.put("present position(h)", 37);
		controlTableDictionary.put("present speed(l)", 38);
		controlTableDictionary.put("present speed(h)", 39);
		controlTableDictionary.put("present load(l)", 40);
		controlTableDictionary.put("present load(h)", 41);
		controlTableDictionary.put("present voltage", 42);
		controlTableDictionary.put("present temperature", 43);
		controlTableDictionary.put("registered", 44);
		controlTableDictionary.put("moving)", 46);
		controlTableDictionary.put("lock", 47);
		controlTableDictionary.put("punch(l)", 48);
		controlTableDictionary.put("punch(h)", 49);
	}

	
	
	/**********************************************************************************************************************************************************/
	
	//TODO legge til beskrivelser fra control table p� alle metodene. Legge inn logikk som sjekker for gyldig inn-data?
	
	
	public static int getModelNumber(int id){
		return readFromDxl(id, controlTableDictionary.get("model number(l)"));
	}
	
	public static int getVersionOfFirmware(int id){
		return readFromDxl(id, controlTableDictionary.get("version of firmware"));
	}
	
	public static int getID(int id){
		return readFromDxl(id, controlTableDictionary.get("id"));
	}
	
	public static void setID(int id, int newID){
		writeToDxl(id, controlTableDictionary.get("id"), newID);
	}
	
	public static int getBaudrate(int id){
		return readFromDxl(id, controlTableDictionary.get("baud rate"));
	}
	
	public static void setBaudrate(int id, int newBaud){
		writeToDxl(id, controlTableDictionary.get("baud rate"), newBaud);
	}
	
	public static int getReturnDelayTime(int id){
		return readFromDxl(id, controlTableDictionary.get("return delay time"));
	}
	
	public static void setReturnDelayTime(int id, int newReturnDelayTime){
		writeToDxl(id, controlTableDictionary.get("return delay time"), newReturnDelayTime);
	}
	
	public static int getCWAngleLimit(int id){
		return readFromDxl(id, controlTableDictionary.get("cw angle limit(l)"));
	}
	
	public static void setCWAngleLimit(int id, int newCWAngleLimit){
		writeToDxl(id, controlTableDictionary.get("cw angle limit(l)"), newCWAngleLimit);
	}
	
	public static int getCCWAngleLimit(int id){
		return readFromDxl(id, controlTableDictionary.get("ccw angle limit(l)"));
	}
	
	public static void setCCWAngleLimit(int id, int newCCWAngleLimit){
		writeToDxl(id, controlTableDictionary.get("ccw angle limit(l)"), newCCWAngleLimit);
	}
	
	public static int getTheHighestLimitTemperature(int id){
		return readFromDxl(id, controlTableDictionary.get("the highest limit temperature"));
	}
	
	public static void setTheHighestLimitTemperature(int id, int value){
		writeToDxl(id, controlTableDictionary.get("the highest limit temperature"), value);
	}

	public static int getTheLowestLimitVoltage(int id){
		return readFromDxl(id, controlTableDictionary.get("the lowest limit voltage"));
	}
	
	public static void setTheLowestLimitVoltage(int id, int value){
		writeToDxl(id, controlTableDictionary.get("the lowest limit voltage"), value); 
	}
	
	public static int getTheHighestLimitVoltage(int id){
		return readFromDxl(id, controlTableDictionary.get("the highest limit voltage"));
	}
	
	public static void setTheHighestLimitVoltage(int id, int value){
		writeToDxl(id, controlTableDictionary.get("the highest limit voltage"), value);
	}
	
	public static int getMaxTorque(int id){
		return readFromDxl(id, controlTableDictionary.get("max torque(l)"));
	}
	
	public static void setMaxTorque(int id, int value){
		writeToDxl(id, controlTableDictionary.get("max torque(l)"), value);
	}
	
	public static int getStatusReturnLevel(int id){
		return readFromDxl(id, controlTableDictionary.get("status return level"));
	}
	
	public static void setStatusReturnLevel(int id, int value){
		writeToDxl(id, controlTableDictionary.get("status return level"), value);
	}
	
	public static int getAlarmLED(int id){
		return readFromDxl(id, controlTableDictionary.get("alarm led"));
	}
	
	public static void setAlarmLED(int id, int value){
		writeToDxl(id, controlTableDictionary.get("alarm led"), value);
	}
	
	public static int getAlarmShutdown(int id){
		return readFromDxl(id, controlTableDictionary.get("alarm shutdown"));
	}
	
	public static void setAlarmShutdown(int id, int value){
		writeToDxl(id, controlTableDictionary.get("alarm shutdown"), value);
	}
	
	public static int getTorqueEnable(int id){
		return readFromDxl(id, controlTableDictionary.get("torque enable"));
	}
	
	public static void setTorqueEnable(int id, int value){
		writeToDxl(id, controlTableDictionary.get("torque enable"), value);
	}
	
	public static int getLED(int id){
		return readFromDxl(id, controlTableDictionary.get("led"));
	}
	
	public static void setLED(int id, int value){
		writeToDxl(id, controlTableDictionary.get("led"), value);
	}
	
	public static int getCWComplianceMargin(int id){
		return readFromDxl(id, controlTableDictionary.get("cw compliance margin"));
	}

	public static void setCWComplianceMargin(int id, int value){
		writeToDxl(id, controlTableDictionary.get("cw compliance margin"), value);
	}
	
	public static int getCCWComplianceMargin(int id){
		return readFromDxl(id, controlTableDictionary.get("ccw compliance margin"));
	}

	public static void setCCWComplianceMargin(int id, int value){
		writeToDxl(id, controlTableDictionary.get("ccw compliance margin"), value);
	}
	
	public static int getCWComplianceSlope(int id){
		return readFromDxl(id, controlTableDictionary.get("cw compliance slope"));
	}

	public static void setCWComplianceSlope(int id, int value){
		writeToDxl(id, controlTableDictionary.get("cw compliance slope"), value);
	}
	
	public static int getCCWComplianceSlope(int id){
		return readFromDxl(id, controlTableDictionary.get("ccw compliance slope"));
	}

	public static void setCCWComplianceSlope(int id, int value){
		writeToDxl(id, controlTableDictionary.get("ccw compliance slope"), value);
	}
	
	public static int getGoalPosition(int id){
		return readFromDxl(id, controlTableDictionary.get("goal position(l)"));
	}
	
	public static void setGoalPosition(int id, int value){
		writeToDxl(id, controlTableDictionary.get("goal position(l)"), value);
	}
	
	public static int getMovingSpeed(int id){
		return readFromDxl(id, controlTableDictionary.get("moving speed(l)"));
	}
	
	public static void setMovingSpeed(int id, int value){
		writeToDxl(id, controlTableDictionary.get("moving speed(l)"), value);
	}
	
	public static int getTorqueLimit(int id){
		return readFromDxl(id, controlTableDictionary.get("torque limit(l)"));
	}
	
	public static void setTorqueLimit(int id, int value){
		writeToDxl(id, controlTableDictionary.get("torque limit(l)"), value);
	}
	
	public static int getPresentPosition(int id){
		return readFromDxl(id, controlTableDictionary.get("present position(l)"));
	}
	
	public static int getPresentSpeed(int id){
		return readFromDxl(id, controlTableDictionary.get("present speed(l)"));
	}
	
	public static int getPresentLoad(int id){
		return readFromDxl(id, controlTableDictionary.get("present load(l)"));
	}
	
	public static int getPresentVoltage(int id){
		return readFromDxl(id, controlTableDictionary.get("present voltage"));
	}
	
	public static int getPresentTemperature(int id){
		return readFromDxl(id, controlTableDictionary.get("present temperature"));
	}
	
	/**
	 * Is instruction registered?
	 * @param id Dynamixel ID
	 * @return 0 or 1
	 */
	public static int getRegistered(int id){
		return readFromDxl(id, controlTableDictionary.get("registered"));
	}
	
	
	public static int getMoving(int id){
		return readFromDxl(id, controlTableDictionary.get("moving"));
	}
	
	/**
	 * Is EEPROM locked for modification?
	 * @param id
	 * @return 0 if open, 1 if locked
	 */
	public static int getLock(int id){
		return readFromDxl(id, controlTableDictionary.get("lock"));
	}
	

	public static void setLock(int id, int value){
		if(value != 0) value = 1; // make sure that the value will be either 0 or 1 
		writeToDxl(id, controlTableDictionary.get("lock"), value);
	}
	
	public static int getPunch(int id){
		return readFromDxl(id, controlTableDictionary.get("punch(l)"));
	}
	
	public static void setPunch(int id, int value){
		writeToDxl(id, controlTableDictionary.get("punch(l)"), value);
	}
	
	
	
	
	
	
	
/*********************************************************************************************************************************************************/

	
	
	
	
	
	
	
	
	// ekstrametoder underveis:
	
	/**
	 * Enables or disables torque, based on current status
	 * @param id Dynamixel ID
	 */
	public void torqueEnableSwitch(int id){
		int status = getTorqueEnable(id);
		if(status > 0) setTorqueEnable(id, 0); // if on, turn off
		else setTorqueEnable(id, 1); // if off, turn on
	}
	
	/**
	 * LED on/off switch
	 * @param id Dynamixel ID
	 */
	public void ledSwitch(int id){
		int status = getLED(id);
		if(status > 0) setLED(id, 0); // if on, turn off
		else setLED(id, 1);
	}
	
	/**
	 * Is there an instruction registered on the Dynamixel?
	 * @param id Dynamixel ID
	 * @return true/false
	 */
	public static boolean isInstructionRegistered(int id){
		return (getRegistered(id) > 0 ?  true : false);
	}
	
	
	/**
	 * Is Goal position achieved?
	 * @param id Dynamixel ID
	 * @return true if still in progress, false if completed
	 */
	public static boolean isMoving(int id){
		return (getMoving(id) > 0 ? true : false);
	}
	
	
	/**
	 * Returns whether the EEPROM is locked for modification
	 * @param id
	 * @return true if locked, false if open
	 */
	public static boolean isEEPROMLocked(int id){
		return(getLock(id) > 0 ? true : false);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////
	
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
	
	

	
	
	
	////////
	
	
	
	
	
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
	
	private static void writeWordToDxl(int id, int address, int value){
		IDynamixelControl.INSTANCE.dxl_write_word(id, address, value);
	}
	
	private static int readByteFromDxl(int id, int address){
		return IDynamixelControl.INSTANCE.dxl_read_byte(id, address);
	}
	
	private static int readWordFromDxl(int id, int address){
		return IDynamixelControl.INSTANCE.dxl_read_word(id, address);
	}
	
	

}

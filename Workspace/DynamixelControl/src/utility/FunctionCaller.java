package utility;


import dxl_communication.DynamixelControl;

public class FunctionCaller {

	
	/**
	 * Set-functions
	 * @param functionName
	 * @param ID
	 * @param value
	 */
	
	
	// TODO toLowerCase()
	
	public static void callSetFunction(String message){
		
		String[] separatedMessage = stringSeparator(message);
		
		if (separatedMessage.length != 3) return;
		
		int id = Integer.parseInt(separatedMessage[0]);
		String functionName = separatedMessage[1];
		int value = Integer.parseInt(separatedMessage[2]);

		
		
		
		//if (functionName == "setID") DynamixelControl.setID(id, value);
		if (functionName == "Set baudrate") DynamixelControl.setBaudrate(id, value);
		if (functionName == "setReturnDelayTime") DynamixelControl.setReturnDelayTime(id, value);
		if (functionName == "setCWAngleLimit") DynamixelControl.setCWAngleLimit(id, value);
		if (functionName == "setCCWAngleLimit") DynamixelControl.setCCWAngleLimit(id, value);
		if (functionName == "setTheHighestLimitTemperature") DynamixelControl.setTheHighestLimitTemperature(id, value);
		if (functionName == "setTheLowestLimitVoltage") DynamixelControl.setTheLowestLimitVoltage(id, value);
		if (functionName == "setTheHighestLimitVoltage") DynamixelControl.setTheHighestLimitVoltage(id, value);
		if (functionName == "setMaxTorque") DynamixelControl.setMaxTorque(id, value);
		if (functionName == "setStatusReturnLevel") DynamixelControl.setStatusReturnLevel(id, value);
		if (functionName == "setAlarmLED") DynamixelControl.setAlarmLED(id, value);
		if (functionName == "setAlarmShutdown") DynamixelControl.setAlarmShutdown(id, value);
		if (functionName == "setTorqueEnable") DynamixelControl.setTorqueEnable(id, value);
		if (functionName == "setLED") DynamixelControl.setLED(id, value);
		if (functionName == "setCWComplianceMargin") DynamixelControl.setCWComplianceMargin(id, value);
		if (functionName == "setCCWComplianceMargin") DynamixelControl.setCCWComplianceMargin(id, value);
		if (functionName == "setCWComplianceSlope") DynamixelControl.setCWComplianceSlope(id, value);
		if (functionName == "setCCWComplianceSlope") DynamixelControl.setCCWComplianceSlope(id, value);
		if (functionName == "setGoalPosition") DynamixelControl.setGoalPosition(id, value);
		if (functionName == "setMovingSpeed") DynamixelControl.setMovingSpeed(id, value);
		if (functionName == "setTorqueLimit") DynamixelControl.setTorqueLimit(id, value);
		if (functionName == "setLock") DynamixelControl.setLock(id, value);
		if (functionName == "setPunch") DynamixelControl.setPunch(id, value);

		
	}
	
	/**
	 * Overload: Get-functions
	 * @param functionName 
	 * @param ID
	 */
	
	//public static int callGetFunction(String functionName, int id, String message){
	public static int callGetFunction(String message){	
		String[] separatedMessage = stringSeparator(message);
		
		if (separatedMessage.length != 2) return -1;
		
		int id = Integer.parseInt(separatedMessage[0]);
		String functionName = separatedMessage[1];
		
		if (functionName == "getModelNumber".toLowerCase()) return DynamixelControl.getModelNumber(id);
		if (functionName == "getModelNumber") return DynamixelControl.getModelNumber(id);
		if (functionName == "getVersionOfFirmware") return DynamixelControl.getVersionOfFirmware(id);
		if (functionName == "getID") return DynamixelControl.getID(id);
		if (functionName == "getBaudrate") return DynamixelControl.getBaudrate(id);
		if (functionName == "getReturnDelayTime") return DynamixelControl.getReturnDelayTime(id);
		if (functionName == "getCWAngleLimit") return DynamixelControl.getCWAngleLimit(id);
		if (functionName == "getCCWAngleLimit") return DynamixelControl.getCCWAngleLimit(id);
		if (functionName == "getTheHighestLimitTemperature") return DynamixelControl.getTheHighestLimitTemperature(id);
		if (functionName == "getTheLowestLimitVoltage") return DynamixelControl.getTheLowestLimitVoltage(id);
		if (functionName == "getTheHighestLimitVoltage") return DynamixelControl.getTheHighestLimitVoltage(id);
		if (functionName == "getMaxTorque") return DynamixelControl.getMaxTorque(id);
		if (functionName == "getStatusReturnLevel") return DynamixelControl.getStatusReturnLevel(id);
		if (functionName == "getAlarmLED") return DynamixelControl.getAlarmLED(id);
		if (functionName == "getAlarmShutdown") return DynamixelControl.getAlarmShutdown(id);
		if (functionName == "getTorqueEnable") return DynamixelControl.getTorqueEnable(id);
		if (functionName == "getLED") return DynamixelControl.getLED(id);
		if (functionName == "getCWComplianceMargin") return DynamixelControl.getCWComplianceMargin(id);
		if (functionName == "getCCWComplianceMargin") return DynamixelControl.getCCWComplianceMargin(id);
		if (functionName == "getCWComplianceSlope") return DynamixelControl.getCWComplianceSlope(id);
		if (functionName == "getCCWComplianceSlope") return DynamixelControl.getCCWComplianceSlope(id);
		if (functionName == "getGoalPosition") return DynamixelControl.getGoalPosition(id);
		if (functionName == "getMovingSpeed") return DynamixelControl.getMovingSpeed(id);
		if (functionName == "getTorqueLimit") return DynamixelControl.getTorqueLimit(id);
		if (functionName == "getPresentPosition") return DynamixelControl.getPresentPosition(id);
		if (functionName == "getPresentSpeed") return DynamixelControl.getPresentSpeed(id);
		if (functionName == "getPresentLoad") return DynamixelControl.getPresentLoad(id);
		if (functionName == "getPresentVoltage") return DynamixelControl.getPresentVoltage(id);
		if (functionName == "getPresentTemperature") return DynamixelControl.getPresentTemperature(id);
		if (functionName == "getRegistered") return DynamixelControl.getRegistered(id);
		if (functionName == "isMoving") return DynamixelControl.getMoving(id);
		if (functionName == "isEEPROMLocked") return DynamixelControl.getLock(id);
		if (functionName == "getPunch") return DynamixelControl.getPunch(id);
		if (functionName == "getGoalPositionAngular") return DynamixelControl.getGoalPositionAngular(id);
		if (functionName == "getPresentPositionAngular") return DynamixelControl.getPresentPositionAngular(id);
		if (functionName == "getMovementMode") return DynamixelControl.getMovementMode(id);
		else return 0;
	}
	
	public static String[] stringSeparator(String message){

		return message.split(",");	
		
	}
	


	
	
	
	
//	public static void main(String args[]){
//		stringSeparator("getMoving,2,134");
//		
//	}
	
	
	/**
	 * TODO
	 * motta string -> call function
	 * 
	 * call function -> string
	 * 
	 * 
	 */
		
		
}

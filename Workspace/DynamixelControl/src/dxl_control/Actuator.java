package dxl_control;

import dxl_communication.DynamixelControl;
import java_native_access.IDynamixelControl;


public class Actuator {
	
	
	/*
	 * 
	 * Mangler: settere og gettere
	 * 
	 * 
	 */
	
	
	private int ID; // the Actuator ID
	
	/*
	 * Create an instance of a Dynamixel actuator
	 * @param ID The ID of this actuator
	 */
	public Actuator(int ID){
		this.ID = ID;
	}
	
	
	public void setID(int id){
		this.ID = id;
	}
	
	public int getID(){
		return this.ID;
	}
	
	
	
	/**
	 * This should automatically choose between byte and word
	 * 
	 * @return
	 */
	private int readStatus(int address){
		return DynamixelControl.readFromDxl(this.ID, address);
	}
	
	/**
	 * This should automatically choose between byte and word
	 */
	private void writeInstruction(int address, int value){

		DynamixelControl.writeToDxl(this.ID, address, value);
		
	}
	




}

package dxl_control;

import dxl_communication.Communication;
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
	private int read(){
	
		return 0;
	}
	
	/**
	 * This should automatically choose between byte and word
	 */
	private void write(int address, int value){

		// inneholder logikk som velger enten writeWord eller writeByte
		
	}
	
	private void writeByte(int address, int value){
	
		// inneholder logikk som velger enten writeWord eller writeByte
		
	}
	
	private void writeWord(int address, int value){
		Communication.writeWordToDxl(this.ID, address, value);
	}
	
	private int readByte(int address){
		return Communication.readByteFromDxl(this.ID, address);
	}
	
	private int readWord(int address){
		return Communication.readWordFromDxl(this.ID, address);
	}
	

	
	
	/**
	 * Transfers an instruction packet to the Dynamixel
	 * Before this is called, make sure that an instruction packet is created
	 */
	private void transferPacket(){
		IDynamixelControl.INSTANCE.dxl_tx_packet();
	}
	
	private void receivePacket(){
		IDynamixelControl.INSTANCE.dxl_rx_packet();
	}
	
	

	

	
	
	
	


}

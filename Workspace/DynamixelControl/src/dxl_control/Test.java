package dxl_control;

import java.util.HashMap;
import java.util.Map;

import java_native_access.IDynamixelControl;
import dxl_communication.Communication;

public class Test {
	
	
	public static void main(String args[]){
		
		
		Actuator a = new Actuator(2);
		
		Communication.initialize();

		
		System.out.println("ID on 2: " + Communication.readFromDxl(2, 3)); 
		System.out.println("Baud rate on 4: " + Communication.readFromDxl(2, 4));
		System.out.println("Baud rate on 4: " + Communication.readFromDxl(4, 4));
		
		System.out.println("ID on 4: " + Communication.readFromDxl(4, 3)); 
		
		Communication.terminate();
		
		
	}

	
	

}

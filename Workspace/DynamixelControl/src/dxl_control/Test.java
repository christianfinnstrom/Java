package dxl_control;

import java.util.HashMap;
import java.util.Map;

import java_native_access.IDynamixelControl;
import dxl_communication.Communication;

public class Test {
	
	
	public static void main(String args[]){
		
		
		Actuator a = new Actuator(2);
		
		Communication.initialize();
		System.out.println("hello");
		System.out.println(Communication.getModelNumber(2));
//		System.out.println(Communication.getBaudrate(a.getID()));
		
		
	}

	
	

}

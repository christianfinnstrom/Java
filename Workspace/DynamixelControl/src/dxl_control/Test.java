package dxl_control;

import dxl_communication.Communication;

public class Test {
	
	
	public static void main(String args[]){
		
		
		Actuator a = new Actuator(2);
		
		System.out.println("Heisann, kj�rer ");
		
		Communication.writeWordToDxl(2, 32, 250);
		
	}

	
	

}

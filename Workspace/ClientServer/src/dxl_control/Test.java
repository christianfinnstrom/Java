package dxl_control;


import client.TCPClient;
import gui.MotorControlPanel;



//import dxl_communication.DynamixelControl;
//import server.GreetingServer;


public class Test {
	
	
	public static void main(String args[]){
		

				
		MotorControlPanel motorControlPanel = new MotorControlPanel();
		motorControlPanel.showControlPanel();
		
		TCPClient tcpClient = new TCPClient("127.0.0.1", 8888);
		
		
		while(!motorControlPanel.cancelPressed){
			if (motorControlPanel.okPressed){
				String message = TCPClient.createMessageString(2, "setGoalPosition",
						motorControlPanel.sliderValue);
				tcpClient.sendToDynamixelComputer(message);
				
				motorControlPanel.okPressed = false;
			}
		}
		
		

		

		

		
		
		
		
	}

	
	

}
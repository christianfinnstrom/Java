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
		
		
		
		// TODO M� kj�res n�r GUI-client skal kj�res
		while(!motorControlPanel.cancelPressed){
			
			//TODO m� legge inn knapper til "get" og kalle riktig "if" hvis den blir trykket p�
			if (motorControlPanel.okPressed){
				
				//TODO Dette m� hente ut ID og functionName fra gui automatisk
				String message = TCPClient.createMessageString(2, "setGoalPosition",
						motorControlPanel.sliderValue);
				tcpClient.sendToDynamixelComputer(message);
				
				//Skal skrive ut melding fra server
				System.out.println(tcpClient.readFromDynamixelComputer());
				
				motorControlPanel.okPressed = false;
			}
		}
		
		

		

		

		
		
		
		
	}

	
	

}

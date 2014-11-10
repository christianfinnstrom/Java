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
			
			// Hvis id-ok er trykket henter den ut id fra textfield. Errormelding hvis feil input
			if(motorControlPanel.okIdPressed){
				System.out.println(motorControlPanel.id);
				motorControlPanel.okIdPressed = false;
			}
			
			//SET-pressed
			if (motorControlPanel.okSetPressed){
				
				//TODO Dette m� hente ut functionName fra gui automatisk
				String message = TCPClient.createMessageString(motorControlPanel.id, motorControlPanel.setFunction,
						motorControlPanel.setSliderValue);
				tcpClient.sendToDynamixelComputer(message);
				
				motorControlPanel.okSetPressed = false;
			}
			
			//GET-pressed
			if (motorControlPanel.okGetPressed){
				
				
				String message = TCPClient.createMessageString(motorControlPanel.id, motorControlPanel.getFunction);
				tcpClient.sendToDynamixelComputer(message);
				
				//TODO bruk denne verdien og sett den i riktig plass

				String getMessageReceived = motorControlPanel.getFunction + ": " + Integer.toString(tcpClient.readFromDynamixelComputer());
				motorControlPanel.setTextField(getMessageReceived);
				motorControlPanel.okGetPressed = false;
			}
		}
		
		

		

		

		
		
		
		
	}

	
	

}

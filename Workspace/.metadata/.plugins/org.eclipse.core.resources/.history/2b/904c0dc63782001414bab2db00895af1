package temp;


import network_communication.TCPClient;
import gui.MotorControlPanel;


public class ClientTest {
	
	
	public static void main(String args[]){
		

		
		MotorControlPanel motorControlPanel = new MotorControlPanel();
		motorControlPanel.showControlPanel();
		
		//TCPClient tcpClient = new TCPClient("127.0.0.1.", 8888);
		int port = Integer.parseInt(args[1]);
		TCPClient tcpClient = new TCPClient(args[0], port);
		
		
		
		// TODO Må kjøres når GUI-client skal kjøres
		while(!motorControlPanel.cancelPressed){
			
			// Hvis id-ok er trykket henter den ut id fra textfield. Errormelding hvis feil input
			if(motorControlPanel.okIdPressed){
				System.out.println(motorControlPanel.id);
				motorControlPanel.okIdPressed = false;
			}
			
			//SET-pressed
			if (motorControlPanel.okSetPressed){
				
				String message = TCPClient.createMessageString(motorControlPanel.id, motorControlPanel.setFunction,
						motorControlPanel.setValue);
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

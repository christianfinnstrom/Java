package temp;

import gui.MotorControlPanel;

import java.io.IOException;
import java.rmi.activation.ActivationGroupDesc.CommandEnvironment;

import network_communication.TCPClient;
import network_communication.TCPServer;
import control.ActuatorControl;
import utility.FunctionCaller;


public class ServerTest {
	
	
	public static void main(String args[]){
		
		
	
		ActuatorControl.initialize();

			
		
		/**
		 * client side
		 */
//		MotorControlPanel motorControlPanel = new MotorControlPanel();
//		motorControlPanel.showControlPanel();
//		
//		TCPClient tcpClient = new TCPClient("127.0.0.1", 8888);
//		
//		String message = TCPClient.createMessageString(2, "setBaudrate");
//		
//		tcpClient.sendToDynamixelComputer(message);
		
		/**
		 * server side
		 */
		
		int port = 8888;//Integer.parseInt(args[0]);
		
		  try
		  {
		     Thread t = new TCPServer(port);
		     t.start();
		     
		     
		  }catch(IOException e)
		  {
		     e.printStackTrace();
		  }
		
		
		
		
	}

	
	

}

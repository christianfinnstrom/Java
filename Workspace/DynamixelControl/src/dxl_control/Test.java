package dxl_control;

import gui.MotorControlPanel;

import java.io.IOException;

import client.TCPClient;
import dxl_communication.DynamixelControl;
import server.GreetingServer;


public class Test {
	
	
	public static void main(String args[]){
		
		/*
		Actuator a = new Actuator(2);
		
		Communication.initialize();
		
		Communication.setGoalPosition(2, 30);
		System.out.println(Communication.getGoalPosition(2));

		
		System.out.println("ID on 2: " + Communication.readFromDxl(2, 3)); 
		System.out.println("Baud rate on 4: " + Communication.readFromDxl(2, 4));
		System.out.println("Baud rate on 4: " + Communication.readFromDxl(4, 4));
		
		System.out.println("ID on 4: " + Communication.readFromDxl(4, 3)); 
		
		
		Communication.terminate();
		*/
			
		
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
		     Thread t = new GreetingServer(port);
		     t.start();
		     
		     
		  }catch(IOException e)
		  {
		     e.printStackTrace();
		  }
		
		
		
		
	}

	
	

}

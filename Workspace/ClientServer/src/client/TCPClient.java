package client;

import java.net.*;
import java.io.*;

public class TCPClient
{
	private Socket client;
	private static DataOutputStream out;
	private OutputStream outToServer;
	
	public TCPClient(String ip, int port){
		
		try {
			client = new Socket(ip, port);
			outToServer = client.getOutputStream();
			out = new DataOutputStream(outToServer);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void sendToDynamixelComputer(String message){
	   
		try {
			out.writeUTF(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
   }
	
	
//   public void startClient()
//   {
	  //Start slider
//	   MotorControlPanel motorControlPanel = new MotorControlPanel();
//	   motorControlPanel.showControlPanel();
	   
//      Socket client;
//      OutputStream outToServer;
//      DataOutputStream out;
      
//      try
//      {
    	 // Connect to server
//         System.out.println("Connecting to client on port " + ip + port);
         
         //sigbjørn ip: 78.91.47.246
         //Socket client = new Socket("127.0.0.1", port);
//         System.out.println("Client connected to " + client.getRemoteSocketAddress());
         
//         OutputStream outToServer = client.getOutputStream();
//         DataOutputStream out = new DataOutputStream(outToServer);
         //client.close();
//      }catch(IOException e)
//      {
//         e.printStackTrace();
//         
//         System.exit(1);
//      }
         
//         boolean dummyBool = false; // cancelpressed
//         boolean dummyBool2 = true; //ok pressed
//         
//         while(dummyBool == false){
//        	 try{
//        		 if (dummyBool2 == true){
//        			 //int sliderValue = motorControlPanel.getValue();
//        			 /*String tiss = createMessageString(3,"tisslur", sliderValue);
//        			 //out.writeUTF(tiss);
//        			  * 
//        			  * */
//        			 
//        			 // Write message to DynamixelComputer:
//        			 sendToDynamixelComputer(createMessageString(3, "tissefanten"), out);
//        			 
//        			 
//        			 dummyBool2 = false;
//        			 out.flush();
//        			 }
//        		 }catch(IOException e){
//        			 System.out.println("Sending error: " + e.getMessage());
//        		 }
//        }
//         System.out.println("Client closed");
//         // Close after messages are sent/received
//		 client.close();
		 
    
//   }
   
   public static String createMessageString(int id, String functionName){
	   
	   return (Integer.toString(id) + "," + functionName);
	   
   }
   
   public static String createMessageString(int id, String functionName,int value){
	   
	   return (Integer.toString(id) + "," + functionName + "," + Integer.toString(value));
   }


   
   // mangler at server sender tilbake, så denne fungerer ikke enda:
//   public static String readFromDynamixelComputer(String message, DataOutputStream out){
//	   try {
//		out.writeUTF(message);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	return message;
//   }

}
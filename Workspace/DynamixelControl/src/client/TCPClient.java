package client;

import java.net.*;

//import gui.MotorControlPanel;

import java.io.*;

public class TCPClient
{
   public static void main(String [] args)
   {
	  //Start slider
//	   MotorControlPanel motorControlPanel = new MotorControlPanel();
//	   motorControlPanel.showControlPanel();
	   
	   
	  //Name of the server
	   String ipName = args[0];
      //Port is the serverport
      int port = Integer.parseInt(args[1]);
      
      Socket client;
      OutputStream outToServer;
      DataOutputStream out;
      
      try
      {
    	 
    	 // Connect to server
         System.out.println("Connecting to client on port " + ipName + port);
         
         //sigbj�rn ip: 78.91.47.246
         client = new Socket(ipName, port);
         System.out.println("Client connected to " + client.getRemoteSocketAddress());
          
         // Wait for OK to be pressed and send value to Server
         outToServer = client.getOutputStream();
         out = new DataOutputStream(outToServer);
         
         boolean dummyBool = false; // cancelpressed
         boolean dummyBool2 = true; //ok pressed
         
         while(dummyBool == false){
        	 try{
        		 if (dummyBool2 == true){
        			 //int sliderValue = motorControlPanel.getValue();
        			 /*String tiss = createMessageString(3,"tisslur", sliderValue);
        			 //out.writeUTF(tiss);
        			  * 
        			  * */
        			 
        			 // Write message to DynamixelComputer:
        			 sendToDynamixelComputer(createMessageString(3, "tissefanten"), out);
        			 
        			 
        			 dummyBool2 = false;
        			 out.flush();
        			 }
        		 }catch(IOException e){
        			 System.out.println("Sending error: " + e.getMessage());
        		 }
        }
         System.out.println("Client closed");
         // Close after messages are sent/received
		 client.close();
		 
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
   
   public static String createMessageString(int id, String functionName){
	   
	   return (Integer.toString(id) + "," + functionName);
	   
   }
   
   public static String createMessageString(int id, String functionName,int value){
	   
	   return (Integer.toString(id) + "," + functionName + "," + Integer.toString(value));
   }

   public static void sendToDynamixelComputer(String message, DataOutputStream out){
	   
	   try {
		out.writeUTF(message);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
   
   // mangler at server sender tilbake, s� denne fungerer ikke enda:
   public static String readFromDynamixelComputer(String message, DataOutputStream out){
	   try {
		out.writeUTF(message);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return message;
   }

}
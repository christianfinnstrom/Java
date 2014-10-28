
import java.net.*;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.omg.CORBA.portable.ValueOutputStream;

public class TCPClient
{
   public static void main(String [] args)
   {
	  //Start slider
	   MotorControlPanel motorControlPanel = new MotorControlPanel();
	   motorControlPanel.showControlPanel();
	  //Name of the server
	   String ipname = args[0];
      //Port is the serverport
      int port = Integer.parseInt(args[1]);
      
      Socket client;
      OutputStream outToServer;
      DataOutputStream out;
      
      try
      {
    	 
    	 // Connect to server
         System.out.println("Connecting to client on port " + ipname +port);
         
         //sigbj�rn ip: 78.91.47.246
         client = new Socket("78.91.47.246", port);
         System.out.println("Client connected to " + client.getRemoteSocketAddress());
          
         // Wait for OK to be pressed and send value to Server
         outToServer = client.getOutputStream();
         out = new DataOutputStream(outToServer);
         
         while(motorControlPanel.cancelPressed == false){
        	 try{
        		 if (motorControlPanel.okPressed == true){
        			 int sliderValue = motorControlPanel.getValue();
        			 /*String tiss = createMessageString(3,"tisslur", sliderValue);
        			 //out.writeUTF(tiss);
        			  * 
        			  * */
        			 
        			 // Write message to DynamixelComputer:
        			 sendToDynamixelComputer(createMessageString(3, "tissefanten"), out);
        			 
        			 
        			 motorControlPanel.okPressed = false;
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
	   
	   out.writeUTF(message);
	   
   }
   
   // mangler at server sender tilbake, s� denne fungerer ikke enda:
   public static String readFromDynamixelComputer(String message, DataOutputStream out){
	   out.writeUTF(message);
   }

}
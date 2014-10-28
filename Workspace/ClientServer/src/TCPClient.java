
import java.net.*;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TCPClient
{
   public static void main(String [] args)
   {
	  //Start slider
	   MotorControlPanel motorControlPanel = new MotorControlPanel();
	   motorControlPanel.showControlPanel();
	  //Name of the server
	   String serverName = args[0];
      //Port is the serverport
      int port = Integer.parseInt(args[1]);
      
      try
      {
    	 
    	 // Connect to server
         System.out.println("Connecting to " + serverName + " on port " + port);
         
         //sigbj�rn ip: 78.91.47.246
         Socket client = new Socket("78.91.47.246", port);
         System.out.println("Client connected to " + client.getRemoteSocketAddress());
          
         // Wait for OK to be pressed and send value to Server
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         
         while(motorControlPanel.cancelPressed == false){
        	 try{
        		 if (motorControlPanel.okPressed == true){
        			 int sliderValue = motorControlPanel.getValue();
        			 out.writeInt(sliderValue);
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

}
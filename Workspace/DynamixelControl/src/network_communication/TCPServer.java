package network_communication;

import gui.ServerControlPanel;

import java.net.*;
import java.io.*;

import control.ActuatorControl;
import utility.FunctionCaller;

public class TCPServer extends Thread
{
   private ServerSocket serverSocket;
   
   public TCPServer(int port) throws IOException
   {
	   //open serversocket on specified port
	   serverSocket = new ServerSocket(port);
	   //Set to timeout if no client connects within specified milliseconds. 0 means infinity.
	   serverSocket.setSoTimeout(0);
   }

   public void run()
   {
      while(true)
      {
         try
         {
        	// Connect to client 
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
            //ServerControlPanel.updateTextField("Waiting for client on port " + serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Server connected to " + server.getRemoteSocketAddress());
            //ServerControlPanel.updateTextField(("Server connected to " + server.getRemoteSocketAddress()));
            
            // Receive message from client
            DataInputStream in = new DataInputStream(server.getInputStream());
            
            
            // Send message
            OutputStream outToServer = server.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            
            
            boolean done = false;
            while(!done){
            	try{
            		String messageFromClient = in.readUTF();
            		System.out.println(messageFromClient);
            		ServerControlPanel.updateTextField(messageFromClient);
            		
            		String[] separatedMessage = messageFromClient.split(",");
            		if (separatedMessage.length == 2){
            			
            			int messageToClient = FunctionCaller.callGetFunction(messageFromClient);
            			System.out.println("Get value: " + messageToClient);
            			ServerControlPanel.updateTextField("Get value: " + messageToClient);
            			out.writeInt(messageToClient);
                		out.flush();

            		}else{
            			FunctionCaller.callSetFunction(messageFromClient);
            		}
            		
//            		out.writeUTF("Hello back- this message is from the server" );
            		
            		
            	}catch(IOException e){
            		done = true;
            	}
            }

            // Close after messages are sent/received
            server.close();
            
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            ServerControlPanel.updateTextField("Socket timed out!");
            break;
            
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   
//   public static void main(String [] args)
//   {
//      int port = Integer.parseInt(args[0]);
//      try
//      {
//         Thread t = new GreetingServer(port);
//         t.start();
//      }catch(IOException e)
//      {
//         e.printStackTrace();
//      }
//   }
}
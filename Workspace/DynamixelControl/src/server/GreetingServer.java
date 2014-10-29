package server;

import java.net.*;
import java.io.*;

import utility.FunctionCaller;

public class GreetingServer extends Thread
{
   private ServerSocket serverSocket;
   
   public GreetingServer(int port) throws IOException
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
            Socket server = serverSocket.accept();
            System.out.println("Server connected to " + server.getRemoteSocketAddress());
            
            
            // Receive message from client
            DataInputStream in = new DataInputStream(server.getInputStream());
            boolean done = false;
            while(!done){
            	try{
            		String messageFromClient = in.readUTF();
            		System.out.println(messageFromClient);
            		
            		FunctionCaller.callSetFunction(messageFromClient);
            		
            	}catch(IOException e){
            		done = true;
            	}
            }

            // Close after messages are sent/received
            server.close();
            
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
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
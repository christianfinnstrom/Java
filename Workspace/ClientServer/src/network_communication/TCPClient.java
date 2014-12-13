package network_communication;

import java.net.*;
import java.io.*;

public class TCPClient
{
	private Socket client;
	private static DataOutputStream out;
	private OutputStream outToServer;
	InputStream inFromServer ;
    private static DataInputStream in;
	
	public TCPClient(String ip, int port){
		
		try {
			client = new Socket(ip, port);
			//output
			outToServer = client.getOutputStream();
			out = new DataOutputStream(outToServer);
			//input 
			inFromServer = client.getInputStream();
			in = new DataInputStream(inFromServer);
			
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
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
   }
	
	//  mangler at server sender tilbake, så denne fungerer ikke ennå:
	public int readFromDynamixelComputer(){
		
		int messageBack = 0;
		try {
			messageBack = in.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageBack;
	}
 
 
 public static String createMessageString(int id, String functionName){
	   
	   return (Integer.toString(id) + "," + functionName);
	   
 }
 
 public static String createMessageString(int id, String functionName,int value){
	   
	   return (Integer.toString(id) + "," + functionName + "," + Integer.toString(value));
 }
 
	
}
package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import temp.ClientTest;
import utility.UtilityClass;
import temp.MainProgram;

public class WelcomeScreen {

	
	public String ipAddress;
	public String portAddress;
	public int returnValue = 0;
	
	private JFrame mainWelcomeFrame;
	
	private JPanel localPanel;
	private JPanel clientPanel;
	private JPanel serverPanel;
	
	public WelcomeScreen(){
		prepareGUI();
	}

	private void prepareGUI() {
		
		mainWelcomeFrame = new JFrame("Welcome");
		mainWelcomeFrame.setSize(400,600);
		mainWelcomeFrame.setLayout(new GridLayout(3, 1));
		mainWelcomeFrame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent windowEvent){
			System.exit(0);
		}
		});
		
			
		localPanel = new JPanel();
		localPanel.setLayout(new FlowLayout());
		
		clientPanel = new JPanel();
		clientPanel.setLayout(new FlowLayout());
		
		serverPanel = new JPanel();
		serverPanel.setLayout(new FlowLayout());
		
	}
	
	// Called in MainProgram
		public int showWelcomeScreen() {
			
			// Text field
			final JTextField ipTextField = new JTextField(5);
			final JTextField portClientTextField = new JTextField(5);
			final JTextField portServerTextField = new JTextField(5);
			
			// Buttons
			JButton localButton = new JButton("Local");
			JButton clientButton = new JButton("Client");
			JButton serverButton = new JButton("Server");
			
			// Label
			JLabel portClientLabel = new JLabel("Port: ");
			JLabel portServerLabel = new JLabel("Port: ");
			JLabel ipLabel = new JLabel("IP: ");
			
			
			// Listeners
			
			

			
			
			// Add content to panels
			localPanel.add(localButton);
			
			clientPanel.add(ipLabel);
			clientPanel.add(ipTextField);
			clientPanel.add(portClientLabel);
			clientPanel.add(portClientTextField);
			clientPanel.add(clientButton);
			
			serverPanel.add(portServerLabel);
			serverPanel.add(portServerTextField);
			serverPanel.add(serverButton);
			
			mainWelcomeFrame.add(localPanel);
			mainWelcomeFrame.add(clientPanel);
			mainWelcomeFrame.add(serverPanel);
			
			mainWelcomeFrame.setVisible(true);
			
			
			while(returnValue == 0){
				
				// Local pressed
				localButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e){
						// Launch Local
						
						returnValue = 1;
						mainWelcomeFrame.dispose();
					}
				});
				
				
				// Client pressed
				clientButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e){
						
						ipAddress = ipTextField.getText();
						
						if(!(UtilityClass.isIPAddressValid(ipAddress))){
							JOptionPane.showMessageDialog(null, "IP address is not valid.", "Bad input",  JOptionPane.ERROR_MESSAGE);
							return;
							
						}else{
							
							try{
								portAddress = portClientTextField.getText(); //Integer.parseInt(
								// Launch Client with IP of host
								
								
								String s[] = {ipAddress, portAddress};
								MainProgram.ipAndPort = s;
								
								returnValue = 2;
								mainWelcomeFrame.dispose();
								
							}catch (NumberFormatException ex){
								JOptionPane.showMessageDialog(null, "port-field must be integer between 1 and xxx", "Bad input",  JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}
				});
				
				
				// Server pressed
				serverButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e){
						// Launch server
						
						MainProgram.serverPort = portServerTextField.getText();

						returnValue = 3;
						mainWelcomeFrame.dispose();
					}
				});
			}
			
			return returnValue;
			
		}
				  
	
}

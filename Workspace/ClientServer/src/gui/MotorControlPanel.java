package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;



	public class MotorControlPanel {
		
		// Initialize variables
		public JTextField getTextField;
		
		public int id = 0;
		public int setSliderValue = 200;
		public boolean okSetPressed = false;
		public boolean okGetPressed = false;
		public boolean okIdPressed = false;
		
		public String setFunction = "";
		public String getFunction = "";
		
		public boolean cancelPressed = false;
		private JFrame mainFrame;
		
		private JPanel idPanel;
		private JPanel setPanel;
		private JPanel getPanel;
		
		

		
	public MotorControlPanel() {
		prepareGUI();
	}

		  
	// Set up GUI for the motor controlpanel
	private void prepareGUI() {
	 
		mainFrame = new JFrame("Motor control-table");
		mainFrame.setSize(400,600);
		mainFrame.setLayout(new GridLayout(4, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent windowEvent){
		//					System.exit(0);
		}
		});
			
		idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout());
		
		setPanel = new JPanel();
		setPanel.setLayout(new FlowLayout());
		
		getPanel = new JPanel();
		getPanel.setLayout(new FlowLayout());
		
	}

		
	// Called from client
	public void showControlPanel() {
			  
			  
		//dropdown-list with set/get functions
		String[] getFunctions = { "get Model Number", 
				"get Model Number", 
				"get Version Of Firmware", 
				"get ID",
				"get Baudrate", 
				"get Return Delay Time", 
				"get CW Angle Limit", 
				"get CCW Angle Limit", 
				"get The Highest Limit Temperature", 
				"get The Lowest Limit Voltage", 
				"get The Highest Limit Voltage", 
				"get Max Torque", 
				"get Status Return Level", 
				"get Alarm LED", 
				"get Alarm Shutdown", 
				"get Torque Enable", 
				"get LED", 
				"get CW Compliance Margin", 
				"get CCW Compliance Margin", 
				"get CW Compliance Slope", 
				"get CCW Compliance Slope", 
				"get Goal Position", 
				"get Moving Speed", 
				"get Torque Limit", 
				"get Present Position", 
				"get Present Speed", 
				"get Present Load", 
				"get Present Voltage", 
				"get Present Temperature",
				"get Registered", 
				"is Moving", 
				"is EEPROM Locked", 
				"get Punch", 
				"get Goal Position Angular", 
				"get Present Position Angular",
				"get Movement Mode"};
		
		String[] setFunctions = { 		
				//"setID",
				"Set baudrate",
				"set Return Delay Time",
				"set CW Angle Limit",
				"set CCW Angle Limit",
				"set The Highest Limit Temperature",
				"set The Lowest Limit Voltage",
				"set The Highest Limit Voltage",
				"set Max Torque",
				"set Status Return Level",
				"set Alarm LED",
				"set Alarm Shutdown",
				"set Torque Enable",
				"set LED",
				"set CW Compliance Margin",
				"set CCW Compliance Margin",
				"set CW Compliance Slope",
				"set CCW Compliance Slope",
				"set Goal Position",
				"set Moving Speed",
				"set Torque Limit",
				"set Lock",
				"set Punch"};
		
		final JComboBox<String> setList = new JComboBox<String>(setFunctions);
		final JComboBox<String> getList = new JComboBox<String>(getFunctions);
		
		  
		// Textfield
		final JTextField idTextField = new JTextField(5);
		getTextField = new JTextField(20);
		getTextField.setEditable(false);
		  
		// Sliders
		final JSlider setSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, setSliderValue);
		setSlider.setMinorTickSpacing(100);
		setSlider.setMajorTickSpacing(200);
		setSlider.setPaintTicks(true);
		setSlider.setPaintLabels(true);
		
		
		
		//Buttons
		JButton okIdButton = new JButton("OK - id");
		JButton okSetButton = new JButton("OK - set");
		JButton okGetButton = new JButton("OK - get");
		JButton cancelButton = new JButton("Close controlpanel");
		
		//Listners

		okIdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try{
					id = Integer.parseInt(idTextField.getText());
					
				}catch (NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "ID-field must be integer between 1 and xxx", "Bad input",  JOptionPane.ERROR_MESSAGE);
					return;
				}
				okIdPressed = true;
			}
		});
		
		okSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setFunction = (String) setList.getSelectedItem();
				setSliderValue = setSlider.getValue();
				okSetPressed = true;
			}
		});
		
		okGetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				getFunction = (String) getList.getSelectedItem();
				okGetPressed = true;
			
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelPressed = true;
				System.exit(0);
			}
		});
		
		idPanel.add(idTextField);
		idPanel.add(okIdButton);
		
		setPanel.add(setList);
		setPanel.add(setSlider);
		setPanel.add(okSetButton);
		
		getPanel.add(getList);
		getPanel.add(okGetButton);
		getPanel.add(getTextField);
		
		mainFrame.add(idPanel);
		mainFrame.add(setPanel);
		mainFrame.add(getPanel);
		mainFrame.add(cancelButton);
		mainFrame.setVisible(true);
		}
	
		
	public int getSliderValue(){
		return this.setSliderValue;
	}
	
	public void setTextField(String getMessageReceived){
		getTextField.setText(getMessageReceived);
	}
}
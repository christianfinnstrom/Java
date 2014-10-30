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
		public int id = 0;
		public int setSliderValue = 200;
		public boolean okSetPressed = false;
		public boolean okGetPressed = false;
		public boolean okIdPressed = false;
		
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
		String[] setFunctions = { "Set goal position", "Set baudrate" };
		String[] getFunctions = { "Get goal position", "Get baudrate" };
		
		JComboBox<String> setList = new JComboBox<String>(setFunctions);
		JComboBox<String> getList = new JComboBox<String>(getFunctions);
		
		  
		// Textfield
		final JTextField idTextField = new JTextField(5);
		JTextField setTextField = new JTextField(10);
		  
		// Sliders
		final JSlider setSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, setSliderValue);
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
				setSliderValue = setSlider.getValue();
				okSetPressed = true;
			}
		});
		
		okGetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
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
		
		mainFrame.add(idPanel);
		mainFrame.add(setPanel);
		mainFrame.add(getPanel);
		mainFrame.add(cancelButton);
		mainFrame.setVisible(true);
		}
	
		
	public int getValue(){
		return this.setSliderValue;
	}
}
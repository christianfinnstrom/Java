package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

	public class MotorControlPanel {
		
		// Initialize variables
		public int sliderValue = 200;
		public boolean okPressed = false;
		public boolean cancelPressed = false;
		private JFrame mainFrame;
		private JPanel buttonPanel;
		
		  public MotorControlPanel() {
			prepareGUI();
		  }

		  
		  // Set up GUI for the motor controlpanel
		  private void prepareGUI() {
			  
			mainFrame = new JFrame("Motor control-table");
			mainFrame.setSize(400,200);
			mainFrame.setLayout(new GridLayout(2, 1));
			mainFrame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent windowEvent){
//					System.exit(0);
				}
			});
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			
			
		}

		
		  // Called from client
		  public void showControlPanel() {
			  
				// Slider 
				final JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 200);
				slider.setMinorTickSpacing(100);
				slider.setMajorTickSpacing(200);
				slider.setPaintTicks(true);
				slider.setPaintLabels(true);
			  
			
			  	JButton okButton = new JButton("OK");
				JButton cancelButton = new JButton("Close controlpanel");
				
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						sliderValue = slider.getValue();
						okPressed = true;
					}
				});
				
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelPressed = true;
						System.exit(0);
					}
				});
				
				buttonPanel.add(okButton);
				buttonPanel.add(cancelButton);
				
				mainFrame.add(slider);
				mainFrame.add(buttonPanel);
				mainFrame.setVisible(true);
		}
		
		public int getValue(){
			return this.sliderValue;
		}
		}
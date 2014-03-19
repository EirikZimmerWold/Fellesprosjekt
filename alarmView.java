package Alarm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// TO DO 
// this view will take one argument which is date, and displays the start time in order for 
// user to set a period for warning the user that the meeting will happen in that period.

public class alarmView extends JPanel {
	
	// Fields
		
		private	JLabel title;
		private JLabel startLabel;
		private JLabel endLabel;
		private JLabel startTimeLabel;
		private JComboBox endHour;
		private JButton confirmButton;
		private JButton cancelButton;
		
	// Constructor will take start-time as arguments (hour, minute)
		public alarmView(){
			
			title = new JLabel();
			title.setText("Set Alarm");

			startLabel = new JLabel();
			startLabel.setText("M¿te Starter");
			endLabel = new JLabel();
			endLabel.setText("Set Alarm");
			
			startTimeLabel = new JLabel();

			String[] hour = {"15 min", "30 min", "1 hour", "2 hours", "3 hours", "4 hours", "5 hours", "1 day", "2 days", "1 week"};
			endHour = new JComboBox(hour);
			confirmButton = new JButton();
			confirmButton.setText("Confirm");
			confirmButton.setName("confirm");
			cancelButton = new JButton("Cancel");
			
			// Add components
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5,5,5,5);
			gbc.gridwidth = 2;
			gbc.gridx = 0;
			gbc.gridy = 0;
			add(title, gbc);
			
			gbc.gridwidth = 1;
			gbc.gridx = 0;
			gbc.gridy = 1;
			add(startLabel,gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 1;
			add(startTimeLabel, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 2;
			add(endLabel, gbc);
/*			
			gbc.gridx = 1;
			gbc.gridy = 2;
			add(endDay, gbc);
			
			gbc.gridx = 2;
			gbc.gridy = 2;
			add(endMonth, gbc);
			
			gbc.gridx = 3;
			gbc.gridy = 2;
			add(endYear, gbc);
*/			
			gbc.gridx = 1;
			gbc.gridy = 2;
			add(endHour, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 3;
			add(confirmButton, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 3;
			add(cancelButton, gbc);
			
		}
	
	// Methods
		public String getSelectedInterval(){
			return (String)this.endHour.getSelectedItem();
		}
		
		public void addButtonsListener(ActionListener al){
			this.confirmButton.addActionListener(al);
			this.cancelButton.addActionListener(al);
		}
		
		public void setStartTimeLabelText(String date){
			this.startTimeLabel.setText(date);
		}
	// Setter and Getter
		public JButton getConfirmButton(){
			return this.confirmButton;
		}
		
		public JButton getCancelButton(){
			return this.cancelButton;
		}
	
}
 
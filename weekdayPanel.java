package Fellesprosjektet;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import java.awt.GridBagLayout;


public class weekdayPanel extends JPanel{

	public weekdayPanel(String day, String date){
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		JLabel dayName = new JLabel(day);
		JLabel dayDate = new JLabel(date);
		
		
		DefaultListModel<String> data = new DefaultListModel<String>();
		
		//Test
		
		for (int i = 0; i<100; i++){
		data.addElement("10:00" + "    " + "Møte");
		//Trenger metoder for å hente ut møteinfo
		}
		
	
		JList<String> appointments = new JList<String>(data);
		JScrollPane scrollPane = new JScrollPane(appointments);
		appointments.setFixedCellHeight(25);	
		appointments.setVisibleRowCount(10);
			
		
		add(dayName, gc);
		gc.gridy=1;
		add(dayDate, gc);
		
		
		gc.gridy=2;
		add(scrollPane, gc);
		
		
		System.out.println(data.getSize());
		
		

		
	}		
}

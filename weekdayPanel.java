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

	JLabel dayName;
	JLabel dayDate;

	public weekdayPanel(String day){
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		dayName = new JLabel(day);
		dayDate = new JLabel();
		
		
		DefaultListModel data = new DefaultListModel();
		
		//Test
		
		for (int i = 0; i<100; i++){
		data.addElement("10:00" + "    " + "M�te");
		//Trenger metoder for � hente ut m�teinfo
		}
		
	
		JList appointments = new JList(data);
		JScrollPane scrollPane = new JScrollPane(appointments);
		appointments.setFixedCellHeight(25);	
		appointments.setVisibleRowCount(10);
			
		
		add(dayName, gc);
		gc.gridy=1;
		add(dayDate, gc);
		
		
		gc.gridy=2;
		add(scrollPane, gc);
		
		
		//System.out.println(data.getSize());
	}

	public void setDate(String date){
		dayDate.setText(date);
	}
}

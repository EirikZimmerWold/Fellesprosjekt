package Fellesprosjektet;

import java.awt.Color;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;


public class weekdayPanel extends JPanel implements PropertyChangeListener{
	private JLabel dayName;
	private JLabel dayDate;
	private JPanel list;
	private GridBagConstraints gbc;
	private ProgramFrame frame;

	public weekdayPanel(String day, ProgramFrame frame){
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		this.frame=frame;
		
		dayName = new JLabel(day);
		dayDate = new JLabel(" ");
		
		list=new JPanel(new GridBagLayout());
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weighty = 1;
        gc.weighty=1;
        gc.gridx=0;
        gc.gridy=2;
        list.add(new JPanel(), gc);
        list.setBackground(Color.white);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(175,450));
		add(scrollPane, gc);
		
		gc.gridx=0;
		gc.gridy=0;
		add(dayName, gc);
		gc.gridx=0;
		gc.gridy=1;
		add(dayDate, gc);
	}

	public void setDate(String date){
		dayDate.setText(date);
	}
	
	public void leggeTilAvtale(Ansatt eier){
		
	}
	public void addAvtalePanel(Avtale avtale){
		AvtalePanel panel=new AvtalePanel(avtale);
		gbc=new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx=1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.addPropertyChangeListener(this);
        list.add(panel, gbc, 0);
        validate();
        repaint();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		avtaleview avtaleview;
		try {
			avtaleview = new avtaleview(((AvtalePanel)evt.getSource()).getAvtale(), frame);
			avtaleview.pack();
			avtaleview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			avtaleview.setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}

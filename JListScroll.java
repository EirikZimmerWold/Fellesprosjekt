package Fellesprosjektet;
import java.awt.BorderLayout;
import java.awt.Dimension;    

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class JListScroll extends JList{

    private JScrollPane listScrollPane = new JScrollPane();
    private DefaultListModel dModel;
    private JList JL;
    

    public JListScroll(DefaultListModel dm) {
    	dModel = new DefaultListModel();
    	dModel = dm;
    	JL = new JList(dModel);
        JL.setVisibleRowCount(5);
        listScrollPane.setViewportView(JL);
    }
    
    public JScrollPane getJScrollPane() {
		return listScrollPane;
    }
    
    //Brukes hvis nye ansatte legges til feks
    public void setDefaultListModel(DefaultListModel dm) {
    	dModel = dm;
    	JL = new JList(dm);
    	JL.setVisibleRowCount(5);
        listScrollPane.setViewportView(JL);
    	
    }
    
    public DefaultListModel getDefaultListModel() {
    	return dModel;
    }
    
    public JList getJList() {
    	return JL;
    }

}
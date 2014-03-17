package Fellesprosjektet;

import java.awt.Component;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainPanel extends JPanel {

	private ProgramFrame frame;
	
	private JTabbedPane panelTabs;
	
	private weekView weekPanel;
	private NotifikasjonListPanel notifikasjonPanel;
	
	private JLabel loggedInLabel;
	private String loggedInText = "Logget inn som: ";
	private String currUser = "";
	
	private Database db;
	
	public MainPanel(ProgramFrame frame) throws SQLException {
		this.frame = frame;
		
		db=new Database();
		
		loggedInLabel = new JLabel(loggedInText + currUser);
		
		panelTabs = new JTabbedPane();
		
		weekPanel = new weekView(frame);
		notifikasjonPanel = new NotifikasjonListPanel(frame);
		
		panelTabs.add("Kalender",weekPanel);
		panelTabs.add("Notifikasjoner",notifikasjonPanel);
		
		initDesign();
	}
	
	public void updatePanelTabTitle(Component object, String title){
		Component[] clist = panelTabs.getComponents();
		for(int i = 0;i<clist.length; i++){
			if(clist[i].equals(object)){
				panelTabs.setTitleAt(i, title);
			}
		}
	}

	public void enableComponents(){
		panelTabs.setEnabled(true);
		for(Component c : weekPanel.getComponents()){
			c.setEnabled(true);
		}
		loggedInLabel.setEnabled(true);
	}
	
	public void disableComponents(){
		panelTabs.setEnabled(false);
		for(Component c : weekPanel.getComponents()){
			c.setEnabled(false);
		}
		loggedInLabel.setEnabled(false);
	}
	
	public void initDesign(){
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(loggedInLabel)
				.addComponent(panelTabs)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(loggedInLabel)
				.addComponent(panelTabs)
		);
	}
	
	public void setCurrUser(String brukernavn){
		try {
			currUser=db.getNavn(brukernavn);
			loggedInLabel.setText(loggedInText + currUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


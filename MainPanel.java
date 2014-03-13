package Fellesprosjektet;

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
	
	public MainPanel(ProgramFrame frame) {
		this.frame = frame;
		
		loggedInLabel = new JLabel(loggedInText + currUser);
		
		panelTabs = new JTabbedPane();
		
		weekPanel = new weekView();
		notifikasjonPanel = new NotifikasjonListPanel();
		
		panelTabs.add("Kalender",weekPanel);
		panelTabs.add("Notifikasjoner",notifikasjonPanel);
		
		initDesign();
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

}

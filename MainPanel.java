package Fellesprosjektet;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainPanel extends JPanel {

	//Fields
	private ProgramFrame frame;
	
	private JTabbedPane panelTabs;
	
	private weekView weekPanel;
	private NotifikasjonListPanel notifikasjonPanel;
	
	private JLabel loggedInLabel;
	private String loggedInText = "Logget inn som: ";
	private String currUser = "";
	
	private Database db;
	/*
	 * Contructor for main panel
	 */
	public MainPanel(ProgramFrame frame) throws SQLException {
		this.frame = frame;
		
		db=new Database();
		
		loggedInLabel = new JLabel(loggedInText + currUser);
		
		panelTabs = new JTabbedPane();
		
		weekPanel = new weekView(frame);
		notifikasjonPanel = new NotifikasjonListPanel(frame);
		
		panelTabs.add("Kalender",weekPanel);
		panelTabs.add("Notifikasjoner",notifikasjonPanel);
		panelTabs.addChangeListener(new REFRESH());
		initDesign();
	}
	class REFRESH implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			if(panelTabs.getSelectedComponent()==weekPanel){
				frame.update();
			}else if(panelTabs.getSelectedComponent()==notifikasjonPanel){
				try {
					notifikasjonPanel.seNotifikasjoner();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			//sette valgt deltager til bekreftet status
		}
	}
	
	/*
	 * Method to update the title of a tab in the tabbed pane
	 */
	public void updatePanelTabTitle(Component object, String title){
		Component[] clist = panelTabs.getComponents();
		for(int i = 0;i<clist.length; i++){
			if(clist[i].equals(object)){
				panelTabs.setTitleAt(i, title);
			}
		}
	}
	
	/*
	 * Get-method for the weekview object.
	 */
	public weekView getWeekview(){
		return this.weekPanel;
	}
	
	/*
	 * Enables a selection of the components
	 */
	public void enableComponents(){
		panelTabs.setEnabled(true);
		for(Component c : weekPanel.getComponents()){
			c.setEnabled(true);
		}
		loggedInLabel.setEnabled(true);
	}
	
	/*
	 * Disables a selection of the components.
	 */
	public void disableComponents(){
		panelTabs.setEnabled(false);
		for(Component c : weekPanel.getComponents()){
			c.setEnabled(false);
		}
		loggedInLabel.setEnabled(false);
	}
	
	/*
	 * Iniializes the design.
	 */
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
	
	/*
	 * Sets the string for the current logged in user.
	 */
	public void setCurrUser(String brukernavn){
		try {
			currUser=db.getNavn(brukernavn);
			loggedInLabel.setText(loggedInText + currUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Swaps the tabbet pane to the week view tab.
	 */
	public void byttTilWeekView(){
		panelTabs.setSelectedComponent(weekPanel);
	}
}


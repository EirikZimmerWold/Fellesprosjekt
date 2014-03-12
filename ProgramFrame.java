package Fellesprosjekt;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class ProgramFrame extends JFrame implements ActionListener{

	private final Dimension windowSize = new Dimension(600,400); 
	private JMenuBar menubar;
	private JMenu fileMenu;
	private JMenu netMenu;
	
	private JMenuItem exitItem;
	private JMenuItem loginItem;
	private JMenuItem logoutItem;
	private JMenuItem acceptNetItem;
	private JMenuItem connectNetItem;
	private JMenuItem disconnectNetItem;
	
	private JTabbedPane panelTabs;
	
	private JPanel testPanel;
	private JPanel testPanel2; 
	
	public ProgramFrame() {
		init();
		initMenu();
		
		panelTabs = new JTabbedPane();
		
		testPanel = new JPanel();
		testPanel2 = new JPanel();
		
		panelTabs.add(testPanel, "Kalender");
		panelTabs.add(testPanel2, "Notifikasjoner");
		
		System.out.println(panelTabs.getComponentCount());
		System.out.println(panelTabs.getComponent(0).toString());
		System.out.println(panelTabs.getComponent(1).toString());
		System.out.println(testPanel.getParent());
	
		JLabel test = new JLabel("Logget inn som");
		
		add(test);
		add(panelTabs);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == loginItem){
			LoggInnPanel loggInn = new LoggInnPanel();
		}
	}
	
	public void initMenu(){
		menubar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		netMenu = new JMenu("Net");
		
		exitItem = new JMenuItem("Exit");
		exitItem.setToolTipText("Exits the application");
		exitItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		
		loginItem = new JMenuItem("Log in");
		loginItem.setToolTipText("Log in to the application");
		loginItem.addActionListener(this);
		
		logoutItem = new JMenuItem("Log out");
		logoutItem.setToolTipText("Log out of the application");
		logoutItem.addActionListener(this);
		
		fileMenu.add(loginItem);
		fileMenu.add(logoutItem);
		fileMenu.add(exitItem);
		
		acceptNetItem = new JMenuItem("Accept incoming");
		acceptNetItem.setToolTipText("Accept incoming connections");
		connectNetItem = new JMenuItem("Connect");
		connectNetItem.setToolTipText("Connect to another user");
		disconnectNetItem = new JMenuItem("Disconnect");
		disconnectNetItem.setToolTipText("Disconnect from user");
		
		netMenu.add(acceptNetItem);
		netMenu.add(connectNetItem);
		netMenu.add(disconnectNetItem);
		
		menubar.add(fileMenu);
		menubar.add(netMenu);
		
		setJMenuBar(menubar);
	}
	
	public void init(){
		setMinimumSize(windowSize);
		setPreferredSize(windowSize);
		setMaximumSize(windowSize);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProgramFrame();
            }
        });
	}
}

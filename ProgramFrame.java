package Fellesprosjektet;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/*
 * Hovedklassen som blir kjøres når programmet starter.
 */

public class ProgramFrame extends JFrame implements ActionListener{
	
	/*
	 * Div variabler. 
	 */
	private final Dimension windowSize = new Dimension(800,650); 
	private JMenuBar menubar;
	private JMenu fileMenu;
	private JMenu netMenu;
	private JMenuItem exitItem;
	private JMenuItem loginItem;
	private JMenuItem logoutItem;
	private JMenuItem acceptNetItem;
	private JMenuItem connectNetItem;
	private JMenuItem disconnectNetItem;
	private MainPanel mainPanel;
	private Database db;
	private Ansatt User = null;
	private Ansatt kalenderEier = null;
	private GregorianCalendar tid;
	/*
	 * Konstruktøren. Starter generelt gui med design. 
	 */	
	public ProgramFrame() throws SQLException {
		init();
		initMenu();
		
		db = new Database();
		
		mainPanel = new MainPanel(this);
		
		tid = new GregorianCalendar();
		
		disableComponents();
		
		add(mainPanel);
	}
	
	/*
	 * Get-metode for databaseobjekt
	 */
	
	public Database getDB(){
		return db;
	}
	
	/*
	 * Get-metode for mainPanel som holder annet design og views. 
	 */
	
	public MainPanel getMainPanel(){
		return mainPanel;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 */
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == loginItem){
			JFrame frame = new JFrame();
			LoggInnPanel loggInn = new LoggInnPanel(this, frame);
			frame.setContentPane(loggInn);
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}else if(event.getSource()==logoutItem){
			disableComponents();
			setUser(null);
			mainPanel.setCurrUser("");
			
			update();
		}
	}
	
	/*
	 * Gjør at alle komponentene i programmet kan brukes.
	 */ 
	
	public void enableComponents(){
		logoutItem.setEnabled(true);
		loginItem.setEnabled(false);
		acceptNetItem.setEnabled(true);
		connectNetItem.setEnabled(true);
		disconnectNetItem.setEnabled(true);
		
		mainPanel.enableComponents();
		
		
	}
	
	/*
	 * Gjør at komponentene ikke kan brukes. 
	 */
	
	public void disableComponents(){
		logoutItem.setEnabled(false);
		loginItem.setEnabled(true);
		acceptNetItem.setEnabled(false);
		connectNetItem.setEnabled(false);
		disconnectNetItem.setEnabled(false);
		
		mainPanel.disableComponents();
		
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
	
	
	public void setKalenderEier(Ansatt eier){
		this.kalenderEier=eier;
	}
	public Ansatt getKalenderEier(){
		return kalenderEier;
	}
	public void setUser(String brukernavn){
		try {
			this.User=db.getBestemtAnsatt(brukernavn);
			kalenderEier = db.getBestemtAnsatt(brukernavn);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("setUser() virker ikke");
		}
	}
	
	public Ansatt getUser(){
		if (this.User==null){
			return null;
		}
		return this.User;
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
                try {
					new ProgramFrame();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        });
	}
	
	public void update(){
		tid.set(Calendar.DAY_OF_WEEK, tid.getFirstDayOfWeek());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date firstDay = tid.getTime();
		String fDay = df.format(firstDay);
		System.out.println(fDay);
		String[] dates = fDay.split("/");
	    int date = Integer.parseInt(dates[0]); 
	    int month = Integer.parseInt(dates[1]);         
	    int year = Integer.parseInt(dates[2]); 
	    weekView weekview=mainPanel.getWeekview();
	    try {
			weekview.generateThisWeek(date, month, year);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

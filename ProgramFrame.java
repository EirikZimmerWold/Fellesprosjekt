package Fellesprosjektet;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/*
 * Main-class
 */

public class ProgramFrame extends JFrame implements ActionListener{
	
	/*
	 * Fields.
	 */
	private final Dimension windowSize = new Dimension(1350,650); 
	private JMenuBar menubar;
	private JMenu fileMenu;
	private JMenuItem exitItem;
	private JMenuItem loginItem;
	private JMenuItem logoutItem;
	private MainPanel mainPanel;
	private Database db;
	private Ansatt User = null;
	private Ansatt kalenderEier = null;
	private GregorianCalendar tid;
	private boolean loggedIn = false;

	/*
	 * Contructor for the window/frame. Sets up everything.
	 */	
	public ProgramFrame() throws SQLException {
		init();
		initMenu();
		db = new Database();
		mainPanel = new MainPanel(this);
		tid = new GregorianCalendar();
		disableComponents();
		add(mainPanel);
		mainPanel.setVisible(true);
		setFocusable(true);
	}
	
	/*
	 * Get-method for the database object.
	 */
	
	public Database getDB(){
		return db;
	}
	
	/*
	 * Get-method for mainPanel.  
	 */
	
	public MainPanel getMainPanel(){
		return mainPanel;
	}
	
	/*
	 * Actionlistener method for log in and log out buttons.
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
			mainPanel.byttTilWeekView();
			update();
			loggedIn(false);
		}
	}
	
	/*
	 * Gjør at alle komponentene i programmet kan brukes.
	*/
	public void enableComponents(){
		logoutItem.setEnabled(true);
		loginItem.setEnabled(false);
		
		mainPanel.enableComponents();
		
		
	}
	
	/*
	 * Disables some of the components in the program.
	 */
	
	public void disableComponents(){
		logoutItem.setEnabled(false);
		loginItem.setEnabled(true);
		
		mainPanel.disableComponents();
		
	}
	
	/*
	 * Initializes the menubar at the top of the program.
	 */
	public void initMenu(){
		menubar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		
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
		
		menubar.add(fileMenu);
		
		setJMenuBar(menubar);
	}
	
	/*
	 * Sets the owner of the current calendar shown. 
	 */
	public void setKalenderEier(Ansatt eier){
		this.kalenderEier=eier;
	}
	
	/*
	 * Gets the owner of the current calendar shown.
	 */
	public Ansatt getKalenderEier(){
		return kalenderEier;
	}
	
	/*
	 * Sets the user's username.
	 */
	public void setUser(String brukernavn){
		try {
			this.User=db.getBestemtAnsatt(brukernavn);
			kalenderEier = db.getBestemtAnsatt(brukernavn);
			mainPanel.setCurrUser(this.User.getBrukernavn());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Gets the user.
	 */
	public Ansatt getUser(){
		if (this.User==null){
			return null;
		}
		return this.User;
	}
	
	/*
	 * Initializes the settings of the window.
	 */
	public void init(){
		setMinimumSize(windowSize);
		setPreferredSize(windowSize);
		setMaximumSize(windowSize);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
				System.exit(0);
			}
		});
	}
	
	/*
	 * Main method of the program. Opens the window, etc.
	 */
	public static void main(String[] args){
		Thread alarmThread, swingThread;
		swingThread = new SwingThread();
		alarmThread = new AlarmThread((SwingThread)swingThread);
		swingThread.start();
		alarmThread.start();
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

	/*
	 * Changes the menu buttons for log in and log out when you log in and log out.
	 */
	public void loggedIn(boolean bool) {
		System.out.println("Logged inn " + bool);
		if(bool){
			loginItem.setEnabled(false);
			logoutItem.setEnabled(true);
			loggedIn = true;
		}else{
			loginItem.setEnabled(true);
			logoutItem.setEnabled(false);
			loggedIn = false;
		}
	}
	
	/*
	 * Gets the loggedIn bool-variable
	 */
	public boolean getLoggedIn(){
		return loggedIn;
	}
}

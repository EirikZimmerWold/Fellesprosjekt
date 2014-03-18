package Fellesprosjektet;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public class weekView extends JPanel implements ActionListener{
	
	private ProgramFrame frame;
	private String panelName = "Kalender";
	private JButton nyAvtaleKnapp, nextButton, backButton;
	private Calendar cal;

	private weekdayPanel wkdPanel, wkdPanel2, wkdPanel3, wkdPanel4, wkdPanel5, wkdPanel6, wkdPanel7;
	
	public weekView(ProgramFrame frame){
		this.frame = frame;
		
		initDesign();

		cal = Calendar.getInstance();
		
		cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);

		// get start of this week in milliseconds
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		System.out.println("Start of this week:       " + cal.getTime());
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date firstDay = cal.getTime();
		String fDay = df.format(firstDay);
		System.out.println(fDay);
		String[] dates = fDay.split("/");
		
		int date = Integer.parseInt(dates[0]); //date of this weeks monday
		int month = Integer.parseInt(dates[1]);		//current month
		int year = Integer.parseInt(dates[2]);		//current year
		
		//System.out.println(thisWeek);
		generateThisWeek(date, month, year);
		
	}
	
	public String getDate(Calendar cal){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date firstDay = cal.getTime();
		String fDay = df.format(firstDay);
		String[] dates = fDay.split("/");
		
		int monDate = Integer.parseInt(dates[0]); //date of this weeks monday
		int month = Integer.parseInt(dates[1]);		//current month
		int year = Integer.parseInt(dates[2]);		//current year
		
		return monDate+"/"+month+"/"+year;
	}
	
	public void generateThisWeek(int monDate, int month, int year){
		System.out.println("dato: " + monDate + "/" + month + "/" + year);
		cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, monDate);
		System.out.println(cal.getTime());
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		System.out.println(cal.getTime());
		
		wkdPanel.setDate(getDate(cal));
		cal.add(Calendar.DATE, 1);
		wkdPanel2.setDate(getDate(cal));
		cal.add(Calendar.DATE, 1);
		wkdPanel3.setDate(getDate(cal));
		cal.add(Calendar.DATE, 1);
		wkdPanel4.setDate(getDate(cal));
		cal.add(Calendar.DATE, 1);
		wkdPanel5.setDate(getDate(cal));
		cal.add(Calendar.DATE, 1);
		wkdPanel6.setDate(getDate(cal));
		cal.add(Calendar.DATE, 1);
		wkdPanel7.setDate(getDate(cal));
	}
	
	public String getPanelName(){
		return panelName;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(nyAvtaleKnapp)){
			nyAvtale na;
			try {
				na = new nyAvtale();
				na.pack();
				na.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				na.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		}
		if(e.getSource().equals(backButton)){
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
			cal.add(Calendar.DATE, -7);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date firstDay = cal.getTime();
			String fDay = df.format(firstDay);
			System.out.println(fDay);
			String[] dates = fDay.split("/");
		    int date = Integer.parseInt(dates[0]); 
		    int month = Integer.parseInt(dates[1]);         
		    int year = Integer.parseInt(dates[2]);          
		    generateThisWeek(date, month, year);
		}if(e.getSource().equals(nextButton)){
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
			cal.add(Calendar.DATE, 7);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date firstDay = cal.getTime();
			String fDay = df.format(firstDay);
			System.out.println(fDay);
			String[] dates = fDay.split("/");
		    int date = Integer.parseInt(dates[0]); 
		    int month = Integer.parseInt(dates[1]);         
		    int year = Integer.parseInt(dates[2]);          
		    generateThisWeek(date, month, year);    
    	}
	}

	public void initDesign(){
		wkdPanel = new weekdayPanel("Mandag", frame);
		wkdPanel2 = new weekdayPanel("Tirsdag", frame);
		wkdPanel3 = new weekdayPanel("Onsdag", frame);
		wkdPanel4 = new weekdayPanel("Torsdag", frame);
		wkdPanel5 = new weekdayPanel("Fredag", frame);
		wkdPanel6= new weekdayPanel("Lørdag", frame);
		wkdPanel7= new weekdayPanel("Søndag", frame);
		nyAvtaleKnapp = new JButton("Ny avtale");
		nyAvtaleKnapp.addActionListener(this);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		add(nyAvtaleKnapp,c);
		backButton = new JButton("<");
		c.gridx=0;
		c.gridy=2;
		add(backButton,c);
		c.gridx=1;
		c.gridy=2;
		add(wkdPanel,c);
		c.gridx=2;
		c.gridy=2;
		add(wkdPanel2,c);
		c.gridx=3;
		c.gridy=2;
		add(wkdPanel3,c);
		c.gridx=4;
		c.gridy=2;
		add(wkdPanel4,c);
		c.gridx=5;
		c.gridy=2;
		add(wkdPanel5,c);
		c.gridx=6;
		c.gridy=2;
		add(wkdPanel6,c);
		c.gridx=7;
		c.gridy=2;
		add(wkdPanel7,c);
		c.gridx=8;
		c.gridy=2;
		
		nextButton = new JButton(">");
		c.gridx=9;
		c.gridy=2;
		add(nextButton,c);
		
		nextButton.addActionListener(this);
		backButton.addActionListener(this);
	}
}

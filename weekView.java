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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public class weekView extends JPanel implements ActionListener{
	
	private ProgramFrame frame;
	private String panelName = "Kalender";
	private JButton nyAvtaleKnapp;
	
	public weekView(ProgramFrame frame){
		this.frame = frame;
		
		Calendar cal = Calendar.getInstance();
		
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
		
		int monDate = Integer.parseInt(dates[0]); //date of this weeks monday
		int month = Integer.parseInt(dates[1]);		//current month
		int year = Integer.parseInt(dates[2]);		//current year
		
		System.out.println(dates[0]);
		
		//System.out.println(thisWeek);
		generateThisWeek(monDate, month, year);
		
	}
	
	
	public void generateThisWeek(int monDate, int month, int year){
		
		weekdayPanel wkdPanel = new weekdayPanel("Mandag", Integer.toString(monDate) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		weekdayPanel wkdPanel2 = new weekdayPanel("Tirsdag", Integer.toString(monDate +1) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		weekdayPanel wkdPanel3 = new weekdayPanel("Onsdag", Integer.toString(monDate + 2) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		weekdayPanel wkdPanel4 = new weekdayPanel("Torsdag", Integer.toString(monDate + 3) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		weekdayPanel wkdPanel5 = new weekdayPanel("Fredag", Integer.toString(monDate + 4) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		weekdayPanel wkdPanel6 = new weekdayPanel("L�rdag", Integer.toString(monDate + 5) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		weekdayPanel wkdPanel7 = new weekdayPanel("S�ndag", Integer.toString(monDate + 6) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		
		nyAvtaleKnapp = new JButton("Ny avtale");
		nyAvtaleKnapp.addActionListener(this);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		add(nyAvtaleKnapp,c);
		JButton backButton = new JButton("<");
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
		
		JButton nextButton = new JButton(">");
		c.gridx=9;
		c.gridy=2;
		add(nextButton,c);
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
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			na.pack();
			na.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			na.setVisible(true);		}
	}
}

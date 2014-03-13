package Fellesprosjektet;

import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class weekView extends JPanel{
	
	public weekView(){
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
		
		

		setLayout(new FlowLayout());
		
		JButton backButton = new JButton("<");
		add(backButton);
		
		add(wkdPanel);
		add(wkdPanel2);
		add(wkdPanel3);
		add(wkdPanel4);
		add(wkdPanel5);
		add(wkdPanel6);
		add(wkdPanel7);
		
		JButton nextButton = new JButton(">");
	
		add(nextButton);
	}
}

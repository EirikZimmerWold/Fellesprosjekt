package Fellesprosjektet;

import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;


public class weekView {
	public static void main(String[] args){
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
	
	
	public static void generateThisWeek(int monDate, int month, int year){
		JFrame frame = new JFrame("Week");
		frame.setSize(1000, 1000);

		weekdayPanel wkdPanel = new weekdayPanel("Mandag", Integer.toString(monDate) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		weekdayPanel wkdPanel2 = new weekdayPanel("Tirsdag", Integer.toString(monDate +1) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		weekdayPanel wkdPanel3 = new weekdayPanel("Onsdag", Integer.toString(monDate + 2) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		weekdayPanel wkdPanel4 = new weekdayPanel("Torsdag", Integer.toString(monDate + 3) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		weekdayPanel wkdPanel5 = new weekdayPanel("Fredag", Integer.toString(monDate + 4) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		weekdayPanel wkdPanel6 = new weekdayPanel("Lørdag", Integer.toString(monDate + 5) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		weekdayPanel wkdPanel7 = new weekdayPanel("Søndag", Integer.toString(monDate + 6) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
		
		

		frame.setLayout(new FlowLayout());
		
		JButton backButton = new JButton("<");
		frame.getContentPane().add(backButton);
		
		frame.getContentPane().add(wkdPanel);
		frame.getContentPane().add(wkdPanel2);
		frame.getContentPane().add(wkdPanel3);
		frame.getContentPane().add(wkdPanel4);
		frame.getContentPane().add(wkdPanel5);
		frame.getContentPane().add(wkdPanel6);
		frame.getContentPane().add(wkdPanel7);
		
		JButton nextButton = new JButton(">");
	
		frame.getContentPane().add(nextButton);
		
		
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

package Fellesprosjektet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class PeriodiskSjekk {
	private Timer t;
	private ArrayList<String> alarmer;
	private ProgramFrame frame;
	private Database db;
	
	  // hent alle alarms
	 public PeriodiskSjekk(final ProgramFrame frame) throws SQLException{
		 this.frame = frame;
		 db = frame.getDB();
		 
		 alarmer = db.getAlarmer(frame.getUser().getBrukernavn());
		 t = new Timer();
		 
		 
		 
		 
		 t.scheduleAtFixedRate(
				 new TimerTask()
				 {
					 public void run()
					 {
						 
						 //Trigger alarms
						 String time = getTime();
						 System.out.println("kjørte");
						 for(String a : alarmer){
							 if(a.equals(time)) { 
								 try {
									frame.kjørAlarm(a);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							 }
						 }
						 
					 }
				 },
				 0,20000); // Repeat each 60 seconds
	 }
	 private String getTime(){		 
		 return new SimpleDateFormat("dd-HH:mm").format(Calendar.getInstance().getTime());
	 }
	 public void avslutt(){
		 t.cancel();
	 }
	 
	 

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

}

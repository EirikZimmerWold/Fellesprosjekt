package Fellesprosjektet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class PeriodiskSjekk {
	private Timer t;
	private ResultSet alarmer;
	private ProgramFrame frame;
	private Database db;
	
	  // hent alle alarms
	 public PeriodiskSjekk(String Brukernavn, ProgramFrame frame) throws SQLException{
		 this.frame = frame;
		 db = frame.getDB();
		 
		 alarmer = db.getAlarmer(Brukernavn);
		 t = new Timer();
		 
		 t.scheduleAtFixedRate(
				 new TimerTask()
				 {
					 public void run()
					 {
						 // Update announcement counter
						 parent.updateAnnounchementCounter();
						 
						 //Trigger alarms
						 String time = getTime();
						 String date = getDate();
						 
						 for(Alarm a : alarmer){
							 if(a.getTime().equals(time) && a.getDate().equals(date)) { 
								 new AlarmPanel(parent, user, a);
							 }
						 }
						 
					 }
				 },
				 0,
				 20000); // Repeat each 60 seconds
	 }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

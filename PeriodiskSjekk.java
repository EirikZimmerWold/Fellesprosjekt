package Fellesprosjektet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PeriodiskSjekk {
	private Timer t;
	private ArrayList<String> alarmer;
	private ProgramFrame frame;
	private Database db;
	private String message = "";
	final JFrame popUpWithMessage = new JFrame();
	
	  // hent alle alarms
	 public PeriodiskSjekk(ProgramFrame frame, ArrayList<String> aa){
		 this.frame = frame;
		 db = frame.getDB();
		 System.out.println("startet");
		 alarmer = aa;
				 
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
									kjørAlarm(a);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							 }
						 }
						 
					 }
				 },
				 0,200); // Repeat each 60 seconds
	 }
	 private String getTime(){		 
		 return new SimpleDateFormat("dd-HH:mm").format(Calendar.getInstance().getTime());
	 }
	 public void avslutt(){
		 t.cancel();
	 }
	 
	 
	public void kjørAlarm(String alarmTid) throws SQLException{
		int avtaleID = db.finnAvtale(alarmTid, frame.getUser().getBrukernavn());
		message = "ALARM! alarm for avtalen som starter: " + db.getBestemtAvtale(avtaleID);
		JOptionPane.showMessageDialog(popUpWithMessage, message);
	}
	 
	 

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

}

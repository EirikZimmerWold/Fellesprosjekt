package Fellesprosjektet;

public class PeriodiskSjekk {
	private Timer t;
	private ResultSet alarmer;
	
	  // hent alle alarms
	 public PeriodiskSjekk(String Brukernavn){
		 
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
						 
						 for(Alarm a : alarms){
							 if(a.getTime().equals(o) && a.getDate().equals(date)) { 
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

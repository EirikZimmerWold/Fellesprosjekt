package Fellesprosjektet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class AlarmThread extends Thread implements Runnable{
	
	private ProgramFrame frame;
	SwingThread swingThread;
	Database db;
	
	private String[] stringOfMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

	public AlarmThread(SwingThread swingThread){
		this.swingThread = swingThread;
		db = new Database();
		String time = getTime();
		System.out.println("timetimetime:" + time);
	}

	@Override
	public void run(){
		try{
			String time;
			swingThread.join();
			frame = swingThread.getFrame();
			frame.revalidate();
			while(true){
				time = getTime();
				String[] currTime = time.split("-");
				System.out.println("ALarmThread: "+  frame.getLoggedIn());
				if(frame.getLoggedIn()){
					ArrayList<String> alarmList = db.getAlarmer(frame.getUser().getBrukernavn());
					System.out.println("AlarmThread, list:" + alarmList.size());
					for(String alarm : alarmList){
						String[] alarmTime = alarm.split("-");
						if(checkAlarm(currTime, alarmTime)){
							fireAlarmWarning(alarmTime[0], alarmTime[1]);
						}
					}
				}
				
				try{
					Thread.sleep(59000);		
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	 private String getTime(){
		 return new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(Calendar.getInstance().getTime());
	 }
	 
	 public boolean checkAlarm(String[] currTime, String[] alarmTime){
		 if(Integer.parseInt(currTime[0])>Integer.parseInt(alarmTime[2])){
			 return true;
		 }else if(Integer.parseInt(currTime[1])>getMonth(alarmTime[3])){
			 return true;
		 }else if(Integer.parseInt(currTime[2])>Integer.parseInt(alarmTime[4])){
			 return true;
		 }else if(Integer.parseInt(currTime[3])>Integer.parseInt(alarmTime[5])){
			 return true;
		 }else if(Integer.parseInt(currTime[4])>=Integer.parseInt(alarmTime[6])){
			 return true;
		 }
		 return false;
	 }
	 
	 public int getMonth(String month){
		 int monthvalue = 0;
		 for(int i = 1;i<=stringOfMonths.length;i++){
			 if(month.equals(stringOfMonths[i-1])){
				 monthvalue = i;
			 }
		 }
		 return monthvalue;
	 }
	 
	 private void fireAlarmWarning(String varselId, String avtaleId){
		 String message= "";
		 try{
			 message = "ALARM! alarm for avtalen som starter: " + db.getBestemtAvtale(Integer.parseInt(avtaleId));
			 db.fjerneAlarm(Integer.parseInt(varselId));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 JOptionPane.showMessageDialog(frame, message);
	 }
}
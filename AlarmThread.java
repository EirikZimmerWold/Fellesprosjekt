package Fellesprosjektet;

public class AlarmThread extends Thread implements Runnable{
	
	private ProgramFrame frame;
	SwingThread swingThread;
	Database db;

	public AlarmThread(SwingThread swingThread){
		this.swingThread = swingThread;
		frame = swingThread.getFrame();
	}

	@Override
	public void run(){
		try{
			
			swingThread.join();
			while(true){
				db = frame.getDB();
				
				
				if(frame.getLoggedIn()){
					
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
}
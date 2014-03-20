package Fellesprosjektet;

public class SwingThread extends Thread implements Runnable{
	
	private ProgramFrame pf;

	@Override
	public void run(){
		try{
			pf = new ProgramFrame();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public ProgramFrame getFrame(){
		return pf;
	}
}
package Fellesprosjektet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class alarmController extends JFrame implements ActionListener {
	
/*	public static void main(String[] arg0){
		JFrame frame = new JFrame();
		frame.setSize(400, 200);
		alarmController ac = new alarmController("2014-March-13-16:10");
		frame.add(ac.view);
		frame.setVisible(true);

	}*/

	// Fields
		private alarmModel model;
		private alarmView view;
		private Database db;
		private ProgramFrame frame;
		private Avtale avtale;
	
	// Constructor
		alarmController(Avtale avtalen,ProgramFrame frame){
			this.frame = frame;
			db = frame.getDB();
			this.avtale = avtalen;
			model = new alarmModel(avtalen.getStartTid());
			view = new alarmView();
			view.addButtonsListener(this);
			add(view);
			this.updateUI();			
		}

	// Methods
		public void updateUI(){
			view.setStartTimeLabelText(model.getDate());
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == view.getConfirmButton()) {
				// will return the selected interval's value.
				String[] valgt = view.getSelectedInterval().split(" ");
				int minutt =model.getMinute();
				int time =model.getHour();
				int dag = model.getDay();
				String tid ="";
				if (valgt[1].equals("min")){
					minutt -= Integer.parseInt(valgt[0]);
					if (minutt < 0){
						minutt += 60;
						time -= 1;
						if (time <= 0){		
							time +=23;
							dag -= 1;
						}
					}
				}
				else if(valgt[1].equals("hours")){
					time -= Integer.parseInt(valgt[0]);
					if (time <= 0){		
						time +=23;
						dag -= 1;
					}
				}
				else {
					dag -= Integer.parseInt(valgt[0]);
					}
				if (dag<=0){
					dag=1;
				}
				tid = (dag + "-"+ time + "-" + minutt);
				try {
					db.setAlarm(frame.getUser().getBrukernavn(), tid, avtale.getId());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
			if (e.getSource() == view.getCancelButton()) {
				// Cancel is clicked
				dispose();
			}
		}
}

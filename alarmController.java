package Alarm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class alarmController implements ActionListener {
	
	public static void main(String[] arg0){
		JFrame frame = new JFrame();
		frame.setSize(400, 200);
		alarmController ac = new alarmController("2014-March-13-16:10");
		frame.add(ac.view);
		frame.setVisible(true);

	}

	// Fields
		private alarmModel model;
		private alarmView view;
	
	// Constructor
		alarmController(String date){
			model = new alarmModel(date);
			System.out.println(model.getDate());
			System.out.println(model.getDay());
			System.out.println(model.getHour());
			view = new alarmView();
			view.addButtonsListener(this);
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
				System.out.println(view.getSelectedInterval());
			}
			if (e.getSource() == view.getCancelButton()) {
				// Cancel is clicked
				System.out.println("Cancel");
			}
		}
}

package Fellesprosjektet;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


public class weekView {
	public static void main(String[] args){
		generateWeek();
		
	}
	
	
	public static void generateWeek(){
		JFrame frame = new JFrame("Week");
		frame.setSize(700, 1500);
		
		weekdayPanel wkdPanel = new weekdayPanel("Mandag", "01.01.2014");
		weekdayPanel wkdPanel2 = new weekdayPanel("Tirsdag", "02.01.2014");
		weekdayPanel wkdPanel3 = new weekdayPanel("Onsdag", "03.01.2014");
		weekdayPanel wkdPanel4 = new weekdayPanel("Torsdag", "04.01.2014");
		weekdayPanel wkdPanel5 = new weekdayPanel("Fredag", "05.01.2014");
		weekdayPanel wkdPanel6 = new weekdayPanel("Lørdag", "06.01.2014");
		weekdayPanel wkdPanel7 = new weekdayPanel("Søndag", "07.01.2014");
		
		wkdPanel.setSize(100, 100);
		

		frame.setLayout(new FlowLayout());
		frame.getContentPane().add(wkdPanel);
		frame.getContentPane().add(wkdPanel2);
		frame.getContentPane().add(wkdPanel3);
		frame.getContentPane().add(wkdPanel4);
		frame.getContentPane().add(wkdPanel5);
		frame.getContentPane().add(wkdPanel6);
		frame.getContentPane().add(wkdPanel7);
		
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

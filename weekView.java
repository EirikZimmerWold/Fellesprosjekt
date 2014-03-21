package Fellesprosjektet;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public class weekView extends JPanel implements ActionListener, ItemListener{
	
	private ProgramFrame frame;
	private String panelName = "Kalender";
	private JButton nyAvtaleKnapp, nextButton, backButton;
	private Calendar cal;
	private JComboBox kalendere;
	private JLabel kalendereL;
	private JButton egenKalender;
	private Database db;
	private String Listavtaler = "";
	private Ansatt Eier;

	private weekdayPanel wkdPanel, wkdPanel2, wkdPanel3, wkdPanel4, wkdPanel5, wkdPanel6, wkdPanel7;
	
	public weekView(ProgramFrame frame) throws SQLException{
		this.frame = frame;
		
		initDesign();

		cal = Calendar.getInstance();
		
		cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);

		// get start of this week in milliseconds
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date firstDay = cal.getTime();
		String fDay = df.format(firstDay);
		String[] dates = fDay.split("/");
		
		int date = Integer.parseInt(dates[0]); //date of this weeks monday
		int month = Integer.parseInt(dates[1]);		//current month
		int year = Integer.parseInt(dates[2]);		//current year
		
		generateThisWeek(date, month, year);
		
	}
	
	public String getDate(Calendar cal){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date firstDay = cal.getTime();
		String fDay = df.format(firstDay);
		String[] dates = fDay.split("/");
		
		int monDate = Integer.parseInt(dates[0]); //date of this weeks monday
		int month = Integer.parseInt(dates[1]);		//current month
		int year = Integer.parseInt(dates[2]);		//current year
		
		return monDate+"/"+month+"/"+year;
	}
	
	public void generateThisWeek(int monDate, int month, int year) throws SQLException{
		cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, monDate);
		cal.getTime();
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		cal.getTime();
		
		
		Eier = frame.getKalenderEier();
		String tider = "";
		if (Eier != null){
			Listavtaler = db.avtalerPersonErMed2(Eier);
			String [] delt = Listavtaler.split("-");
			for (String verdi:delt){
				if (verdi != ""){
					int avID = Integer.parseInt(verdi);
					Avtale avtalen=db.getBestemtAvtale(avID);
					tider += avtalen.getStartTid() + "-"+avID + "#";
				}
			}
		}
		
		
		wkdPanel.setDate(getDate(cal));
		cal.add(Calendar.DATE, 1);
		wkdPanel.leggeTilAvtale(Eier,tider);
		wkdPanel2.setDate(getDate(cal));
		cal.add(Calendar.DATE, 1);
		wkdPanel2.leggeTilAvtale(Eier,tider);
		wkdPanel3.setDate(getDate(cal));
		cal.add(Calendar.DATE, 1);
		wkdPanel3.leggeTilAvtale(Eier,tider);
		wkdPanel4.setDate(getDate(cal));
		cal.add(Calendar.DATE, 1);
		wkdPanel4.leggeTilAvtale(Eier,tider);
		wkdPanel5.setDate(getDate(cal));
		cal.add(Calendar.DATE, 1);
		wkdPanel5.leggeTilAvtale(Eier,tider);
		wkdPanel6.setDate(getDate(cal));
		cal.add(Calendar.DATE, 1);
		wkdPanel6.leggeTilAvtale(Eier,tider);
		wkdPanel7.setDate(getDate(cal));
		wkdPanel7.leggeTilAvtale(Eier,tider);
		
	}
	
	public String getPanelName(){
		return panelName;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(nyAvtaleKnapp)){
			nyAvtale na;
			try {
				na = new nyAvtale(frame);
				na.pack();
				na.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				na.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		}
		if(e.getSource().equals(backButton)){
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
			cal.add(Calendar.DATE, -7);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date firstDay = cal.getTime();
			String fDay = df.format(firstDay);
			String[] dates = fDay.split("/");
		    int date = Integer.parseInt(dates[0]); 
		    int month = Integer.parseInt(dates[1]);         
		    int year = Integer.parseInt(dates[2]);          
		    try {
				generateThisWeek(date, month, year);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}if(e.getSource().equals(nextButton)){
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
			cal.add(Calendar.DATE, 7);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date firstDay = cal.getTime();
			String fDay = df.format(firstDay);
			String[] dates = fDay.split("/");
		    int date = Integer.parseInt(dates[0]); 
		    int month = Integer.parseInt(dates[1]);         
		    int year = Integer.parseInt(dates[2]);          
		    try {
				generateThisWeek(date, month, year);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}    
    	}
		if(e.getSource().equals(egenKalender)){
			frame.setKalenderEier(frame.getUser());
			setKalender();
			frame.enableComponents();
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(kalendere)){
			frame.setKalenderEier((Ansatt) kalendere.getSelectedItem());
			Ansatt ansatt = (Ansatt)kalendere.getSelectedItem();
			if(ansatt != null){	
				if(!frame.getUser().getNavn().equals(ansatt.getNavn())){
					frame.disableComponents();
					this.backButton.setEnabled(true);
					this.nextButton.setEnabled(true);
					this.egenKalender.setEnabled(true);
					this.kalendere.setEnabled(true);
				}else{
					frame.enableComponents();
				}
			}
			setKalender();
		}
	}
	
	public void setKalender(){
		if(kalendere.getSelectedItem() != frame.getKalenderEier()){
			kalendere.setSelectedItem(frame.getKalenderEier());
		}
		kalendere.updateUI();
	}

	public void initDesign() throws SQLException{
		wkdPanel = new weekdayPanel("Mandag", frame);
		wkdPanel.setPreferredSize((new Dimension(175,500)));
		wkdPanel.setMaximumSize((new Dimension(175,500)));
		wkdPanel.setMinimumSize((new Dimension(175,500)));
		wkdPanel2 = new weekdayPanel("Tirsdag", frame);
		wkdPanel2.setPreferredSize((new Dimension(175,500)));
		wkdPanel2.setMaximumSize((new Dimension(175,500)));
		wkdPanel2.setMinimumSize((new Dimension(175,500)));
		wkdPanel3 = new weekdayPanel("Onsdag", frame);
		wkdPanel3.setPreferredSize((new Dimension(175,500)));
		wkdPanel3.setMaximumSize((new Dimension(175,500)));
		wkdPanel3.setMinimumSize((new Dimension(175,500)));
		wkdPanel4 = new weekdayPanel("Torsdag", frame);
		wkdPanel4.setPreferredSize((new Dimension(175,500)));
		wkdPanel4.setMaximumSize((new Dimension(175,500)));
		wkdPanel4.setMinimumSize((new Dimension(175,500)));
		wkdPanel5 = new weekdayPanel("Fredag", frame);
		wkdPanel5.setPreferredSize((new Dimension(175,500)));
		wkdPanel5.setMaximumSize((new Dimension(175,500)));
		wkdPanel5.setMinimumSize((new Dimension(175,500)));
		wkdPanel6= new weekdayPanel("Lørdag", frame);
		wkdPanel6.setPreferredSize((new Dimension(175,500)));
		wkdPanel6.setMaximumSize((new Dimension(175,500)));
		wkdPanel6.setMinimumSize((new Dimension(175,500)));
		wkdPanel7= new weekdayPanel("Søndag", frame);
		wkdPanel7.setPreferredSize((new Dimension(175,500)));
		wkdPanel7.setMaximumSize((new Dimension(175,500)));
		wkdPanel7.setMinimumSize((new Dimension(175,500)));
		nyAvtaleKnapp = new JButton("Ny avtale");
		nyAvtaleKnapp.addActionListener(this);
		
		db = frame.getDB();
		egenKalender = new JButton("g� til egen kalender");
		egenKalender.addActionListener(this);
		kalendereL = new JLabel("se andres Kalendre: ");
		kalendere = new JComboBox<Ansatt>();
		DefaultListModel<Ansatt> folk = db.getAlleAnsatte();
		for(int a =0; a < folk.getSize(); a++){
			kalendere.addItem((folk.getElementAt(a)));
		}
		kalendere.addItemListener(this);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		add(nyAvtaleKnapp,c);
		c.gridx = 2;
		add(kalendereL, c);
		c.gridx = 3;
		add(kalendere, c);
		c.gridx = 4;
		add(egenKalender,c);
		backButton = new JButton("<");
		c.gridx=0;
		c.gridy=2;
		add(backButton,c);
		c.gridx=1;
		c.gridy=2;
		add(wkdPanel,c);
		c.gridx=2;
		c.gridy=2;
		add(wkdPanel2,c);
		c.gridx=3;
		c.gridy=2;
		add(wkdPanel3,c);
		c.gridx=4;
		c.gridy=2;
		add(wkdPanel4,c);
		c.gridx=5;
		c.gridy=2;
		add(wkdPanel5,c);
		c.gridx=6;
		c.gridy=2;
		add(wkdPanel6,c);
		c.gridx=7;
		c.gridy=2;
		add(wkdPanel7,c);
		c.gridx=8;
		c.gridy=2;
		
		nextButton = new JButton(">");
		c.gridx=9;
		c.gridy=2;
		add(nextButton,c);
		
		nextButton.addActionListener(this);
		backButton.addActionListener(this);
	}

}

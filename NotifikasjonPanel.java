package Fellesprosjektet;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class NotifikasjonPanel extends JPanel implements ActionListener{
	private JLabel lederLabel, startTidLabel, sluttTidLabel, leder, startTid, sluttTid, status;
	private JButton bekreftButton, avslaaButton, infoButton;
	protected Notifikasjon notifikasjon;
	private GridBagConstraints gbc;
	private PropertyChangeSupport pcs;
	public final static String INFO_PROPERTY="infoButton";
	private Database db;
	private ProgramFrame frame;
	
	private NotifikasjonListPanel notifListPanel;
	
	public NotifikasjonPanel(NotifikasjonListPanel notifListPanel, ProgramFrame frame, Avtale avtale){
		this.notifListPanel = notifListPanel;
		db=new Database();
		pcs=new PropertyChangeSupport(this);
		notifikasjon=new Notifikasjon(avtale);
		this.frame=frame;
		
		gbc=new GridBagConstraints();
		setLayout(new GridBagLayout());
		gbc.insets=new Insets(5,5,5,5);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		
		lederLabel=new JLabel("Leder: ");
		gbc.gridx=0;
		gbc.gridy=1;
		this.add(lederLabel,gbc);
		
		leder=new JLabel(notifikasjon.getLeder());
		gbc.gridx=1;
		gbc.gridy=1;
		this.add(leder,gbc);
		
		startTidLabel=new JLabel("Start tid: ");
		gbc.gridx=0;
		gbc.gridy=2;
		this.add(startTidLabel, gbc);
		
		startTid=new JLabel(notifikasjon.getStartTid());
		gbc.gridx=1;
		gbc.gridy=2;
		this.add(startTid,gbc);
		
		sluttTidLabel=new JLabel("Slutt tid: ");
		gbc.gridx=0;
		gbc.gridy=3;
		this.add(sluttTidLabel, gbc);
		
		sluttTid=new JLabel(notifikasjon.getSluttTid());
		gbc.gridx=1;
		gbc.gridy=3;
		this.add(sluttTid,gbc);
		
		bekreftButton=new JButton("Bekreft");
		bekreftButton.addActionListener(this);
		gbc.gridx=2;
		gbc.gridy=1;
		this.add(bekreftButton, gbc);
		
		avslaaButton=new JButton(" Avslaa ");
		avslaaButton.addActionListener(this);
		gbc.gridx=2;
		gbc.gridy=2;
		this.add(avslaaButton, gbc);
		
		infoButton=new JButton("    Info    ");
		infoButton.addActionListener(this);
		gbc.gridx=2;
		gbc.gridy=3;
		this.add(infoButton, gbc);
		
		status=new JLabel(" ");
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=3;
		this.add(status,gbc);
	}
	
	public void erEndret(){ //sendes til alle 
		status.setText("Eventen har blitt endret");
	}
	
	public void invitert(){ //sendes til den ansatte som har blitt invitert
		status.setText("Du har blitt invitert til en event");
	}
	
	public void enAnsattHarAvslaatt(){
		status.setText(" har avslått avtalen");//skal legge til navn som har avslått
	}
	
	public void erAlarm(){
		status.setText("Du har en avtale om ");//skal legge til tid for mote
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==bekreftButton){
			try {
				db.setBekreftetStatus(frame.getUser() , notifikasjon.getAvtale(), 1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(arg0.getSource()==avslaaButton){
			try {
				db.setBekreftetStatus(frame.getUser(), notifikasjon.getAvtale(), 0);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if (arg0.getSource()==infoButton){
			pcs.firePropertyChange(INFO_PROPERTY,"oldvalue","newvalue"); //firepropertyChange til notifikasjonListPanel
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public Notifikasjon getNotifikasjon(){
		return this.notifikasjon;
	}
}

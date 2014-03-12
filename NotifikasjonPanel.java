package Fellesprosjektet;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class NotifikasjonPanel extends JPanel implements ActionListener{
	private JLabel lederLabel, tidLabel, beskrivelseLabel, leder, tid, beskrivelse;
	private JButton bekreftButton, avslaaButton, infoButton;
	protected Notifikasjon notifikasjon;
	private GridBagConstraints gbc;
	private PropertyChangeSupport pcs;
	public final static String INFO_PROPERTY="infoButton";
	
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		NotifikasjonPanel panel=new NotifikasjonPanel();
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
	
	public NotifikasjonPanel(){ //tar inn avtale/notifikasjon
		pcs=new PropertyChangeSupport(this);
		
		notifikasjon=new Notifikasjon(); //skal fjernes
		
		gbc=new GridBagConstraints();
		setLayout(new GridBagLayout());
		gbc.insets=new Insets(5,5,5,5);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		
		lederLabel=new JLabel("Leder: ");
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(lederLabel,gbc);
		
		leder=new JLabel(notifikasjon.getLeder());
		gbc.gridx=1;
		gbc.gridy=0;
		this.add(leder,gbc);
		
		tidLabel=new JLabel("Tid: ");
		gbc.gridx=0;
		gbc.gridy=1;
		this.add(tidLabel, gbc);
		
		tid=new JLabel(notifikasjon.getTid());
		gbc.gridx=1;
		gbc.gridy=1;
		this.add(tid,gbc);
		
		beskrivelseLabel=new JLabel("Beskrivelse: ");
		gbc.gridx=0;
		gbc.gridy=2;
		this.add(beskrivelseLabel, gbc);
		
		beskrivelse=new JLabel(notifikasjon.getBeskrivelse());
		gbc.gridx=1;
		gbc.gridy=2;
		this.add(beskrivelse,gbc);
		
		bekreftButton=new JButton("Bekreft");
		bekreftButton.addActionListener(this);
		gbc.gridx=2;
		gbc.gridy=0;
		this.add(bekreftButton, gbc);
		
		avslaaButton=new JButton(" Avslaa ");
		avslaaButton.addActionListener(this);
		gbc.gridx=2;
		gbc.gridy=1;
		this.add(avslaaButton, gbc);
		
		infoButton=new JButton("    Info    ");
		infoButton.addActionListener(this);
		gbc.gridx=2;
		gbc.gridy=2;
		this.add(infoButton, gbc);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==bekreftButton){
			System.out.println("Bekreft");
		}else if(arg0.getSource()==avslaaButton){
			System.out.println("Avslaa");
		}else if (arg0.getSource()==infoButton){
			pcs.firePropertyChange(INFO_PROPERTY,"oldvalue","newvalue"); //firepropertyChange til notifikasjonListPanel
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
}

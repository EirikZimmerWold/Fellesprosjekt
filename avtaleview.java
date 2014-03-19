package Fellesprosjektet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//Denne klassen skal dukke opp n�r man velger en avtale i ukeview.
//den henter inn avtaleinfo og lager en liste som har alle deltagerene
//det er 4 knapper. bekreft/Avsla for deltagerne og endre/slette avtale for Leder

public class avtaleview extends JFrame {
	JScrollPane rull;
	JList<Ansatt> Deltagere;
	JButton Bekreft;
	JButton Avsla;
	JButton endre;
	JButton slette;
	JButton alarm;
	GridBagConstraints c;
	DefaultListModel<Ansatt> model;
	avtaleinfo avt;
	JLabel DeltagereL;
	Avtale avtale;
	Database db;
	ProgramFrame frame;
	
	//tar inn avtalen man vil se p�
	public avtaleview(Avtale avtalen, ProgramFrame frame) throws SQLException {
		this.frame = frame; 
		avtale = avtalen;
		Deltagere = new JList<Ansatt>();
		DeltagereL = new JLabel("Deltagere: ");
		model = new DefaultListModel<Ansatt>();
		db = new Database();
		model = db.alleDeltagere(avtale.getId());
		for(int i=0;i<model.size();i++){
			Ansatt ansatt = model.get(i);
			ansatt.setStatus(db.getStatus(avtale.getId(), ansatt.getBrukernavn()));
		}
		Deltagere.setModel(model);
		//maks antall elementer som sees p� en gang
		Deltagere.setVisibleRowCount(5);
		Deltagere.setCellRenderer(new DeltagerRenderer());
		//scroller
		rull = new JScrollPane(Deltagere);
		if(!frame.getUser().getBrukernavn().equals(avtale.getLeder().getBrukernavn())){
			Deltagere.disable();
		}
		rull.setPreferredSize(new Dimension(125,100));
		Bekreft = new JButton("Bekreft");
		Bekreft.addActionListener(new OK());
		Avsla = new JButton("Avsla");
		Avsla.addActionListener(new NEI());
		endre = new JButton("endre event");
		endre.addActionListener(new EDIT());
		slette = new JButton("slett event");
		slette.addActionListener(new SLETT());
		alarm = new JButton("Sett Alarm");
		alarm.addActionListener(new ALARM());
		//panellet med informasjonen om avtalen
		avt = new avtaleinfo();
		avt.settInfo(avtalen);
    	setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets=new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight =3;
		add(avt,c);
		c.gridwidth = 1;
		c.gridy=3;
		add(DeltagereL,c);
		c.gridx = 1;
		add(rull,c);
		c.gridheight=1;
		c.gridx=2;
		add(Bekreft, c);
		c.gridy=4;
		add(Avsla, c);
		c.gridx = 2;
		c.gridy = 0;
		add(endre, c);
		c.gridy =1;
		add(alarm,c);
		c.gridy =2;
		add(slette, c);
	}
	
	public static void main(String[] args) throws SQLException {
		//skal nok fjernes i ferdig produkt, kj�res i en superklasse
		//JFrame frame = new JFrame();
		//JPanel panel = new avtaleview(new Avtale("", "", "", new Rom("test",10), new DefaultListModel<Ansatt>(), new Ansatt("testperson")), new ProgramFrame());
		//frame.add(panel);
		//frame.setVisible(true);
		//frame.setSize(600, 600);
	}
	
	class OK implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(avtale.getLeder().getBrukernavn().equals(frame.getUser().getBrukernavn())){
					Deltagere.getSelectedValue().setStatus(1);
					db.setBekreftetStatus(Deltagere.getSelectedValue(),avtale, 1);
					repaint();
					revalidate();
				}else{
					frame.getUser().setStatus(1);
					db.setBekreftetStatus(frame.getUser(),avtale, 1);
					repaint();
					revalidate();
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//sette valgt deltager til bekreftet status
		}
	}
	class NEI implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(frame.getUser().getBrukernavn().equals(avtale.getLeder().getBrukernavn())){
					Deltagere.getSelectedValue().setStatus(0);
					db.setBekreftetStatus(Deltagere.getSelectedValue(),avtale, 0);
					repaint();
					revalidate();
				}else{
					frame.getUser().setStatus(0);
					db.setBekreftetStatus(frame.getUser(), avtale, 0);
					repaint();
					revalidate();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//sette valgt deltager til Avslatt status
		}
	}
	//sender avtalen til nyAvtale for � endres
	class EDIT implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(frame.getUser().getBrukernavn().equals(avtale.getLeder().getBrukernavn())){
				nyAvtale na;
				try {
					na = new nyAvtale(frame);
					na.pack();
					na.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					na.setVisible(true);
					na.endreAvtale(avtale, frame);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	//sender inn avtalen man vil slette, skal bare skje om man er vert for avtalen
	class SLETT implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (frame.getUser().getBrukernavn().equals(avtale.getLeder().getBrukernavn())){
				try {
					db.fjerneAvtale(avtale);
					dispose();
					frame.update();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//fjerner avtalen. den slettes fra alle deltagere sine kalendre (cascade?)
		}
	}
	//sender inn starttiden til Alarmview s� man kan sette opp alarm
	class ALARM implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			alarmController na;

			na = new alarmController(avtale.getStartTid());
			na.pack();
			na.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			na.setVisible(true);

			//sende til alarmsiden med starttid
			//alarm(avtale.getStartTid)
			 
		}
	}
}

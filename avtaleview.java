package kalender;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

//Denne klassen skal dukke opp når man velger en avtale i ukeview.
//den henter inn avtaleinfo og lager en liste som har alle deltagerene
//det er 4 knapper. bekreft/Avsla for deltagerne og endre/slette avtale for Leder

public class avtaleview extends JPanel {
	JScrollPane rull;
	//må forandres til JList<Ansatt>
	JList<String> Deltagere;
	JButton Bekreft;
	JButton Avsla;
	JButton endre;
	JButton slette;
	GridBagConstraints c;
	DefaultListModel<String> model;
	avtaleinfo avt;
	JLabel DeltagereL;
	
	public avtaleview() {
		//forandre <String> til <Ansatt>
		Deltagere = new JList<String>();
		DeltagereL = new JLabel("Deltagere: ");
		model = new DefaultListModel<String>();
		//legge til Ansatt elementer og en cellrenderer
		model.addElement("Kari");
		model.addElement("Eirik");
		model.addElement("Ida");
		Deltagere.setModel(model);
		//maks antall elementer som sees på en gang
		Deltagere.setVisibleRowCount(5);
		//scroller
		rull = new JScrollPane(Deltagere);
		rull.setPreferredSize(new Dimension(125,100));
		Bekreft = new JButton("Bekreft");
		Bekreft.addActionListener(new OK());
		Avsla = new JButton("Avsla");
		Avsla.addActionListener(new NEI());
		endre = new JButton("endre event");
		endre.addActionListener(new EDIT());
		slette = new JButton("slett event");
		slette.addActionListener(new SLETT());
		//panellet med informasjonen om avtalen
		avt = new avtaleinfo();
		//JPanel panel2 = avt;
    	setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets=new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight =2;
		add(avt,c);
		avt.settInfo("ahaaha");
		c.gridwidth = 1;
		c.gridy=2;
		add(DeltagereL,c);
		c.gridx = 1;
		add(rull,c);
		c.gridheight=1;
		c.gridx=2;
		add(Bekreft, c);
		c.gridy=3;
		add(Avsla, c);
		c.gridx = 2;
		c.gridy = 0;
		add(endre, c);
		c.gridy =1;
		add(slette, c);
	}
	
	public static void main(String[] args) {
		//skal nok fjernes i ferdig produkt, kjøres i en superklasse
		JFrame frame = new JFrame();
		JPanel panel = new avtaleview();
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(600, 600);
	}
	
	class OK implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//sette valgt deltager til bekreftet status
		}
	}
	class NEI implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//sette valgt deltager til Avslatt status
		}
	}
	class EDIT implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//sendes til nyAvtale siden 
			//kjøre metode som setter informasjonen inn i nyAvtale boksene
		}
	}
	class SLETT implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//fjerner avtalen. den slettes fra alle deltagere sine kalendre (cascade?)
		}
	}

}

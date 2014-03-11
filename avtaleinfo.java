package kalender;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;


//denne klassen lager bokser som inneholder informasjonen om avtalen man trykket på
//skal brukes av avtaleview og notifikasjoner klassene
public class avtaleinfo extends JPanel {
	JTextField leder;
	JTextField starttid;
	JTextField sluttid;
	JTextField moterom;
	JTextArea beskrivelse;
	GridBagConstraints c;
	
	JLabel lederL;
	JLabel starttidL;
	JLabel sluttidL;
	JLabel moteromL;
	JLabel beskrivelseL;
	
	
	public avtaleinfo() {
		leder = new JTextField(20);
		leder.setEditable(false);
		lederL = new JLabel("Leder: ");
		starttid = new JTextField(20);
		starttid.setEditable(false);
		starttidL = new JLabel("Mote starter: ");
		sluttid = new JTextField(20);
		sluttid.setEditable(false);
		sluttidL = new JLabel("Mote slutter: ");
		moterom = new JTextField(20);
		moterom.setEditable(false);
		moteromL = new JLabel("Moterom: ");
		beskrivelse = new JTextArea();
		beskrivelse.setColumns(20);
		beskrivelse.setRows(5);
		beskrivelse.setLineWrap(true);
		beskrivelse.setEditable(false);
		beskrivelseL = new JLabel("Beskrivelse: ");
		
    	setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets=new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		add(lederL,c);
		c.gridx = 1;
		add(leder,c);
		c.gridy=1;
		add(starttid,c);
		c.gridx = 0;
		add(starttidL,c);
		c.gridy=2;
		add(sluttidL,c);
		c.gridx = 1;
		add(sluttid,c);
		c.gridy=3;
		add(moterom,c);
		c.gridx = 0;
		add(moteromL,c);
		c.gridy=4;
		add(beskrivelseL,c);
		c.gridx = 1;
		add(beskrivelse,c);
		
	}
	//tar inn avtalen man trykket på og setter informasjonen i boksene
	public void settInfo(/*Avtale A*/String a){
		leder.setText("AHA");
		starttid.setText("15:00 8. Desember 2014");
		beskrivelse.setText(a);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

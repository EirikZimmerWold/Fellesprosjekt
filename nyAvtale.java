package Fellesprosjektet;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class nyAvtale<finnEtRomCheckbox> extends JPanel {
	GridBagConstraints gc;
	Calendar tid;
	final JFrame popUpWithMessage = new JFrame();
	JScrollPane listScrollPane;
	Database db;
	
	//Personer
	Ansatt kari;
	Ansatt ida;
	Ansatt henrik;
	Ansatt gris;
	Ansatt fredrik;
	Ansatt nora;
	Ansatt eline;
	Ansatt Gina;
	Ansatt Fridtjof;
	Ansatt oyvind;
	Ansatt ola;
	Ansatt knut;
	Ansatt eirik;
	Ansatt truls;
	Ansatt vert;
	
	//Starttid
	JLabel startTidLabel;
	JComboBox startTidDag;
	JComboBox startTidMaaned;
	JComboBox startTidAar;
	JTextField startTidKl;
	
	//Sluttid
	JLabel sluttTidLabel;
	JComboBox sluttTidDag;
	JComboBox sluttTidMaaned;
	JComboBox sluttTidAar;
	JTextField sluttTidKl;

	//Beskrivelse
	JLabel beskrivelseLabel;
	JTextField beskrivelseFelt;
	
	//Personer
	JLabel personerLabel;
	JListScroll personerList;
	DefaultListModel personModell;
	
	//Grupper
	JLabel grupperLabel;
	JListScroll grupperList;
	DefaultListModel gruppeModell;
	
	//Deltagere
	JLabel deltagereLabel;
	JListScroll deltagereList;
	DefaultListModel deltagerModell;
	
	//Rom
	ButtonGroup buttongroup;
	
	JLabel mooteromLabel;
	
	JLabel finnEtRomLabel;
	JCheckBox finnEtRomCheckbox;
	JComboBox romBox;
	JLabel beskjedEtterFunnetRomLabel;
	JButton finnPassendeRomButton;
	JLabel beskrivelseAvRomLabel;
	
	JLabel velgEgetRomLabel;
	JCheckBox egetRomCheckbox;
	JTextField egetRomFelt;
	
	//Buttons
	JButton fjernButton;
	JButton inviterPersonButton;
	JButton inviterGruppeButton;
	JButton lagreButton;
	JButton avbrytButton;
	
	public nyAvtale()  {
		
		super(new GridBagLayout());
		gc = new GridBagConstraints();
		tid = new GregorianCalendar();
		db = new Database();
		
		// START-TID
	
	    startTidLabel = new JLabel("Start-tidspunkt:");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 1;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(startTidLabel, gc);
	    
	    // START ÅR
	    
	    startTidAar = new JComboBox();
	    for (int k = tid.getTime().getYear()+1900; k < 2031; k++) {
			startTidAar.addItem(k);
		}
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 2;
	    gc.gridy = 1;
	    add(startTidAar, gc);
	    
	    // START MÅNED
	    
	    startTidMaaned = new JComboBox(Maaned.values());
	    startTidMaaned.setSelectedIndex(tid.getTime().getMonth());
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 3;
	    gc.gridy = 1;
	    add(startTidMaaned, gc);
	    
	    // START DAG
	   
	    startTidDag = new JComboBox();
	    Maaned stm = (Maaned) startTidMaaned.getSelectedItem();
	    //setter antall dager som hoorer til current maaned
		settStartDager();
	    startTidDag.setSelectedIndex(tid.getTime().getDate()-1);
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 4;
	    gc.gridy = 1;
	    add(startTidDag, gc);

	    
	    // START-TID KLOKKE
	    
	    startTidKl = new JTextField();
	    startTidKl.setEditable(true);
	    int minutes = tid.getTime().getMinutes();
	    String minutesWithNull = ""+minutes;
	    if (minutes < 10) {
	    	minutesWithNull = "0"+minutes;
	    }
	    
	    tid.getTime().getMinutes();
	    startTidKl.setText(tid.getTime().getHours()+":" + minutesWithNull);
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 5;
	    gc.gridy = 1;
	    add(startTidKl, gc);
	    
	    
	    // SLUTT-TID
	    
	    sluttTidLabel = new JLabel("Slutt-tidspunkt:");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 2;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(sluttTidLabel, gc);
	    
	    // SLUTT-TID ÅR
	    
	    sluttTidAar = new JComboBox();
	    for (int k = tid.getTime().getYear()+1900; k < 2031; k++) {
			sluttTidAar.addItem(k);
		}
	    sluttTidAar.setSelectedIndex(0);
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 2;
	    gc.gridy = 2;
	    add(sluttTidAar, gc);
	    
	    // SLUTT-TID MÅNED
	    
	    sluttTidMaaned = new JComboBox(Maaned.values());
	    sluttTidMaaned.setSelectedIndex(tid.getTime().getMonth());
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 3;
	    gc.gridy = 2;
	    add(sluttTidMaaned, gc);
	    
	    // SLUTT-TID DAG
	    
	    sluttTidDag = new JComboBox();
	    //setter antall dager som hoorer til current maaned
		settSluttDager();
	    sluttTidDag.setSelectedIndex(tid.getTime().getDate()-1);
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 4;
	    gc.gridy = 2;
	    add(sluttTidDag, gc);
	    
	    // SLUTT-TID KLOKKE
	    
	    sluttTidKl = new JTextField();
	    sluttTidKl.setEditable(true);
	    sluttTidKl.setText(1+ tid.getTime().getHours()+":" + minutesWithNull);
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 5;
	    gc.gridy = 2;
	    add(sluttTidKl, gc);
	    
	    // BESKRIVELSE
	    
	    beskrivelseLabel = new JLabel("Beskrivelse:");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 3;
	    gc.insets = new Insets(0, 0, 50, 0);
	    add(beskrivelseLabel, gc);
	    
	    beskrivelseFelt = new JTextField();
	    gc.gridwidth = 4;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 2;
	    gc.gridy = 3;
	    gc.insets = new Insets(0, 0, 50, 0);
	    add(beskrivelseFelt, gc);
	    
	    // LISTE MED DELTAGERE
	    deltagereLabel = new JLabel("Deltagere:");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 5;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(deltagereLabel, gc);
	    
	    
		deltagerModell = new DefaultListModel();
		deltagereList = new JListScroll(deltagerModell);
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 6;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(deltagereList.getJScrollPane(), gc);
	    
	    // FJERN - BUTTON
	    
	   	fjernButton = new JButton();
	   	fjernButton.setName("Fjern");
	   	fjernButton.setText("Fjern =>");
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 7;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(fjernButton, gc);
	    
	    
		// LISTE MED PERSONER
	    
	    personerLabel = new JLabel("Personer:");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 3;
	    gc.gridy = 5;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(personerLabel, gc);
	    
		personModell = new DefaultListModel();
		personerList = new JListScroll(personModell);
	    gc.weightx = 0.5;
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 3;
	    gc.gridy = 6;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(personerList.getJScrollPane(), gc);
	    
	    inviterPersonButton = new JButton();
	    inviterPersonButton.setName("Velg");
	    inviterPersonButton.setText("<= Inviter person");
	    gc.weightx = 0.5;
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 3;
	    gc.gridy = 7;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(inviterPersonButton, gc);
	    
		// LISTE MED GRUPPER
	    
	    grupperLabel = new JLabel("Grupper:");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 5;
	    gc.gridy = 5;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(grupperLabel, gc);
	    
		gruppeModell = new DefaultListModel();
		grupperList = new JListScroll(gruppeModell);
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 5;
	    gc.gridy = 6;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(grupperList.getJScrollPane(), gc);
	    
	    inviterGruppeButton = new JButton();
	    inviterGruppeButton.setText("<= Inviter gruppe");
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 5;
	    gc.gridy = 7;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(inviterGruppeButton, gc);
	    
	    // MØTEROM
	    
	    mooteromLabel = new JLabel("Møterom:");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 8;
	    gc.insets = new Insets(60, 0, 0, 0);
	    add(mooteromLabel, gc);
	    
	    
	    // Finn et møterom
	    
	    finnEtRomLabel = new JLabel("Finn et rom: ");
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 9;
	    gc.insets = new Insets(10, 0, 0, 0);
	    add(finnEtRomLabel, gc);
	    
	    finnEtRomCheckbox = new JCheckBox();
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 2;
	    gc.gridy = 9;
	    gc.insets = new Insets(10, 0, 0, 0);
	    add(finnEtRomCheckbox, gc);
	    
	    romBox = new JComboBox();
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 3;
	    gc.gridy = 9;
	    gc.insets = new Insets(10, 0, 0, 0);
	    add(romBox, gc);
	    
	    finnPassendeRomButton = new JButton();
	   	finnPassendeRomButton.setText("Finn et passende rom");
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 4;
	    gc.gridy = 9;
	    gc.insets = new Insets(10, 0, 0, 0);
	    add(finnPassendeRomButton, gc);
	    
	    beskjedEtterFunnetRomLabel = new JLabel("med tanke på antall deltagere");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 5;
	    gc.gridy = 9;
	    gc.insets = new Insets(10, 0, 0, 0);
	    add(beskjedEtterFunnetRomLabel, gc);
	    
	    // Beskrivelse av rom
	    
	    beskrivelseAvRomLabel = new JLabel("Her kommer rombeskrivelse når du har valgt rom");
	    gc.gridwidth = 3;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 3;
	    gc.gridy = 10;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(beskrivelseAvRomLabel, gc);
	    
	    // Selvvalgt rom
	    
	    velgEgetRomLabel = new JLabel("Eller velg et eget rom: ");
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 11;
	    gc.insets = new Insets(20, 0, 0, 0);
	    add(velgEgetRomLabel, gc);
	    
	    egetRomCheckbox = new JCheckBox();
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 2;
	    gc.gridy = 11;
	    gc.insets = new Insets(20, 0, 0, 0);
	    add(egetRomCheckbox, gc);
	    
	    egetRomFelt = new JTextField();
	    egetRomFelt.setEditable(false);
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 3;
	    gc.gridy = 11;
	    gc.insets = new Insets(20, 0, 0, 0);
	    add(egetRomFelt, gc);
	    
	    // Buttongroup
	    
	    buttongroup = new ButtonGroup();
	    buttongroup.add(egetRomCheckbox);
	    buttongroup.add(finnEtRomCheckbox);
	    finnEtRomCheckbox.setSelected(true);
	    
	    // AVBRYT OG LAGRE - BUTTONS
	    
	    avbrytButton = new JButton();
	   	avbrytButton.setText("Avbryt");
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 12;
	    gc.insets = new Insets(20, 0, 0, 0);
	    add(avbrytButton, gc);
	    
	   	lagreButton = new JButton();
	   	lagreButton.setText("Lagre avtale");
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 2;
	    gc.gridy = 12;
	    gc.insets = new Insets(20, 0, 0, 0);
	    add(lagreButton, gc);
	    
	    // LEGGER INN VERDIER FOR Å TESTE
	    
	    // Han som inviterer (Må legge til ekstra funksjoner til han)
	    vert = new Ansatt("Vert");
	    deltagerModell.addElement(vert);
		
	    // Ansatte
		kari = new Ansatt("Kari");
		ida = new Ansatt("Ida");
		henrik = new Ansatt("Henrik");
		gris = new Ansatt("gris");
		fredrik = new Ansatt("Fredrik");
		nora = new Ansatt("Nora");
		eline = new Ansatt("Eline");
		Gina = new Ansatt("Gina");
		Fridtjof = new Ansatt("Fridtjof");
		oyvind = new Ansatt("Øyvind");
		ola = new Ansatt("Ola");
		knut = new Ansatt("Knut");
		eirik = new Ansatt("Eirik");
		truls = new Ansatt("Truls");
		personModell.addElement(kari);
		personModell.addElement(ida);
		personModell.addElement(henrik);
		personModell.addElement(gris);
		personModell.addElement(fredrik);
		personModell.addElement(nora);
		personModell.addElement(eline);
		personModell.addElement(Gina);
		personModell.addElement(Fridtjof);
		personModell.addElement(oyvind);
		personModell.addElement(ola);
		personModell.addElement(knut);
		personModell.addElement(eirik);
		personModell.addElement(truls);
		
		//Grupper
		Gruppe rekrutteringsgruppa = new Gruppe("Rekrutteringsgruppa");
		rekrutteringsgruppa.setNavn("Rekrutteringsgruppa");
		rekrutteringsgruppa.setMedlem(kari);
		rekrutteringsgruppa.setMedlem(ida);
		Gruppe administrerende = new Gruppe("Administrerende");
		administrerende.setNavn("Administrerende");
		administrerende.setMedlem(knut);
		administrerende.setMedlem(Fridtjof);
		administrerende.setMedlem(ola);
		administrerende.setMedlem(oyvind);
		Gruppe utviklere = new Gruppe("Utviklere");
		utviklere.setNavn("Utviklere");
		utviklere.setMedlem(eline);
		utviklere.setMedlem(kari);
		utviklere.setMedlem(ida);
		utviklere.setMedlem(truls);
		utviklere.setMedlem(Gina);
		utviklere.setMedlem(fredrik);
		gruppeModell.addElement(rekrutteringsgruppa);
		gruppeModell.addElement(administrerende);
		gruppeModell.addElement(utviklere);
		
		//Rom
	    Rom r1 = new Rom("R1", 3);
	    Rom r2 = new Rom("R2", 10);
	    Rom r3 = new Rom("R3", 15);
	    Rom r4 = new Rom("R4", 5);
	    r1.setBeskrivelse("beskrivelse av rom r1");
	    r2.setBeskrivelse("beskrivelse av rom r2");
	    r3.setBeskrivelse("beskrivelse av rom r3");
	    r4.setBeskrivelse("beskrivelse av rom r4");
	    romBox.addItem((Rom) r1);
	    romBox.addItem((Rom) r2);
	    romBox.addItem((Rom) r3);
	    romBox.addItem((Rom) r4);
	    
	    
	    // ACTIONLISTENERS
	    
	    startTidAar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ((Integer) startTidAar.getSelectedItem() > (Integer) sluttTidAar.getSelectedItem()) {
					sluttTidAar.setSelectedItem(startTidAar.getSelectedItem());
				}
			}
		});
	    
	    sluttTidAar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ((Integer) startTidAar.getSelectedItem() > (Integer) sluttTidAar.getSelectedItem()) {
					sluttTidAar.setSelectedItem(startTidAar.getSelectedItem());
				}
			}
		});
	    
	    startTidMaaned.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				startTidDag.removeAllItems();
				
			    //setter antall dager som hoorer til current maaned
				settStartDager();
			
				//Sjekker at starttid skal være før sluttid
				if ((Integer) startTidAar.getSelectedItem()-(Integer) sluttTidAar.getSelectedItem() == 0) {
					if ((Integer) startTidMaaned.getSelectedIndex() > (Integer) sluttTidMaaned.getSelectedIndex()) {
						sluttTidMaaned.setSelectedIndex(startTidMaaned.getSelectedIndex());
					}
				}
				//Setter dag til dagens dato
				startTidDag.setSelectedIndex(tid.getTime().getDate()-1);
				
			}
		});
	    
	    sluttTidMaaned.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sluttTidDag.removeAllItems();
				
			    //setter antall dager som hoorer til current maaned
				settSluttDager();
				
				//Sjekker at sluttid er etter starttid
				if ((Integer) startTidAar.getSelectedItem()-(Integer) sluttTidAar.getSelectedItem() == 0) {
					if ((Integer) startTidMaaned.getSelectedIndex() > (Integer) sluttTidMaaned.getSelectedIndex()) {
						sluttTidMaaned.setSelectedIndex(startTidMaaned.getSelectedIndex());
					}
				}
				sluttTidDag.setSelectedIndex(tid.getTime().getDate()-1);
			}
		});
	    
	    startTidDag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (startTidMaaned.getSelectedIndex() - sluttTidMaaned.getSelectedIndex() == 0) {
					if ((Integer) startTidDag.getSelectedIndex() > (Integer) sluttTidDag.getSelectedIndex()) {
						sluttTidDag.setSelectedIndex(startTidDag.getSelectedIndex());
					}
				}
				
			}
		});
	    
	    sluttTidDag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (startTidMaaned.getSelectedIndex() - sluttTidMaaned.getSelectedIndex() == 0) {
					if ((Integer) startTidDag.getSelectedIndex() > (Integer) sluttTidDag.getSelectedIndex() && sluttTidDag.getItemCount() > 27) {
						sluttTidDag.setSelectedIndex(startTidDag.getSelectedIndex());
					}
				}
			}
		});
	    
	    inviterPersonButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deltagerModell.addElement(personerList.getJList().getSelectedValue());
				deltagereList.setDefaultListModel(deltagerModell);
				personModell.removeElement(personerList.getJList().getSelectedValue());
				personerList.setDefaultListModel(personModell);
				deltagereList.updateUI();	
				
			}
		});
	    
	    inviterGruppeButton.addActionListener(new ActionListener() {
	    	
			@Override
			public void actionPerformed(ActionEvent e) {
				Gruppe g = (Gruppe) grupperList.getJList().getSelectedValue();
				for (int r = 0; r < g.getCount(); r++) {
					Ansatt medlem = g.getAnsatt(r);
					if (deltagerModell.contains(medlem)) {
						String message = medlem.getBrukernavn() + " er allerede invitert";
						JOptionPane.showMessageDialog(popUpWithMessage, message);
					}
					else {
						deltagerModell.addElement(medlem);
						deltagereList.setDefaultListModel(deltagerModell);
						personModell.removeElement(medlem);
						personerList.setDefaultListModel(personModell);
					}
				}
			}
		});
	    
	    fjernButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				if (deltagereList.getJList().getSelectedValue() == vert) {
					JOptionPane.showMessageDialog(popUpWithMessage, "Verten må delta på arrangementet. Kan ikke fjernes.");
				}
				else {*/
					personModell.addElement(deltagereList.getJList().getSelectedValue());
					personerList.setDefaultListModel(personModell);
					deltagerModell.removeElement(deltagereList.getJList().getSelectedValue());
					deltagereList.setDefaultListModel(deltagerModell);
				//}
			}
		});
	    
	    finnPassendeRomButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int antallDeltagere = deltagereList.getDefaultListModel().getSize();
				Rom passendeRom = (Rom) romBox.getItemAt(0);
				
				// må søke gjennom alle avtaler, og sjekke om det passende møterommet er ledig på gitt tidspunkt
				String datoTest = "testDato";
				
				for (int s = 0; s < romBox.getItemCount(); s++) {
					Rom rom = (Rom) romBox.getItemAt(s);
					if (rom.getMaksAntallPersoner() >= antallDeltagere && (rom.romLedigPaaGittTidspunkt(datoTest)==true)) {
						if ((rom.getMaksAntallPersoner()-antallDeltagere) <= (passendeRom.getMaksAntallPersoner()-antallDeltagere) || passendeRom.getMaksAntallPersoner()-antallDeltagere <= 0) {
							passendeRom = rom;
						}
					}
				}
				romBox.setSelectedItem(passendeRom);
				romBox.updateUI();
				beskrivelseAvRomLabel.setText(passendeRom.getBeskrivelse());
				
				if (passendeRom.getMaksAntallPersoner() < antallDeltagere) {
					JOptionPane.showMessageDialog(popUpWithMessage, "Det finnes dessverre ikke et stort nok møterom");
				}
				
			}
		});
	    
	    finnEtRomCheckbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (finnEtRomCheckbox.isSelected()) {
					romBox.setEditable(true);
					romBox.setEnabled(true);
					finnPassendeRomButton.setEnabled(true);
					egetRomFelt.setEditable(false);
				}
				else {
					romBox.setEditable(false);
					romBox.setEnabled(false);
					finnPassendeRomButton.setEnabled(false);
					egetRomFelt.setEditable(true);
				}
				
			}
		});
	    
	    egetRomCheckbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (egetRomCheckbox.isSelected()) {
					egetRomFelt.setEditable(true);
					romBox.setEditable(false);
					romBox.setEnabled(false);
					finnPassendeRomButton.setEnabled(false);
				}
				else {
					egetRomFelt.setEditable(false);
					romBox.setEditable(true);
					romBox.setEnabled(true);
					finnPassendeRomButton.setEnabled(true);
				}
				
			}
		});
	    
	    lagreButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String st = startTidAar.getSelectedItem()+"-"+startTidMaaned.getSelectedItem()+"-"+startTidDag.getSelectedItem()+"-"+startTidKl.getText();
				String sl = sluttTidAar.getSelectedItem()+"-"+sluttTidMaaned.getSelectedItem()+"-"+sluttTidDag.getSelectedItem()+"-"+sluttTidKl.getText();
				Avtale avtale = new Avtale(st, sl, beskrivelseFelt.getText(), (Rom) romBox.getSelectedItem(), Fridtjof);
				beskrivelseAvRomLabel = new JLabel(avtale.toString());
				
				try {
					db.addAvtale(avtale);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//Inviterer alle deltagerne
				for (int a = 0; a < deltagerModell.getSize(); a++) {
					String ansattInvitert = ((Ansatt) deltagerModell.get(a)).getBrukernavn().toLowerCase();
					try {
						((Database) db).invitertTilAvtale(ansattInvitert, avtale.getId());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	protected enum Maaned {
		Januar,
		Feburar,
		Mars,
		April,
		Mai,
		Juni,
		Juli,
		August,
		September,
		Oktober,
		November,
		Desember
	}
	
	private void settStartDager() {
		// det er start-dager som skal settes
		Maaned stm = (Maaned) startTidMaaned.getSelectedItem();
		if (stm == Maaned.Feburar) {
			for (int dag = 1; dag < 29; dag++) {
				startTidDag.addItem(dag);
			}
			// Dersom det er skuddår skal det være 29 dager i februar
			if (tid.getTime().getYear() % 4 == 0) {
				startTidDag.addItem(29);
			}
		}
		
		else if(stm == Maaned.Januar || stm == Maaned.Mars || stm == Maaned.Mai || stm == Maaned.Juli || stm == Maaned.August || stm == Maaned.Oktober || stm == Maaned.Desember) {
			for (int dag = 1; dag < 32; dag++) {
				startTidDag.addItem(dag);
			}
		}
		
		else if(stm == Maaned.April || stm == Maaned.Juni || stm == Maaned.September || stm == Maaned.November) {
			for (int dag = 1; dag < 31; dag++) {
				startTidDag.addItem(dag);
			}
			
		}
	}
	
	private void settSluttDager() {
		// det er slutt-dager som skal settes
		Maaned sltm = (Maaned) sluttTidMaaned.getSelectedItem();
		if (sltm == Maaned.Feburar) {
			for (int dag = 1; dag < 29; dag++) {
				sluttTidDag.addItem(dag);
			}
			// Dersom det er skuddår skal det være 29 dager i februar
			if (tid.getTime().getYear() % 4 == 0) {
				sluttTidDag.addItem(29);
			}
		}
		
		else if(sltm == Maaned.Januar || sltm == Maaned.Mars || sltm == Maaned.Mai || sltm == Maaned.Juli || sltm == Maaned.August || sltm == Maaned.Oktober || sltm == Maaned.Desember) {
			for (int dag = 1; dag < 32; dag++) {
				sluttTidDag.addItem(dag);
			}
		}
		
		else if(sltm == Maaned.April || sltm == Maaned.Juni || sltm == Maaned.September || sltm == Maaned.November) {
			for (int dag = 1; dag < 31; dag++) {
				sluttTidDag.addItem(dag);
			}
		}

	}
	
	// CELL-RENDERER - SKAL VI BRUKE DENNE?
	
	/*
	private static class JListCellRenderer extends DefaultListCellRenderer {  
        public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {  
            Component c = super.getListCellRendererComponent( list, value, index, isSelected, cellHasFocus );  
            c.setBackground(Color.white);
            c.setForeground(Color.black);
            if (cellHasFocus) {
            	c.setBackground(Color.cyan);
            }
            return c;  
        }  
    }
    */ 
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(900,600));
		nyAvtale na = new nyAvtale();
		frame.setContentPane(na);
		frame.setVisible(true);
		frame.setBackground(Color.white);
		
	} 

}

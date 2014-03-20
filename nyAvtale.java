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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Inet4Address;
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


public class nyAvtale<finnEtRomCheckbox> extends JFrame implements ActionListener{

	GridBagConstraints gc;
	Calendar tid;
	final JFrame popUpWithMessage = new JFrame();
	JScrollPane listScrollPane;
	Database db;
	Ansatt vert;
	Avtale oldAvtale = null;
	ProgramFrame frame;
	
	TLSEmail emailHandler;
	
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
	
	//ekstern bruker
	JLabel eksternBrukerLabel;
	JLabel eksternBrukerNavnLabel;
	JTextField eksternBrukerNavn;
	JLabel eksternBrukerEmailLabel;
	JTextField eksternBrukerEmail;
	JButton leggTilEksternBrukerButton;
	
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
	
	public nyAvtale(ProgramFrame frame) throws SQLException  {
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		tid = new GregorianCalendar();
		db = new Database();
		this.frame=frame;
		
		emailHandler = new TLSEmail();
		
		tidBeskrivelse();
		deltagerePersonerGrupper();
		rom();
	    avbrytLagreButtons();
	    eksternBruker();
	}
	
	private void tidBeskrivelse() {
		// TODO Auto-generated method stub
		// START-TID
		
	    startTidLabel = new JLabel("Start-tidspunkt:");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 1;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(startTidLabel, gc);
	    
	    // START  R
	    
	    startTidAar = new JComboBox();
	    for (int k = tid.getTime().getYear()+1900; k < 2031; k++) {
			startTidAar.addItem(k);
		}
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 2;
	    gc.gridy = 1;
	    add(startTidAar, gc);
	    
	    // START M NED
	    
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
	    
	    // SLUTT-TID  R
	    
	    sluttTidAar = new JComboBox();
	    for (int k = tid.getTime().getYear()+1900; k < 2031; k++) {
			sluttTidAar.addItem(k);
		}
	    sluttTidAar.setSelectedIndex(0);
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 2;
	    gc.gridy = 2;
	    add(sluttTidAar, gc);
	    
	    // SLUTT-TID M NED
	    
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
			
				//Sjekker at starttid skal v re f r sluttid
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
		
	}
	
	private void settStartDager() {
		// det er start-dager som skal settes
		Maaned stm = (Maaned) startTidMaaned.getSelectedItem();
		if (stm == Maaned.Feburar) {
			for (int dag = 1; dag < 29; dag++) {
				startTidDag.addItem(dag);
			}
			// Dersom det er skudd r skal det v re 29 dager i februar
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
			// Dersom det er skudd r skal det v re 29 dager i februar
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
	
	private void deltagerePersonerGrupper() throws SQLException {
		// LISTE MED DELTAGERE
	    
	    deltagereLabel = new JLabel("Deltagere:");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 5;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(deltagereLabel, gc);
	    
		// Legger til vert
		vert = frame.getUser();
	    
		deltagerModell = new DefaultListModel();
		deltagerModell.addElement(vert);
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
	    
		personModell = db.getAlleAnsatte();
	    //personModell = new DefaultListModel();
		
		for (int t = 0; t < personModell.getSize(); t++){
			Ansatt aaa =(Ansatt) personModell.getElementAt(t);
			if (vert.getBrukernavn().equals(aaa.getBrukernavn())){
				personModell.removeElement(aaa);
				t = personModell.getSize() +1;
			}
		}
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
	    
	    gruppeModell = db.getAlleGrupper();
		//gruppeModell = new DefaultListModel();
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
					Ansatt faktisk = new Ansatt("tull");
					for (int t=0;t < personModell.getSize(); t++){
						Ansatt Test = (Ansatt) personModell.get(t);
						if (medlem.getBrukernavn().equals(Test.getBrukernavn())){
							faktisk = Test;
							t = personModell.getSize();
						}
					if (deltagerModell.contains(faktisk)) {
						String message = medlem.getBrukernavn() + " er allerede invitert";
						JOptionPane.showMessageDialog(popUpWithMessage, message);
					}
					else {
						}
						if (faktisk.getBrukernavn()!= "tull"){
							deltagerModell.addElement(faktisk);
							deltagereList.setDefaultListModel(deltagerModell);
							personModell.removeElement(faktisk);
							personerList.setDefaultListModel(personModell);	
						}
						}
					}
				}
			});
	    
	    fjernButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				if (deltagereList.getJList().getSelectedValue() == vert) {
					JOptionPane.showMessageDialog(popUpWithMessage, "Verten m  delta p  arrangementet. Kan ikke fjernes.");
				}
				else {*/
					personModell.addElement(deltagereList.getJList().getSelectedValue());
					personerList.setDefaultListModel(personModell);
					deltagerModell.removeElement(deltagereList.getJList().getSelectedValue());
					deltagereList.setDefaultListModel(deltagerModell);
				//}
			}
	    });}
	
	private void rom() throws SQLException {
		
		// M TEROM
	    mooteromLabel = new JLabel("M terom:");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 11;
	    gc.insets = new Insets(60, 0, 0, 0);
	    add(mooteromLabel, gc);
	    
	    
	    // Finn et m terom
	    
	    finnEtRomLabel = new JLabel("Finn et rom: ");
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 12;
	    gc.insets = new Insets(10, 0, 0, 0);
	    add(finnEtRomLabel, gc);
	    
	    finnEtRomCheckbox = new JCheckBox();
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 2;
	    gc.gridy = 12;
	    gc.insets = new Insets(10, 0, 0, 0);
	    add(finnEtRomCheckbox, gc);
	    
	    romBox = db.getAlleRom(); //henter alle rom fra database
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 3;
	    gc.gridy = 12;
	    gc.insets = new Insets(10, 0, 0, 0);
	    add(romBox, gc);
	    
	    finnPassendeRomButton = new JButton();
	   	finnPassendeRomButton.setText("Finn et passende rom");
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 4;
	    gc.gridy = 12;
	    gc.insets = new Insets(10, 0, 0, 0);
	    add(finnPassendeRomButton, gc);
	    
	    beskjedEtterFunnetRomLabel = new JLabel("med tanke p  antall deltagere");
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 5;
	    gc.gridy = 12;
	    gc.insets = new Insets(10, 0, 0, 0);
	    add(beskjedEtterFunnetRomLabel, gc);
	    
	    // Beskrivelse av rom
	    
	    beskrivelseAvRomLabel = new JLabel("Her kommer rombeskrivelse n r du har valgt rom");
	    gc.gridwidth = 3;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 3;
	    gc.gridy = 13;
	    gc.insets = new Insets(0, 0, 0, 0);
	    add(beskrivelseAvRomLabel, gc);
	    
	    // Selvvalgt rom
	    
	    velgEgetRomLabel = new JLabel("Eller velg et eget rom: ");
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 14;
	    gc.insets = new Insets(20, 0, 0, 0);
	    add(velgEgetRomLabel, gc);
	    
	    egetRomCheckbox = new JCheckBox();
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 2;
	    gc.gridy = 14;
	    gc.insets = new Insets(20, 0, 0, 0);
	    add(egetRomCheckbox, gc);
	    
	    egetRomFelt = new JTextField();
	    egetRomFelt.setEditable(false);
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 3;
	    gc.gridy = 14;
	    gc.insets = new Insets(20, 0, 0, 0);
	    add(egetRomFelt, gc);
	    
	    // Buttongroup
	    
	    buttongroup = new ButtonGroup();
	    buttongroup.add(egetRomCheckbox);
	    buttongroup.add(finnEtRomCheckbox);
	    finnEtRomCheckbox.setSelected(true);
	    
	    finnPassendeRomButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int antallDeltagere = deltagereList.getDefaultListModel().getSize();
				Rom passendeRom = (Rom) romBox.getItemAt(0);
				
				// m  s ke gjennom alle avtaler, og sjekke om det passende m terommet er ledig p  gitt tidspunkt
				String datoSt = startTidAar.getSelectedItem()+"-"+startTidMaaned.getSelectedItem()+"-"+startTidDag.getSelectedItem()+
						"-"+startTidKl.getText();
				String datoSl=sluttTidAar.getSelectedItem()+"-"+sluttTidMaaned.getSelectedItem()+"-"+sluttTidDag.getSelectedItem()+
						"-"+sluttTidKl.getText();
				
				for (int s = 0; s < romBox.getItemCount(); s++) {
					Rom rom = (Rom) romBox.getItemAt(s);
					if (rom.getMaksAntallPersoner() >= antallDeltagere && rom.romLedigPaaGittTidspunkt(datoSt+"/"+datoSl)) {
						if ((rom.getMaksAntallPersoner()-antallDeltagere) <= (passendeRom.getMaksAntallPersoner()-antallDeltagere) || passendeRom.getMaksAntallPersoner()-antallDeltagere <= 0) {
							passendeRom = rom;
						}
					}
				}
				romBox.setSelectedItem(passendeRom);
				romBox.updateUI();
				beskrivelseAvRomLabel.setText(passendeRom.getBeskrivelse());
				
				if (passendeRom.getMaksAntallPersoner() < antallDeltagere) {
					JOptionPane.showMessageDialog(popUpWithMessage, "Det finnes dessverre ikke et stort nok m terom");
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
	    
	}
	
	private void avbrytLagreButtons() {
		// AVBRYT OG LAGRE - BUTTONS
	    
	    avbrytButton = new JButton();
	   	avbrytButton.setText("Avbryt");
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 15;
	    gc.insets = new Insets(20, 0, 0, 0);
	    add(avbrytButton, gc);
	    
	   	lagreButton = new JButton();
	   	lagreButton.setText("Lagre avtale");
	    gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 2;
	    gc.gridy = 15;
	    gc.insets = new Insets(20, 0, 0, 0);
	    add(lagreButton, gc);
	    
	    lagreButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//ToDo: nyAvtale() skal ogs� fungere som endreAvtale(), slik at n�r man trykker p� "lagre" m� det sjekkes om en avtale opprettes
				// eller oppdateres.Dersom den oppdateres m� den gamle slettes og en ny, oppdatert, lages.
				
				
				// finner lagrede felter : start- og sluttid.
				String st = startTidAar.getSelectedItem()+"-"+startTidMaaned.getSelectedItem()+"-"+startTidDag.getSelectedItem()+"-"+startTidKl.getText();
				String sl = sluttTidAar.getSelectedItem()+"-"+sluttTidMaaned.getSelectedItem()+"-"+sluttTidDag.getSelectedItem()+"-"+sluttTidKl.getText();
			
				Avtale avtale;
				try {
					// fjerner gammel avtale dersom en bare endres paa
					if (oldAvtale != null) {
						db.fjerneAvtale(oldAvtale);
					}
					// oppretter ny avtale med de feltene som er fylt inn i GUI
					if(finnEtRomCheckbox.isSelected()){
						avtale = new Avtale(st, sl, beskrivelseFelt.getText(), (Rom) romBox.getSelectedItem(), deltagerModell, vert, null);
					}else{
						avtale = new Avtale(st, sl, beskrivelseFelt.getText(), null, deltagerModell, vert, egetRomFelt.getText());
					}
					// legger til avtalen i databasen
					db.setNyAvtale(avtale);
					//Inviterer alle deltagerne
					for (int a = 0; a < deltagerModell.getSize(); a++) {
						// Inviterer hver enkelt som er valgt
						if(deltagerModell.get(a) instanceof Ansatt){//deltageren er ansatt
							String ansattInvitert = ((Ansatt) deltagerModell.get(a)).getBrukernavn().toLowerCase();
							try {
								((Database) db).setPersonDeltarAvtale(ansattInvitert, avtale.getId());
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}else{//deltageren er eksternbruker
							String invitert = ((EksternBruker) deltagerModell.get(a)).getMail();
							try {
								((Database) db).setEksternBrukerDeltar(invitert, avtale.getId());
								emailHandler.sendEmail(invitert, vert.getNavn(),vert.getEmail());
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
					db.setBekreftetStatus(vert, avtale, 1);
					//lukker vinduet
					dispose();
					frame.update();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	    avbrytButton.addActionListener(this);
	}
	
	public void eksternBruker(){
		eksternBrukerLabel=new JLabel("Ekstern bruker: ");
		gc.gridwidth = 1;
	    gc.gridheight = 1;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.gridx = 1;
	    gc.gridy = 8;
	    gc.insets = new Insets(15, 0, 0, 0);
		add(eksternBrukerLabel, gc);
		
		eksternBrukerEmailLabel=new JLabel("Mail: ");
	    gc.gridx = 1;
	    gc.gridy = 9;
	    gc.insets = new Insets(5, 0, 0, 0);
		add(eksternBrukerEmailLabel, gc);
		
		eksternBrukerEmail=new JTextField(20);
	    gc.gridx = 1;
	    gc.gridy = 10;
	    gc.insets = new Insets(5, 0, 0, 0);
		add(eksternBrukerEmail, gc);
		eksternBrukerEmail.addKeyListener(new KeyListener() {
			
		//naar du skriver inn mail og trykker enter eller bytter skal det sjekkes om personen finnes i databasen og da skal navnet fylles ut
			
	    
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER||arg0.getKeyCode()==KeyEvent.VK_SPACE){
					try {
						if(db.eksternBrukerenEksisterer(eksternBrukerEmail.getText())){
							eksternBrukerNavn.setText(db.getEksternBruker(eksternBrukerEmail.getText()).getNavn());
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		
		eksternBrukerNavnLabel=new JLabel("Navn: ");
	    gc.gridx = 3;
	    gc.gridy = 9;
	    gc.insets = new Insets(5, 0, 0, 0);
		add(eksternBrukerNavnLabel, gc);
		
		eksternBrukerNavn=new JTextField(20);
	    gc.gridx = 3;
	    gc.gridy = 10;
	    gc.insets = new Insets(5, 0, 0, 0);
		add(eksternBrukerNavn, gc);
		
		leggTilEksternBrukerButton=new JButton("<= Legg til");
	    gc.gridx = 5;
	    gc.gridy = 10;
	    gc.insets = new Insets(5, 0, 0, 0);
		add(leggTilEksternBrukerButton,gc);
		leggTilEksternBrukerButton.addActionListener(new ActionListener() {
		 
			@Override
			//legger til eksternBruker i deltager liste
			public void actionPerformed(ActionEvent arg0) {
				String mail=eksternBrukerEmail.getText();
				String navn=eksternBrukerNavn.getText();
				try {
					if(!db.eksternBrukerenEksisterer(mail)){//ser om brukeren eksisterer
						if(validMail(mail)){
							db.setNyEksternBruker(mail, navn);//hvis han ikke gjore det blir han lagt til
						}else{
							JOptionPane.showMessageDialog(popUpWithMessage, "Ugyldig email");
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				EksternBruker bruker;
				try {
					bruker = db.getEksternBruker(mail);
					deltagerModell.addElement(bruker);//legger til personen i listen
					eksternBrukerEmail.setText("");//feltene blir tomme naar personen blir lagt til
					eksternBrukerNavn.setText("");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			private boolean validMail(String mail) {//sjekker at mailadressen er gyldig
				if(mail.contains("@")&&mail.contains(".")){//skal endres
					return true;
				}
				return false;
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}

	public void endreAvtale(Avtale a, ProgramFrame frame) throws SQLException {
		oldAvtale = a;
		//setter start tid
		String[] startTid=oldAvtale.getStartTid().split("-");
		startTidAar.setSelectedItem(startTid[0]);
		startTidMaaned.setSelectedItem(startTid[1]);
		startTidDag.setSelectedItem(startTid[2]);
		startTidKl.setText(startTid[3]);
		//setter slutt tid
		String[] sluttTid=oldAvtale.getSluttTid().split("-");
		sluttTidAar.setSelectedItem(sluttTid[0]);
		sluttTidMaaned.setSelectedItem(sluttTid[1]);
		sluttTidDag.setSelectedItem(sluttTid[2]);
		sluttTidKl.setText(sluttTid[3]);
		//setter beskrivelse
		beskrivelseFelt.setText(oldAvtale.getBeskrivelse());
		//setter deltagere
		deltagerModell.removeElement(frame.getUser());
		DefaultListModel deltagere=db.alleDeltagere(oldAvtale.getId());
		for(int i=0; i<deltagere.getSize();i++){
			deltagerModell.addElement(deltagere.get(i));
			personModell.removeElement(deltagere.get(i));
		}
		DefaultListModel eksterneDeltagere=db.alleEksterneDeltagere(oldAvtale.getId());
		for(int i=0; i<eksterneDeltagere.getSize();i++){
			deltagerModell.addElement(eksterneDeltagere.get(i));
			personModell.removeElement(eksterneDeltagere.get(i));
		}
		//setter rom
		if(oldAvtale.getRom().getNavn().equals("0")){
			egetRomCheckbox.setSelected(true);
			finnEtRomCheckbox.setSelected(false);
			egetRomFelt.setEditable(true);
			romBox.setEnabled(false);
			romBox.setEnabled(false);
			egetRomFelt.setText(oldAvtale.getSted());
		}else{
			finnEtRomCheckbox.setSelected(true);
			egetRomCheckbox.setSelected(false);
			romBox.setSelectedItem(oldAvtale.getRom());
		}
		
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
	
	/*
	public static void main(String[] args) throws SQLException {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(400,200));
		nyAvtale pp = new nyAvtale();
		frame.setContentPane(pp);
		frame.setVisible(true);
	}
	*/
	
	/*
	 * // CELL-RENDERER - brukes ikke n , men kan tas ibruk dersom vi trenger!
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

}

package Fellesprosjektet;

import javax.swing.DefaultListModel;

public class Avtale {
	private static int count = 0;
	
	private int id;
	private String startTid;
	private String sluttTid;
	private String beskrivelse;
	private Rom rom = null;
	private Ansatt leder;
	
	
	public Avtale(String st, String sl, String besk, Rom r, DefaultListModel deltagere, Ansatt leder) {
		/*
		 * Dette kan brukes dersom tid skal hentes ut av databasen!
		 * 
		String[] startParts = st.split("-");
		this.startAar = startParts[0];
		this.startMaaned = startParts[1];
		this.startDag = startParts[2];
		this.startKl = startParts[3];
		
		String[] sluttParts = sl.split("-");
		this.sluttAar = sluttParts[0];
		this.sluttMaaned = sluttParts[1];
		this.sluttDag = sluttParts[2];
		this.sluttKl = sluttParts[3];
		*/
		startTid = st;
		sluttTid = sl;
		this.beskrivelse = besk;
		this.rom = r;
		this.id = count;
		count += 1;
		
		this.leder=leder;
	}


	public static int getCount() {
		return count;
	}


	public static void setCount(int count) {
		Avtale.count = count;
	}


	public String getStartTid() {
		return startTid;
	}


	public void setStartTid(String startTid) {
		this.startTid = startTid;
	}


	public String getSluttTid() {
		return sluttTid;
	}


	public void setSluttTid(String sluttTid) {
		this.sluttTid = sluttTid;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Rom getRom() {
		return rom;
	}

	public void setRom(Rom rom) {
		this.rom = rom;
	}
	
	public String toString() {
		return "Id: " + id + "  Rom: " + rom;
	}
	
	public void setLeder(Ansatt leder){
		this.leder=leder;
	}
	
	public Ansatt getLeder(){
		return this.leder;
	}
	
	public void setBeskrivelse(String beskrivelse){
		this.beskrivelse=beskrivelse;
	}
	
	public String getBeskrivelse(){
		return this.beskrivelse;
	}
	
	public int getId(){
		return id;
	}
}

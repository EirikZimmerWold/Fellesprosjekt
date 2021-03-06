package Fellesprosjektet;

import java.sql.SQLException;

import javax.swing.DefaultListModel;

public class Avtale {
	private int id;
	private String startTid;
	private String sluttTid;
	private String beskrivelse;
	private Rom rom = null;
	private Ansatt leder;
	private DefaultListModel model;
	private Database db;
	private String sted;
	
	public Avtale(String st, String sl, String besk, Rom r, DefaultListModel deltagere, Ansatt leder, String sted) throws SQLException {
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
		model = deltagere;
		this.leder=leder;
		this.sted=sted;
		
		db = new Database();
		this.id = db.getNyAvtaleID();
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
	public void setModel(DefaultListModel modell){
		this.model = modell;
	}
	public DefaultListModel getModel(){
		return model;
	}
	public void setSted(String sted){
		this.sted=sted;
	}
	public String getSted(){
		return this.sted;
	}
}

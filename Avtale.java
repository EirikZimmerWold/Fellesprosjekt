package Fellesprosjektet;

import javax.swing.DefaultListModel;

public class Avtale {
	private static int count;
	
	private int id;
	private String startAar;
	private String startMaaned;
	private String startDag;
	private String startKl;
	private String sluttAar;
	private String sluttMaaned;
	private String sluttDag;
	private String sluttKl;
	private String beskrivelse;
	private Rom rom = null;
	private DefaultListModel deltagere;
	private Ansatt leder;
	
	public Avtale(String st, String sl, String besk, Rom r, DefaultListModel dm, Ansatt leder) {
		/* 
		 * Start- og sluttidspunkt skal stå på formen:
		 * 2014-12-20-1415
		 */
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
		
		this.beskrivelse = besk;
		this.rom = r;
		this.deltagere = dm;
		count += 1;
		id = count;
		
		this.leder=leder;
	}

	public String getStartAar() {
		return startAar;
	}

	public void setStartAar(String startAar) {
		this.startAar = startAar;
	}

	public String getStartMaaned() {
		return startMaaned;
	}

	public void setStartMaaned(String startMaaned) {
		this.startMaaned = startMaaned;
	}

	public String getStartDag() {
		return startDag;
	}

	public void setStartDag(String startDag) {
		this.startDag = startDag;
	}

	public String getStartKl() {
		return startKl;
	}

	public void setStartKl(String startKl) {
		this.startKl = startKl;
	}

	public String getSluttAar() {
		return sluttAar;
	}

	public void setSluttAar(String sluttAar) {
		this.sluttAar = sluttAar;
	}

	public String getSluttMaaned() {
		return sluttMaaned;
	}

	public void setSluttMaaned(String sluttMaaned) {
		this.sluttMaaned = sluttMaaned;
	}

	public String getSluttDag() {
		return sluttDag;
	}

	public void setSluttDag(String sluttDag) {
		this.sluttDag = sluttDag;
	}

	public String getSluttKl() {
		return sluttKl;
	}

	public void setSluttKl(String sluttKl) {
		this.sluttKl = sluttKl;
	}

	public Rom getRom() {
		return rom;
	}

	public void setRom(Rom rom) {
		this.rom = rom;
	}

	public DefaultListModel getDeltagere() {
		return deltagere;
	}

	public void setDeltagere(DefaultListModel deltagere) {
		this.deltagere = deltagere;
	}
	
	public String toString() {
		return id + "";
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
}

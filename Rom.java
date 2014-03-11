package Fellesprosjektet;

import java.util.ArrayList;

public class Rom {
	
	private int maksAntallPersoner;
	private String beskrivelse;
	private String navn;
	
	public Rom(String navn) {
		this.navn = navn;
	}
	
	public Rom(String navn, int ant) {
		maksAntallPersoner = ant;
		this.navn = navn;
	}
	
	public int getMaksAntallPersoner() {
		return maksAntallPersoner;
	}
	
	public void setMaksAntallPersoner(int ant) {
		maksAntallPersoner = ant;
	}
	
	public void setBeskrivelse(String b) {
		beskrivelse = b;
	}
	
	public String getBeskrivelse() {
		return beskrivelse;
	}
	
	public String toString() {
		return navn + ", maks: " + maksAntallPersoner + " personer";
	}

}

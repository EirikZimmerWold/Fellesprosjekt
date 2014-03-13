package Fellesprosjektet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Ansatt {
	
	private String brukernavn;
	private String passord;
	private String navn;
	private String fdato;
	private String email;
	private String adresse;
	private String telefon;
	private String stilling;
	
	protected Ansatt(String b) {
		// TODO Auto-generated constructor stub
		brukernavn = b;
	}

	
	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getPassord() {
		return passord;
	}

	public void setPassord(String passord) {
		this.passord = passord;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getFdato() {
		return fdato;
	}

	public void setFdato(String fdato) {
		this.fdato = fdato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}
	
	public String toString() {
		return this.brukernavn;
	}

}

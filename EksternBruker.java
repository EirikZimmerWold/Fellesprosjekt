package Fellesprosjektet;

public class EksternBruker {
	private String mail, navn;
	
	public EksternBruker(String mail, String navn){
		this.mail=mail;
		this.navn=navn;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	public String toString(){
		return this.navn;
	}

}

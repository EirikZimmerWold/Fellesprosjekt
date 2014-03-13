package Fellesprosjektet;

public class Notifikasjon {
	private String leder, startTid, sluttTid;
	private Avtale avtale;
	
	public Notifikasjon(Avtale avtale){
		this.avtale=avtale;
		this.leder=avtale.getLeder().getNavn();
		this.startTid=avtale.getStartTid();
		this.sluttTid=avtale.getSluttTid();
	}

	public String getLeder() {
		return leder;
	}

	public void setLeder(String leder) {
		this.leder = leder;
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
	
	public Avtale getAvtale(){
		return avtale;
	}
}

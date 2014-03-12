package Fellesprosjektet;

public class Notifikasjon {
	private String leder, beskrivelse, tid;
	
	public Notifikasjon(Avtale avtale){
		this.leder=avtale.getLeder().getNavn();
		this.beskrivelse=avtale.getBeskrivelse();
		String startTid=avtale.getStartDag()+"."+avtale.getStartMaaned()+"."+avtale.getStartAar()+" "+avtale.getStartKl();
		String sluttTid=avtale.getSluttDag()+"."+avtale.getSluttMaaned()+"."+avtale.getSluttAar()+" "+avtale.getSluttKl();
		this.tid=startTid+" - "+sluttTid;
	}

	public String getLeder() {
		return leder;
	}

	public void setLeder(String leder) {
		this.leder = leder;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}
}

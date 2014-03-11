package kalender;

public class avtale {
	private String leder;
	private String starttid;
	private String sluttid;
	private String Beskrivelse;
	//trenger klassen Rom
	//private Rom sted;
	
	public avtale(String leder){
		this.leder = leder;
	}
	public String getLeder(){
		return leder;
	}
	
	public void setLeder(String L){
		leder = L;
	}
	public String getStarttid(){
		return starttid;
	}
	
	public void setStarttid(String tid){
		starttid = tid;
	}
	
	public String getsluttid(){
		return sluttid;
	}
	
	
	public void setsluttid(String tid){
		sluttid = tid;
	}
	
	public String getBeskrivelse(){
		return Beskrivelse;
	}
	
	public void setBeskrivelse(String beskriv){
		Beskrivelse=beskriv;
	}
	
	
	/*public Rom getRom(){
		return Rom;
	}*/
	
	
	/*public void setRom(Rom rom){
		sted = rom
}*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

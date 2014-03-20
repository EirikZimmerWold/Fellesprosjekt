package Fellesprosjektet;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class Rom {
	
	private int maksAntallPersoner;
	private String beskrivelse;
	private String navn;
	private Database db;
	
	public Rom(String navn) {
		this.navn = navn;
		db=new Database();
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
	
	public String getNavn(){
		return this.navn;
	}
	
	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	public boolean romLedigPaaGittTidspunkt(String dato) {
		String[] tidAvtale=dato.split("/");
		
		String[] stAvtale=tidAvtale[0].split("-");//startTid nyavtale
		int stAvtaleAar=Integer.parseInt(stAvtale[0]);
		int stAvtaleMaaned=finnMaaned(stAvtale[1]);
		int stAvtaleDag=Integer.parseInt(stAvtale[2]);
		String[] stAvtaleKl=stAvtale[3].split(":");
		int stAvtaleTime=Integer.parseInt(stAvtaleKl[0]);
		int stAvtaleMin=Integer.parseInt(stAvtaleKl[1]);
		
		String[] slAvtale=tidAvtale[1].split("-");//sluttTid nyaltale
		int slAvtaleAar=Integer.parseInt(slAvtale[0]);
		int slAvtaleMaaned=finnMaaned(slAvtale[1]);
		int slAvtaleDag=Integer.parseInt(slAvtale[2]);
		String[] slAvtaleKl=slAvtale[3].split(":");
		int slAvtaleTime=Integer.parseInt(slAvtaleKl[0]);
		int slAvtaleMin=Integer.parseInt(slAvtaleKl[1]);
		try {
			DefaultListModel<String> avtaler=db.alleAvtaler(navn);
			boolean ok = true;
			for(int i=0;i<avtaler.getSize();i++){
				String[] tid=avtaler.get(i).split("/");
				System.out.println("avtale fra database"+avtaler.get(i) + ", Tid avtale:" + dato);
				
				String[] st=tid[0].split("-");//startTid
				int stAar=Integer.parseInt(st[0]);
				int stMaaned=finnMaaned(st[1]);
				int stDag=Integer.parseInt(st[2]);
				String[] stKl=st[3].split(":");
				int stTime=Integer.parseInt(stKl[0]);
				int stMin=Integer.parseInt(stKl[1]);
				
				String[] sl=tid[1].split("-");//sluttTid
				int slAar=Integer.parseInt(sl[0]);
				int slMaaned=finnMaaned(sl[1]);
				int slDag=Integer.parseInt(sl[2]);
				String[] slKl=sl[3].split(":");
				int slTime=Integer.parseInt(slKl[0]);
				int slMin=Integer.parseInt(slKl[1]);
				//stAvtale>sl og slAvtale>st
				
				if ((stAar == stAvtaleAar) && (stMaaned == stAvtaleMaaned) && (stDag == stAvtaleDag)){
					if (stAvtaleTime == stTime && stAvtaleMin > stMin){
						if (stAvtaleTime < slTime){
							ok = false;
						}
						else if (stAvtaleTime == slTime && stAvtaleMin < slMin){
							ok = false;
						}
					}
					
					
					if (stAvtaleTime == slTime && stAvtaleMin < slMin){
						if (stAvtaleTime > stTime){
							ok = false;
						}
						else if (stAvtaleTime == stTime && stAvtaleMin > stMin){
							ok = false;
						}
					}
					
					
					if (slAvtaleTime == stTime && slAvtaleMin > stMin){
						if (slAvtaleTime < slTime){
							ok = false;
						}
						else if (slAvtaleTime == slTime && slAvtaleMin < slMin){
							ok = false;
						}
					}
					
					
					if (slAvtaleTime == slTime && slAvtaleMin < slMin){
						if (slAvtaleTime > stTime){
							ok = false;
						}
						else if (slAvtaleTime == stTime && slAvtaleMin > stMin){
							ok = false;
						}
					}
				}
				
				
				System.out.println("Sjekket rom");
				System.out.println(ok);
			}
			return ok;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private int finnMaaned(String maaned){
		if(maaned.equals("Januar")){
			return 1;
		}else if(maaned.equals("Februar")){
			return 2;
		}else if(maaned.equals("Mars")){
			return 3;
		}else if(maaned.equals("April")){
			return 4;
		}else if(maaned.equals("Mai")){
			return 5;
		}else if(maaned.equals("Juni")){
			return 6;
		}else if(maaned.equals("Juli")){
			return 7;
		}else if(maaned.equals("August")){
			return 8;
		}else if(maaned.equals("September")){
			return 9;
		}else if(maaned.equals("Oktober")){
			return 10;
		}else if(maaned.equals("November")){
			return 11;
		}else if(maaned.equals("Desember")){
			return 12;
		}else{
			return -1;
		}
	}
}

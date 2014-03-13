package Fellesprosjektet;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Database {
	private String url = "jdbc:mysql://mysql.stud.ntnu.no/andresly_KalenderGruppe15";
	private String brukernavn ="andresly";
	private String passord = "FPGruppe15";
	private java.sql.Connection c;
	private java.sql.Statement st;
	private ResultSet rs;
	private String query;
	
	public Database() {
		try {
			c = DriverManager.getConnection(url, brukernavn, passord);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	// Denne metoden er bare for Œ sjekke at brukeren har et brukernavn #TEST
	public boolean checkUsername(String brukernavn) throws SQLException {
		st = c.createStatement();
		query = "SELECT brukernavn FROM Ansatt WHERE brukernavn = '" + brukernavn + "';";
		rs = st.executeQuery(query);
		if (rs.next()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// husk Œ sende deltagerliste og! (legg til i database)
	public void addAvtale(Avtale avtale) throws SQLException {
		st = c.createStatement();
		int id = avtale.getId();
		String beskrivelse = avtale.getBeskrivelse();
		String startTid = avtale.getStartTid();
		String sluttTid = avtale.getSluttTid();
		String adminBrukernavn = avtale.getLeder().getBrukernavn();
		String rom = avtale.getRom().getNavn();
		query = "INSERT INTO Avtale(avtaleId, beskrivelse, startTid, sluttTid, adminBrukernavn, romNr) VALUES('" 
		+id+"','"+beskrivelse+ "','" +startTid+ "','"+ sluttTid+ "','"+ adminBrukernavn +"','"+ rom+"');";
		st.executeUpdate(query);
	}
	
	// Ny ansatt - brukes til Œ registrere ny bruker hvis vi skal ha med det
	public void nyAnsatt(String brukernavn, String navn, String adresse, String telefon, String stilling, String passord) throws SQLException {
		st = c.createStatement();
		query = "INSERT INTO Ansatt(brukernavn, navn, adresse, telefon, stilling, passord) VALUES('" 
				+brukernavn+ "','" +navn+ "','"+ adresse+ "','"+ telefon +"','"+stilling+"','"+ passord+"');";
	}
	
	// NŒ blir deltagerne i avtalen invitert til avtalen, med -1 som bekreftet-status fordi de ikke har svart enda
	public void invitertTilAvtale(String ansattInvitert, int id) throws SQLException {
		st = c.createStatement();
		int b = -1;
		System.out.println("id som blir sendt til db: "+id);
		query = "INSERT INTO PersonDeltarAvtale(brukernavn, avtaleId, bekreftet) VALUES('"+ansattInvitert+"','"+id+"','"+b+"');";
		st.executeUpdate(query);
		
	}
	
	// Endre bekreftet-status hos en ansatt i personDeltarAvtale
	public void endreBekreftetStatus(Ansatt ansatt, Avtale avtale, int status) throws SQLException {
		st = c.createStatement();
		String brukernavn = ansatt.getBrukernavn().toLowerCase();
		int av = avtale.getId();
		query = "UPDATE PersonDeltarAvtale SET bekreftet='"+status+"' WHERE brukernavn= '"+brukernavn+"' AND avtaleID = '"+av+"');";
		st.executeUpdate(query);
	}
	
	public void fjerneAvtale (Avtale avtale) throws SQLException {
		st = c.createStatement();
		//if setningen kan brukes når vi har satt opp at vi kan sjekke hvem som er pålogget
		//String leder = avtale.getLeder().getBrukernavn();
		//if (vårtBrukernavn == leder){
			int av = avtale.getId();
			query = "DELETE FROM Avtale WHERE avtaleId= '" + av+ "';";
			st.executeUpdate(query);
		//}
	}

}

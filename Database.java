package Fellesprosjektet;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;

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
		query = "INSERT INTO PersonDeltarAvtale(brukernavn, avtaleId, bekreftet) VALUES('"+ansattInvitert+"','"+id+"','"+b+"');";
		st.executeUpdate(query);
		
	}
	
	// Endre bekreftet-status hos en ansatt i personDeltarAvtale
	public void endreBekreftetStatus(Ansatt ansatt, Avtale avtale, int status) throws SQLException {
		st = c.createStatement();
		String brukernavn = ansatt.getBrukernavn().toLowerCase();
		int av = avtale.getId();
		query = "UPDATE PersonDeltarAvtale SET bekreftet='"+status+"' WHERE brukernavn= '"+brukernavn+"' AND avtaleID = '"+av+"';";
		st.executeUpdate(query);
	}
	
	// Hent ansatte
	public DefaultListModel hentAnsatte() throws SQLException {
		st = c.createStatement();
		query = "SELECT * FROM Ansatt;";
		rs = st.executeQuery(query);
		DefaultListModel result = new DefaultListModel();
		
		while(rs.next()) {
			Ansatt ansatt = new Ansatt(rs.getString("brukernavn"));
			ansatt.setNavn(rs.getString("navn"));
			ansatt.setAdresse(rs.getString("adresse"));
			ansatt.setTelefon(rs.getString("telefon"));
			ansatt.setStilling(rs.getString("stilling"));
			ansatt.setPassord(rs.getString("passord"));
			result.addElement((Ansatt) ansatt);
        }
		return result;
	}
	
	// Hent en bestemt ansatt
	public Ansatt hentBestemtAnsatt(String brukernavn) throws SQLException {
		st = c.createStatement();
		query = "SELECT * FROM Ansatt WHERE brukernavn='"+brukernavn+"';";
		rs = st.executeQuery(query);
		Ansatt ansatt = new Ansatt("foorBrukernavn");
		
		while(rs.next()) {
			ansatt.setBrukernavn(rs.getString("brukernavn"));
			ansatt.setNavn(rs.getString("navn"));
			ansatt.setAdresse(rs.getString("adresse"));
			ansatt.setTelefon(rs.getString("telefon"));
			ansatt.setStilling(rs.getString("stilling"));
			ansatt.setPassord(rs.getString("passord"));
		}
		return ansatt;
	}
	
	
	// Hent alle rom
	
	// Hent bestemt rom

}

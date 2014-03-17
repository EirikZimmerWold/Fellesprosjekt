package Fellesprosjektet;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

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
	// Legg inn ny avtale
	public void setNyAvtale(Avtale avtale) throws SQLException {
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
	
	// Hent alle avtaler
	public DefaultListModel getAlleAvtaler() throws SQLException {
		st = c.createStatement();
		query = "SELECT * FROM Avtale;";
		rs = st.executeQuery(query);
		DefaultListModel result = new DefaultListModel();
		
		while(rs.next()) {
			String startTid = rs.getString("startTid");
			System.out.println("starttid: " + startTid);
			
			String sluttTid = rs.getString("sluttTid");
			System.out.println("slutttid: " + sluttTid);
			
			String beskrivelse = rs.getString("beskrivelse");
			
			Rom rom = getBestemtRom(rs.getString("romNr"));
			System.out.println(rom);
			
			int id = rs.getInt("avtaleId");
			System.out.println(id);
			
			//String admin = rs.getString("romNr");
			//System.out.println("Admin: " + admin);
			//Ansatt admin = getBestemtAnsatt(rs.getString("adminBrukernavn"));
			
			Avtale avtale = new Avtale(startTid, sluttTid, beskrivelse, rom, null);
			result.addElement((Avtale) avtale);
        }
		return result;
	}

	
	// Legg inn ny ansatt
	public void setNyAnsatt(String brukernavn, String navn, String adresse, String telefon, String stilling, String passord) throws SQLException {
		st = c.createStatement();
		query = "INSERT INTO Ansatt(brukernavn, navn, adresse, telefon, stilling, passord) VALUES('" 
				+brukernavn+ "','" +navn+ "','"+ adresse+ "','"+ telefon +"','"+stilling+"','"+ passord+"');";
	}
	
	// NŒ blir deltagerne i avtalen invitert til avtalen, med -1 som bekreftet-status fordi de ikke har svart enda
	public void setPersonDeltarAvtale(String ansattInvitert, int id) throws SQLException {
		st = c.createStatement();
		int b = -1;
		query = "INSERT INTO PersonDeltarAvtale(brukernavn, avtaleId, bekreftet) VALUES('"+ansattInvitert+"','"+id+"','"+b+"');";
		st.executeUpdate(query);
		
	}
	
	// Endre bekreftet-status hos en ansatt i personDeltarAvtale
	public void setBekreftetStatus(Ansatt ansatt, Avtale avtale, int status) throws SQLException {
		st = c.createStatement();
		String brukernavn = ansatt.getBrukernavn().toLowerCase();
		int av = avtale.getId();
		query = "UPDATE PersonDeltarAvtale SET bekreftet='"+status+"' WHERE brukernavn= '"+brukernavn+"' AND avtaleID = '"+av+"';";
		st.executeUpdate(query);
	}
	
	// Hent ansatte
	public DefaultListModel getAlleAnsatte() throws SQLException {
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
	public Ansatt getBestemtAnsatt(String brukernavn) throws SQLException {
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
	public JComboBox getAlleRom() throws SQLException {
		st = c.createStatement();
		query = "SELECT * FROM Rom;";
		rs = st.executeQuery(query);
		JComboBox result = new JComboBox();
		
		while(rs.next()) {
			Rom rom = new Rom(rs.getString("romNr"));
			rom.setMaksAntallPersoner(rs.getInt("maksAntallDeltagere"));
			result.addItem((Rom) rom);
        }
		return result;
		
	}
	
	// Hent bestemt rom
	
	public Rom getBestemtRom(String romNr) throws SQLException {
		st = c.createStatement();
		query = "SELECT * FROM Rom WHERE romNr='"+romNr+"';";
		rs = st.executeQuery(query);
		Rom rom = new Rom("foorRom");
		
		while(rs.next()) {
			rom.setNavn(rs.getString("romNr"));
			rom.setMaksAntallPersoner(rs.getInt("maksAntallDeltagere"));
		}
		return rom;
	}
	
	// Finn ut om en person er i en bestemt gruppe
	
	public boolean personHarGruppe(Gruppe gr, Ansatt ansatt) throws SQLException {
		st = c.createStatement();
		String brukernavn = ansatt.getBrukernavn();
		query = "SELECT brukernavn FROM GruppeHarMedlem WHERE brukernavn = '" + brukernavn + "';";
		rs = st.executeQuery(query);
		if (rs.next()) {
			return true;
		}
		else {
			return false;
		}
		
	}

	// Finn ut om en person er invitert i en bestemt avtale
	
	public boolean personDeltarAvtale(Ansatt ansatt, Avtale avtale) throws SQLException {
		st = c.createStatement();
		String brukernavn = ansatt.getBrukernavn();
		int avtaleId = avtale.getId();
		query = "SELECT brukernavn FROM PersonDeltarAvtale WHERE brukernavn = '" + brukernavn + "' AND avtaleId = '" +avtaleId + "';";
		rs = st.executeQuery(query);
		if (rs.next()) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void setVarsel(Avtale avtale, int varseltidFoorAvtale, Ansatt ansatt) throws SQLException {
		st = c.createStatement();
		int id = avtale.getId();
		String brukernavn = ansatt.getBrukernavn();
		int varselId = 45;
		query = "INSERT INTO Varsel(varselId, varselTidFoorAvtale, brukernavn, avtaleId) VALUES('"+varselId+"','"+varseltidFoorAvtale+"','"+brukernavn+"','"+id+"');";
		st.executeUpdate(query);
	}

}

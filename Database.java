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
	private ResultSet rs2;
	private ResultSet rs3;
	private String query;
	private Ansatt deltager;
	
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
		while (rs.next()) {
			int id = rs.getInt("avtaleId");
			String beskrivelse = rs.getString("beskrivelse");
			String startTid = rs.getString("startTid");
			String sluttTid = rs.getString("sluttTid");
			String admin = rs.getString("adminBrukernavn");
			String rom1 = rs.getString("romNr");
			
			Rom rom = getBestemtRom(rom1);
			Ansatt ansatt = getBestemtAnsatt(admin);
			
			//Avtale avtale = new Avtale(startTid, sluttTid, beskrivelse, rom, ansatt);
			//result.addElement((Avtale) avtale);
			
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
		query = "UPDATE PersonDeltarAvtale SET bekreftet='"+status+"' WHERE brukernavn= '"+brukernavn+"' AND avtaleID = '"+av+"');";
		st.executeUpdate(query);
	}
	
	//fjerner valgt avtale
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
	
	//gir en liste av alle deltagere i gitte avtalen
	public DefaultListModel alleDeltagere(int avtaleid) throws SQLException{
		st = c.createStatement();
		int avi = avtaleid;
		DefaultListModel<Ansatt> result= new DefaultListModel<Ansatt>();
		query = "SELECT brukernavn FROM PersonDeltarAvtale WHERE avtaleId= '"+ avi+"';";
		rs2 = st.executeQuery(query);
		while (rs2.next()){
			deltager = getBestemtAnsatt(rs2.getString("brukernavn"));
			result.addElement(deltager);
		}
		return result;
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
	
	//setter inn varsel i databasen
	public void setVarsel(Avtale avtale, int varseltidFoorAvtale, Ansatt ansatt) throws SQLException {
		st = c.createStatement();
		int id = avtale.getId();
		String brukernavn = ansatt.getBrukernavn();
		int varselId = 45;
		query = "INSERT INTO Varsel(varselId, varselTidFoorAvtale, brukernavn, avtaleId) VALUES('"+varselId+"','"+varseltidFoorAvtale+"','"+brukernavn+"','"+id+"');";
		st.executeUpdate(query);
	}
	
	//henter passordet til avtalen
	public String getPassord(String brukernavn) throws SQLException{
		st=c.createStatement();
		query="SELECT passord FROM Ansatt WHERE brukernavn='"+brukernavn+"';";
		rs = st.executeQuery(query);
		String string="";
		while(rs.next()){
			string = rs.getString("passord");
		}
		return string;
	}
	
	//henter navnet når man har et brukernavn
	public String getNavn(String brukernavn) throws SQLException{
		st=c.createStatement();
		query="SELECT navn FROM Ansatt WHERE brukernavn='"+brukernavn+"';";
		rs = st.executeQuery(query);
		String string="";
		while(rs.next()){
			string = rs.getString("navn");
		}
		return string;
	}

	public Avtale getBestemtAvtale(int avtaleid) throws SQLException {
		st = c.createStatement();
		query = "SELECT * FROM Avtale WHERE AvtaleId='"+avtaleid+"';";
		rs3 = st.executeQuery(query);
	
		Avtale avtale = new Avtale("","","",new Rom("midlertidig"),new DefaultListModel(),new Ansatt("midlertidig"));
		while(rs3.next()) {
			String st = (rs3.getString("startTid"));
			avtale.setStartTid(st);
			String sl = (rs3.getString("startTid"));
			avtale.setSluttTid(sl);
			String besk = (rs3.getString("startTid"));
			avtale.setBeskrivelse(besk);
			Rom rom = getBestemtRom(rs3.getString("startTid"));
			avtale.setRom(rom);
			Ansatt leder = getBestemtAnsatt(rs3.getString("adminBrukernavn"));
			avtale.setLeder(leder);
			DefaultListModel model = alleDeltagere(avtaleid);
			avtale.setModel(model);
		}	
		return avtale;
	}
	//gir string typen 1-13-12-6 hvor hvert tall er en avtaleId den ansatte deltar i
	public String avtalerPersonErMed(Ansatt ansatt) throws SQLException {
		st = c.createStatement();
		String brukernavn = ansatt.getBrukernavn();
		query = "SELECT avtaleId FROM PersonDeltarAvtale WHERE brukernavn = '"
		+ brukernavn + "' AND bekreftet = -1;";
		rs = st.executeQuery(query);
		String IDene ="";
		while(rs.next()) {
			IDene += "-"+ rs.getString("avtaleId");
		}
		return IDene;
	}
	
}




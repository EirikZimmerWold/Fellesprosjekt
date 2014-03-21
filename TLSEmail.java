package Fellesprosjektet;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import java.util.Properties;

public class TLSEmail {
	/**
    Outgoing Mail (SMTP) Server
    requires TLS or SSL: smtp.gmail.com (use authentication)
    Use Authentication: Yes
    Port for TLS/STARTTLS: 587
	 */
	
	private String fromEmail = "fpgruppe15@gmail.com"; // requires valid gmal id
	private String password = "51eppurgpf"; // correct password gmail id
	private String toEmail; // any email id
	private Avtale avtale;
	
	public void sendEmail(String toemail, String byUsername, String hostMail, Avtale avtale){
		toEmail = toemail;
		this.avtale=avtale;
		
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com"); // SMTP host
		prop.put("mail.smtp.port", "587"); // TLS port
		prop.put("mail.smtp.auth", "true"); // enable authentication
		prop.put("mail.smtp.starttls.enable", "true");  // enable STARTTLS
		
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator(){
			//override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
            	return new PasswordAuthentication(fromEmail, password);
            }
		};
		
		Session session = Session.getInstance(prop, auth);
		// change the value of subject and body as you want
		if(avtale.getRom()==null){
			String body = "Du har blitt invitert til en avtale av: "+ byUsername + "\n"+
					"Tid: "+avtale.getStartTid()+" - "+avtale.getSluttTid()+"\n"+
					"Sted: "+avtale.getSted()+"\n"+
					"Beskrivelse: "+avtale.getBeskrivelse();
			EmailHandlerUtil.sendEmail(session, toEmail, "M�teinvitasjon", body);
		}else{
			String body = "Du har blitt invitert til en avtale av: "+ byUsername + "\n"+
					"Tid: "+avtale.getStartTid()+" - "+avtale.getSluttTid()+"\n"+
					"Rom: "+avtale.getRom()+"\n"+
					"Beskrivelse: "+avtale.getBeskrivelse();
			EmailHandlerUtil.sendEmail(session, toEmail, "M�teinvitasjon", body);
		}
	}

}

package EmailHandler;

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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		TLSEmail ts = new TLSEmail();
		ts.sendEmail("pingandhispong@hotmail.com", "Ida");
		
		
	}
	
	private String fromEmail = "fpgruppe15@gmail.com"; // requires valid gmal id
	private String password = "51eppurgpf"; // correct password gmail id
	private String toEmail; // any email id
	
	public void sendEmail(String toemail, String byUsername){
		toEmail = toemail;
		
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
		String body = "Du har blitt til en avtale av: "+ byUsername + "\n" +"Svar på invitasjonen til: " + toemail;
		EmailHandlerUtil.sendEmail(session, toEmail, "Møteinvitasjon", body);
	}

}

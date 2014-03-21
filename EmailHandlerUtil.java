package Fellesprosjektet;

import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailHandlerUtil {
	/**
	 * Utility method to send simple HTML email
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	
		public static void sendEmail(Session session, String toEmail, String subject, String body){
			try{
				MimeMessage msg = new MimeMessage(session);
				// set message handler
				msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
				msg.addHeader("format", "flowed");
				msg.addHeader("Content-Transfer-Encodeing", "8-bit");
				
				msg.setFrom(new InternetAddress("fpgruppe15@gmail.com", "NoReply-FPG15"));
				msg.setReplyTo(InternetAddress.parse("no_reply@journaldev.com",false));
				
				msg.setSubject(subject);
				msg.setText(body, "UTF-8");
				
				msg.setSentDate(new Date());
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail,false));
				Transport.send(msg);
				
			} catch ( Exception e){
				e.printStackTrace();
			}
		}
}

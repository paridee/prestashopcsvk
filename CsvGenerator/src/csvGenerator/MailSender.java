package csvGenerator;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	Session session;
	
	public MailSender(){
		final String fromEmail = "noreply@eclshop.tv"; //requires valid gmail id
		final String password = "193ofQf279"; // correct password for gmail id
		final String toEmail = "paride.casulli@gmail.com,paride.casulli@capgemini.com"; // can be any email id 
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "secure.alien8.it"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
		
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
		//sendEmail(session,toEmail,"test","prova");
	}
	
	/**
	 * Utility method to send simple HTML email
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public void sendEmail(String toEmail, String subject, String body){
		try
	    {		
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("noreply@printsolutionsroma.com", "No Reply PrintSolutionsRoma"));

	      msg.setReplyTo(InternetAddress.parse("noreply@printsolutionsroma.com", false));

	      msg.setSubject(subject, "UTF-8");

	      msg.setText(body, "UTF-8");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      System.out.println("Message is ready");
    	  Transport.send(msg);  
	      System.out.println("EMail Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}
	public static void main(String[] args) {
		MailSender test	=	new MailSender();
	}
}

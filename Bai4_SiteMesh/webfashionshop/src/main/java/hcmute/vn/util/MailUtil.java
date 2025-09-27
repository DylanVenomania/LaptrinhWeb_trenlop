package hcmute.vn.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil
{
	private static final String FROM_EMAIL = "hoangcamton.@gmail.com"; // Thay bằng email Gmail của bạn
    private static final String APP_PASSWORD = "cam77810509moi"; // Thay bằng App Password từ Google
    
    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException 
    {
    	
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() 
        {
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });
      
        
        Message message = new MimeMessage( session );
        message.setFrom( new InternetAddress(FROM_EMAIL) );
        InternetAddress[] addresses = InternetAddress.parse(toEmail.trim(), false);
        message.setRecipients(Message.RecipientType.TO, addresses);
        message.setSubject(subject);
        message.setText( body );

        Transport.send(message);
    }
}
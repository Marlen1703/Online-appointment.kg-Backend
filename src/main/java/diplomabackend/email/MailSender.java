package diplomabackend.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class MailSender {

    @Value("${gmail.user}")
    private String gmailUser;

    @Value("${gmail.password}")
    private String gmailPassword;

    public void sendMessage(String toWhom,String policy){
    // Recipient's email ID needs to be mentioned.
    String to = toWhom;

    // Sender's email ID needs to be mentioned
    String from = gmailUser;

    // Assuming you are sending email from through gmails smtp
    String host = "smtp.gmail.com";

    // Get system properties
    Properties properties = System.getProperties();

    // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

    // Get the Session object.// and pass username and password
    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

        protected PasswordAuthentication getPasswordAuthentication() {

            return new PasswordAuthentication(gmailUser, gmailPassword);

        }

    });
    // Used to debug SMTP issues
        session.setDebug(true);

        try {

        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);


        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));


        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        // Set Subject: header field
        message.setSubject("Здравствуйте, вам открыт полис");


        // Now set the actual message
        message.setText("Ваш полис - "+policy);

        System.out.println("sending...");

        // Send message
        Transport.send(message);
        System.out.println("Sent message successfully....");


    } catch (MessagingException mex) {
        mex.printStackTrace();
    }

}
}

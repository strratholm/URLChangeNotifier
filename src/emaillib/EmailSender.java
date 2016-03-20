package emaillib; /**
 * Created by strratholm on 20.03.2016.
 */

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    private String email;
    private Session session;

    public EmailSender(String mailHost, String email, String password) {
        this.email = email;
        session = Email.getSession(Email.getSMTPProp(mailHost), email, password);
    }

    public void send(String subject, String text, String toEmail){
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

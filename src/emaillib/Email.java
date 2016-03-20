package emaillib; /**
 * Created by strratholm on 20.03.2016.
 */

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Email {

    static Properties getSMTPProp(String mailHost) {
        Properties prop = new Properties();

        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp." + mailHost);
        prop.put("mail.smtp.port", "587");

        return prop;
    }

    static Session getSession(Properties prop, String email, String password) {
        return Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }});
    }

    public static boolean checkEmail(String email) {
        //sorry for not using monstrous regex
        return email.contains("@");
    }


}

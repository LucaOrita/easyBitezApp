package ro.easybites.app.logic;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailSender {

    private final String to;
    private final String subject;
    private final String msj;

    public MailSender(String to, String subject, String msj) {
        this.to = to;
        this.subject = subject;
        this.msj = msj;
    }

    public boolean sendEmail() {
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.smtp.host", "smtp.gmail.com");
            prop.setProperty("mail.smtp.port", "465");
            prop.setProperty("mail.smtp.auth", "true");
            prop.setProperty("mail.smtp.ssl.enable", "true");

            Session sess = Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(MailCreditentials.EMAIL.getValue(), MailCreditentials.PASS.getValue());
                }
            });

            sess.setDebug(true);

            Message msg = new MimeMessage(sess);

            msg.setFrom(new InternetAddress(this.to));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
            msg.setSubject(this.subject);
            msg.setText(msj);

            Transport.send(msg);
            return true;
        } catch (MessagingException msgEx) {
//            msgEx.printStackTrace();
            System.out.println("exception");
            return false;
        }
    }
}

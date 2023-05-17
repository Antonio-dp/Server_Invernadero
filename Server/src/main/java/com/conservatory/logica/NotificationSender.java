package com.conservatory.logica;

import Entidades.Alarma;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Session;
public class NotificationSender {
    public NotificationSender() {
    }

    public void notificarCorreo(Alarma alarma){
        String from= "xlfaceboot";
        String pass = "abzdebuipdrbugnq";
        String subject = "PELIGRO";

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(alarma.getCorreo());
            message.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setText("Una alarma se ha activado: "+ alarma.getTipo());
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }

}

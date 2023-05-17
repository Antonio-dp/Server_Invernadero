package com.conservatory.logica;

import Entidades.Alarma;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Session;
import java.util.logging.Level;
import java.util.logging.Logger;
public class NotificationSender {
    /**
     * Session
     */
    private Session session;
    /**
     * Propiedades
     */
    private Properties properties;

    public NotificationSender() {
        this.properties = new Properties();
        this.session = Session.getDefaultInstance(properties);
    }
    /**
     * Propiedades del notificador
     */
    public void llenarProperties() {
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.user", "fakme3364@gmail.com");
        properties.setProperty("mail.smtp.auth", "true");
    }
    public void notificar(Alarma alarma) {
        MimeMessage contenedor = new MimeMessage(session);
        try {
            contenedor.setFrom(new InternetAddress((String) this.properties.get("mail.smtp.user")));
            contenedor.addRecipient(Message.RecipientType.TO, new InternetAddress(alarma.getCorreo()));
            contenedor.setSubject("Notificación Invernadero");
            contenedor.setText("PELIGRO: limite de " + alarma.getTipo());
            Transport t = session.getTransport("smtp");
            t.connect((String) this.properties.get("mail.smtp.user"), "iweuucvrydutylkg"); //Para conseguir la contraseñase debe activar la verificación po 2 pasos y Agregar contraseña para Aplicación
            t.sendMessage(contenedor, contenedor.getAllRecipients());
        } catch (Exception ex) {
            Logger.getLogger(NotificationSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

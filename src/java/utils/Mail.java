/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package utils;

import java.util.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;

/**
 *
 * @author Mountain
 */
public class Mail {

    public void SendMail(String mail, String pass) throws AddressException, MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        Message mailMessage;
        final String username = "sonkahe161888@fpt.edu.vn";
        final String password = "kieuanhson02";
        mailServerProperties = new Properties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(mailServerProperties,
                new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        mailMessage = new MimeMessage(session);
        mailMessage.setFrom(new InternetAddress(username));
        mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
        mailMessage.setSubject("Reset password");
        mailMessage.setText(pass);
        Transport.send(mailMessage);
    }
}

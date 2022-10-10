/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package utils;

import java.util.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mountain
 */
public class Mail {

    public boolean SendMail(String mail, String pass){
        try {
            Properties mailServerProperties;
            Message mailMessage;
            final String emailFrom = "sonkienthuong@gmail.com";
            final String username = "fcf1d8057a8f8e";
            final String password = "53f7eb98b02306";
            mailServerProperties = new Properties();
            mailServerProperties.put("mail.smtp.port", "2525");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.host", "smtp.mailtrap.io");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            
            Session session = Session.getInstance(mailServerProperties,
                    new jakarta.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            mailMessage = new MimeMessage(session);
            mailMessage.setFrom(new InternetAddress(emailFrom));
            mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            mailMessage.setSubject("Reset password");
            mailMessage.setText(pass);
            Transport.send(mailMessage);
            return true;
        } catch (AddressException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

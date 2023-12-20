package api.methods;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.*;
import java.lang.*;
import java.io.*;

public class EmailSender {

    public boolean sendEmail(String to, String from, String subject, String text, File file) {
        boolean flag = false;
        String senderEmail = "shalugavendra@gmail.com";
        String senderPassword = "wmuq wywz tjxe edla";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");



        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail,senderPassword);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(from));

            // Set To: header field
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            MimeBodyPart part1 = new MimeBodyPart();
            part1.setText(text);

            MimeBodyPart part2 = new MimeBodyPart();
            part2.attachFile(file);

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(part1);
            multipart.addBodyPart(part2);

            message.setContent(multipart);

            // Send message
            Transport.send(message);
            flag = true;
        }
         catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
}
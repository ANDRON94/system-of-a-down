package com.util;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;


public class MailUtil {
    public static boolean sendMail(String recipientEmail, String subject, String message){
// SSL // I USED THIS METHOD
        Properties propsSSL = new Properties();

// EVEN IF YOU SKIP THESE TWO PROP IT WOULD WORK
        propsSSL.put("mail.transport.protocol", "smtps");
        propsSSL.put("mail.smtps.host", "smtp.gmail.com");

// THIS IS THE MOST IMPORTANT PROP —> "mail.smtps.auth"
        propsSSL.put("mail.smtps.auth", "true");
        Session sessionSSL = Session.getInstance(propsSSL);
        sessionSSL.setDebug(true);
        Message messageSSL = new MimeMessage(sessionSSL);

        Transport transportSSL = null;
        try {
            messageSSL.setFrom(new InternetAddress("is.forum.1@gmail.com"));
            messageSSL.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // real recipient
            messageSSL.setSubject(subject);
            messageSSL.setContent(message,"text/html; charset = utf-8");
            messageSSL.setText(message);
            transportSSL = sessionSSL.getTransport();
            transportSSL.connect("smtp.gmail.com", 465, "is.forum.1@gmail.com", "7890uiopjkl;"); // account used
            transportSSL.sendMessage(messageSSL, messageSSL.getAllRecipients());
            transportSSL.close();
        } catch (NoSuchProviderException e) {
            return false;
        } catch (MessagingException e) {
            return false;
        }
// EVEN IF YOU SKIP PORT NUMBER , IT WOULD WORK

        return true;
    }
    public static String hash(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            String salt = "some_random_salt";
            String passWithSalt = password + salt;
            byte[] passBytes = passWithSalt.getBytes();
            byte[] passHash = sha256.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< passHash.length ;i++) {
                sb.append(Integer.toString((passHash[i] & 0xff) + 0x100, 16).substring(1));
            }
            String generatedPassword = sb.toString();
            return generatedPassword;
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return null;
    }
}

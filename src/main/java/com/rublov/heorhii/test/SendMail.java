package com.rublov.heorhii.test;

/**
 * @author Rublev Heorhii
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * before using a google account you need to torn on Less secure app by \
 * link: https://myaccount.google.com/lesssecureapps
 *
 * settings with SSL:
 * props.put("mail.smtp.host", "smtp.gmail.com");
 * props.put("mail.smtp.socketFactory.port", "465");
 * props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
 * props.put("mail.smtp.auth", "true");
 * props.put("mail.smtp.port", "465");
 *
 * setting without SSL:
 * props.put("mail.smtp.auth", "true");
 * props.put("mail.smtp.starttls.enable", "true");
 * props.put("mail.smtp.host", "smtp.gmail.com");
 * props.put("mail.smtp.port", "587");
 *
 */

public class SendMail {
    /**
     *
     * @param to
     * @param from
     * @param password
     */
    public void send(String to, final String from, final String password){

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(from, password);
           }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Test");
            message.setText("Test Test Test");
            Transport.send(message);
            System.out.println("Done!");
        }catch (MessagingException e){
            System.out.println(e);
        }

    }
}

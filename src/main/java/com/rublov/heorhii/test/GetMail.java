package com.rublov.heorhii.test;

/**
 * @author Rublev Heorhii
 */


import sun.util.calendar.LocalGregorianCalendar;
import sun.util.calendar.LocalGregorianCalendar.Date;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

/**
 * before using a google account you need to torn on Less secure app by \
 * link: https://myaccount.google.com/lesssecureapps
 */

public class GetMail {

    /**
     *
     * @param from
     * @param password
     */
    public void getMails(String from, String password){

        Properties props = new Properties();
        props.put("mail.pop3.host", "pop.gmail.com");
        props.put("mail.pop3.port", "995");
//        props.put("mail.pop3.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, password);
            }
        });



        try {
            Store store = session.getStore("imaps");
            store.connect(props.getProperty("mail.pop3.host"), from, password);
            Folder folder = store.getFolder("inbox");
            folder.open(Folder.READ_ONLY);
            Message[] messages = folder.getMessages();
            System.out.println("messages.length---" + messages.length);
            for(int mes = messages.length-1; mes>=0; mes--){
                Message message = messages[mes];
                System.out.println("----------------" + message.getMessageNumber()+ "---------------------");
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Subject: " + message.getSubject());
                System.out.println("Date: " + message.getSentDate());
                if(message.getContent().getClass() == javax.mail.internet.MimeMultipart.class){
                    System.out.println("Text Multiple: " + message.getContent());
                }else{
                    System.out.println("Text Simple: " + message.getContent().toString());
                }
                System.out.println();
            }

            folder.close();
            store.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

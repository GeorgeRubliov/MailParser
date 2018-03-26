package com.rublov.heorhii.test;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class SendAndTakeMail {

    public List<Message> takeMail(final String out, final String password, String subject){
        List<Message> allMessages = new ArrayList<>();
        Properties props = new Properties();
        props.put("mail.pop3.host", "imap.gmail.com");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(out, password);
            }
        });
        try{
            Store store = session.getStore("imaps");
            store.connect(props.getProperty("mail.pop3.host"), out, password);
            Folder folder = store.getFolder("inbox");
            folder.open(Folder.READ_ONLY);
            List<Message> messages = Arrays.asList(folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false)));
            for(Message message: messages){
                if(message.getSubject().contains(subject)){
                    allMessages.add(message);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return allMessages;
    }

    public void sendMail(final String out, final String to, final String password){


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(out, password);
           }
        });
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(out));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Test");
            message.setText("Test Test Test");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            DataSource source  = new FileDataSource("test.xlsx");
            mimeBodyPart.setDataHandler(new DataHandler(source));
            mimeBodyPart.setFileName("test.xlsx");
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            File file = new File("test.xlsx");
            file.delete();
        }catch (MessagingException e){
            System.out.println(e);
        }
    }

    public List<String> getText(List<Message> messageList) {
        List<String> textList = new ArrayList<>();
        for(int i=0; i<messageList.size(); i++){
            try {
                textList.add(readMime((Multipart) messageList.get(i).getContent()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return textList;
    }

    private String readMime(Multipart content) throws MessagingException {
        String text = null;
        for (int x = 0; x < content.getCount(); x++) {
            BodyPart bodyPart = content.getBodyPart(x);
            String disposition = bodyPart.getDisposition();
            if (disposition != null && (disposition.equals(BodyPart.ATTACHMENT))) {
                System.out.println("Mail have some attachment : ");
                DataHandler handler = bodyPart.getDataHandler();
                System.out.println("file name : " + handler.getName());
            } else {
                try {
                    text = String.valueOf(bodyPart.getContent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return text;
    }

    public List<String> clearFromHTML(List<String> textListWithHTML) {
        List<String> clearText = new LinkedList<>();
        for(int i=0; i<textListWithHTML.size(); i++){
//            Document doc = Jsoup.parse(textListWithHTML.get(i));
//            clearText.add(doc.body().text());
            String temp = textListWithHTML.get(i).replaceAll("\\<[^>]*>","");
            clearText.add(temp);
        }
        return clearText;
    }

}

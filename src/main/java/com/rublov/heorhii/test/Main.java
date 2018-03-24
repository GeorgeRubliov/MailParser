package com.rublov.heorhii.test;

import javax.mail.Message;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String from = args[0];
        String to = args[1];
        String password = args[2];

        SendAndTakeMail mail = new SendAndTakeMail();
        List<Message> messageList = mail.takeMail(from, password, "Test Plexsupply");
        List<String> textList = mail.getText(messageList);
        textList = mail.clearFromHTML(textList);
        List<MessageMail> messageMails = mail.separateByLines(textList);
        if(messageMails.isEmpty() || messageMails == null){
            System.out.println("no new messages");
        }else{
            MakeXMLFile mxml = new MakeXMLFile();
            mxml.make(messageMails);
            mail.sendMail(from, to, password);
        }


    }

}

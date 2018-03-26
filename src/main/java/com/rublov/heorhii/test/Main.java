package com.rublov.heorhii.test;

import com.rublov.heorhii.test.loaders.StartPageLoader;
import com.rublov.heorhii.test.separator.TextToMassive;
import com.rublov.heorhii.test.separator.ToXML;

import javax.mail.Message;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        StartPageLoader.launch(StartPageLoader.class, args);
//        String from = args[0];
//        String to = args[1];
//        String password = args[2];

//        SendAndTakeMail mail = new SendAndTakeMail();
//        List<Message> messageList = mail.takeMail(from, password, "Test Plexsupply");
//        List<String> textList = mail.getText(messageList);
//        textList = mail.clearFromHTML(textList);
//        TextToMassive textToMassive = new TextToMassive();
//
//
//        if(messageList.isEmpty() || messageList == null){
//            System.out.println("no new messages");
//        }else{
//            new ToXML().make(textToMassive.separate(textList));
//            mail.sendMail(from, to, password);
//        }


    }

}

package com.rublov.heorhii.test;

import javax.mail.MessagingException;

public class Main {

    public static void main(String[] args) {
        String from = args[0];
        String to = args[1];
        String password = args[2];
        System.out.println(from + "  " + to + "   " +password);
        new SendMail().send(to, from,password);
//        GetMail mail = new GetMail();

//            mail.getMails(from, password);

    }

}

package com.rublov.heorhii.test;

import java.util.ArrayList;
import java.util.List;

public class MessageMail {

    String[] allText = null;
    List<List<String>> lists;

    public MessageMail() {
        this.lists = new ArrayList<>();
    }

    public String[] getAllText() {
        return allText;
    }

    public List<List<String>> getLists() {
        return lists;
    }

    public void setLists(List<List<String>> lists) {
        this.lists = lists;
    }

    public void setAllText(String[] allText) {
        this.allText = allText;
    }



    public void printLists(){
        for(int i=0; i<lists.size(); i++){
            System.out.println("Line # "+i);
            for(int a=0; a<lists.get(i).size(); a++){
                System.out.println("cell # "+a+": "+lists.get(i).get(a));
            }
        }
    }

    @Override
    public String toString() {
        return "MessageMail{" +
                "lists=" + lists +
                '}';

    }
}

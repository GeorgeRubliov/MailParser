package com.rublov.heorhii.test.separator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TextToMassive {

    public List<Message> separate(List<String> text){
        List<Message> messages = new LinkedList<>();
        for(int t=0; t<text.size(); t++ ){
            String tx = text.get(t);
            List<Node> nodeList = new LinkedList<>();
            int baseLine=0;
            String[] textByMassive = tx.split("\n");
            for(int i=0; i<textByMassive.length; i++){
                if(textByMassive[i].charAt(0) == '-'){
                    baseLine=i;
                    break;
                }
            }
            int start =0, end=0;
            for(int s=0; s<textByMassive[baseLine].length(); s++){
                if(textByMassive[baseLine].charAt(s) == ' '){
                    end=s;
                    Node node = new Node(start,end);
                    start = ++s;
                    nodeList.add(node);
                }
            }
            nodeList.add(new Node(start,textByMassive[baseLine].length()-1));

            messages.add(new Message(baseLine,nodeList,textByMassive));
        }
        return messages;
    }
}

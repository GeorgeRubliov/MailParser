package com.rublov.heorhii.test;

import java.util.ArrayList;
import java.util.List;

public class Invoice {

    private List<String> list = new ArrayList<>();

    public Invoice() {
        list = new ArrayList<>(10);
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void printList(){
        for(int i=0; i<list.size();i++){
            System.out.println(i+". "+list.get(i));
        }
    }
}

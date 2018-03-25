package com.rublov.heorhii.test.separator;

import java.util.List;

public class Message {
    int baseLine;
    List<Node> nodeList;
    String[] text;

    public Message() {
    }


    public Message(int baseLine, List<Node> nodeList, String[] text) {
        this.baseLine = baseLine;
        this.nodeList = nodeList;
        this.text = text;
    }

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public Message(int baseLine, List<Node> nodeList) {
        this.baseLine = baseLine;
        this.nodeList = nodeList;
    }

    public int getBaseLine() {
        return baseLine;
    }

    public void setBaseLine(int baseLine) {
        this.baseLine = baseLine;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

}

package com.rublov.heorhii.test.FXMLControllers;


import com.rublov.heorhii.test.SendAndTakeMail;
import com.rublov.heorhii.test.separator.TextToMassive;
import com.rublov.heorhii.test.separator.ToXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import javax.mail.Message;
import java.util.List;


public class StartPageController {

    @FXML
    TextField outMail;

    @FXML
    PasswordField password;

    @FXML
    TextField subject;

    @FXML
    TextField inputMail;

    @FXML
    Label statusInfo;

    @FXML
    Button startButton;

    public void start(ActionEvent actionEvent) {
        startButton.setVisible(false);
        statusInfo.setText("Connecting to GMAIL to account "+outMail.getText());
        SendAndTakeMail mail = new SendAndTakeMail();
        List<Message> messageList = mail.takeMail(outMail.getText(), password.getText(), subject.getText());
        statusInfo.setText("not read mails from account "+outMail.getText()+" " +messageList.size());
        List<String> textList = mail.getText(messageList);
        statusInfo.setText("Bodies of mails got");
        textList = mail.clearFromHTML(textList);
        statusInfo.setText("Text to massive");
        TextToMassive textToMassive = new TextToMassive();
        if(messageList.isEmpty() || messageList == null){
            statusInfo.setText("No new messages");
        }else{
            statusInfo.setText("Making excess file");
            new ToXML().make(textToMassive.separate(textList));
            statusInfo.setText("mail was sand");
            mail.sendMail(outMail.getText(), inputMail.getText(), password.getText());
            statusInfo.setText("Done!");
        }
        startButton.setVisible(true);
    }

    @FXML
    private void handleOnKeyReleased(KeyEvent event)
    {
        switch (event.getCode()){
            case ENTER:{
                start(new ActionEvent());
                break;
            }
        }
    }

}

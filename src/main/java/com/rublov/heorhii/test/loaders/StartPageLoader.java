package com.rublov.heorhii.test.loaders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartPageLoader extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StartPage.fxml"));
        primaryStage.setTitle("Mail Parser");
        Parent parent = (Parent)loader.load();
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

    }
}

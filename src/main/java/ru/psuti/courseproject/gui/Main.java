package ru.psuti.courseproject.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Генератор случайных велечин");
        FXMLLoader loader = new FXMLLoader();
        Parent rootLayout = loader.load(getClass().getResourceAsStream("/ru/psuti/courseproject/gui/sample.fxml"));
        Controller controller = loader.getController();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

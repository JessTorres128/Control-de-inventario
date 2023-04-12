package com.example.controldeinventario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage primarystage;
    @Override
    public void start(Stage stage) throws IOException {
        primarystage =stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Inventario");
        stage.setScene(scene);
        stage.show();

        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}
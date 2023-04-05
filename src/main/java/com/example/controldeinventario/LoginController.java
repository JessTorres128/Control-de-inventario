package com.example.controldeinventario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
@FXML TextField txtuser;
@FXML TextField txtpassword;
Conexion conexion;

@FXML protected void initialize(){
    conexion=new Conexion();
}

@FXML private void IngresarLogin(ActionEvent event) throws IOException, SQLException {
    String user = txtuser.getText();
    String pass = txtpassword.getText();

    ResultSet resultado = conexion.consultar("select * from usuarios where username='"+user+"' and password='"+pass+"'");
    if (resultado != null){
        int cont =0;
        if (resultado.next()){cont++;}

        if(cont==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("Datos incorrectos");
            alert.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("principal.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 550, 400);
            HelloApplication.primarystage.setX(100);
            HelloApplication.primarystage.setY(50);
            HelloApplication.primarystage.setTitle("Pantalla principal");
            HelloApplication.primarystage.setScene(scene);
            HelloApplication.primarystage.setResizable(false);
        }

    }
}

}
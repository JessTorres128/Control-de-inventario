package com.example.controldeinventario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalController {
    @FXML MenuItem menuItemB;

    @FXML protected void initialize(){
       // Image imgSearch= new Image("",25,25,false,true);menuItemB.setGraphic(new ImageView(imgSearch));
    }






    //Controles del menú
    @FXML private void IngresarArticulos(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Pedidos.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);

    }
    @FXML private void IngresarHerramientas(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Herramientas.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);

    }
    @FXML private void IngresarLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);

    }
    @FXML private void IngresarRoles(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Roles.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);

    }
    @FXML private void IngresarEmpleados(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Empleados.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);

    }
    @FXML private void IngresarPedidos(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Pedidos.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);

    }

    private void AbrirVentana(Parent root){
        Stage ventanaSecundaria = new Stage();
        ventanaSecundaria.initModality(Modality.APPLICATION_MODAL);
        ventanaSecundaria.initOwner(HelloApplication.primarystage);
        ventanaSecundaria.setScene(new Scene(root));
        ventanaSecundaria.show();
    }
}

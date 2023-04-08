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
        Image imgSearch= new Image("",25,25,false,true);
        menuItemB.setGraphic(new ImageView(imgSearch));
    }






    //Controles del menú
    @FXML private void ingresarPedidos(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Pedidos.fxml"));
        Parent root = fxmlLoader.load();

        Stage ventanaSecundaria = new Stage();
        ventanaSecundaria.initModality(Modality.APPLICATION_MODAL);
        ventanaSecundaria.initOwner(HelloApplication.primarystage);
        ventanaSecundaria.setScene(new Scene(root));
        ventanaSecundaria.show();
    }
}

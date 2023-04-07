package com.example.controldeinventario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalController {







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

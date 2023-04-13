package com.example.controldeinventario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.ResultSet;
import java.util.Random;

public class HerramientasController {
    Conexion conexion;
@FXML Button btnNew, btnSave, btnEdit, btnCancel, btnExit;
@FXML TabPane tabV;
@FXML Tab tabSearch, tabNew;

@FXML protected void initialize(){
    conexion = new Conexion();
 /*   btnNew.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnSave.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnEdit.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnCancel.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnExit.setGraphic(new ImageView(new Image("",25,25,false,true))); */

}





@FXML private void NewHerramienta(ActionEvent event){
GenerateNumber();
tabV.getSelectionModel().select(tabNew);

}

private Long GenerateNumber(){
    Random random=new Random();
    long numero = (long)(random.nextDouble()*10000000000L);
    while(!String.valueOf(numero).matches("\\d{10}")){
        numero = (long)(random.nextDouble()*10000000000L);
        System.out.println(numero);
    }
    ResultSet resultSet = conexion.consultar("select *");


    return null;
}
}

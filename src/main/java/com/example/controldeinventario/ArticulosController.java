package com.example.controldeinventario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ArticulosController {
    @FXML ComboBox cbMaterial;
    @FXML TextArea txtCaracteristicas;
    @FXML TextField txtCodigoBarras, txtArmario,txtGaveta,txtSubCompartimento,txtTipo,txtNumParte,txtValor,txtUnidadMedida,txtStock,txtStockMin;
    Conexion conexion;
    @FXML
    Button btnNew, btnSave, btnEdit, btnCancel, btnExit;
    @FXML
    TabPane tabV;
    @FXML
    Tab tabSearch, tabNew;

    @FXML protected void initialize(){
        conexion = new Conexion();
 /*   btnNew.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnSave.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnEdit.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnCancel.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnExit.setGraphic(new ImageView(new Image("",25,25,false,true))); */

    }





    @FXML private void NewArticulo(ActionEvent event) throws SQLException {
        Long cb = GenerateNumber();
        tabV.getSelectionModel().select(tabNew);
        txtCodigoBarras.setText(String.valueOf(cb));
        CleanTextFields();
    }
    @FXML private void SaveArticulo(ActionEvent event){
        boolean sp= VerifyTxt(txtCaracteristicas, cbMaterial,txtArmario,txtCodigoBarras,txtGaveta,txtSubCompartimento,txtStock,txtStockMin,txtNumParte,txtTipo,txtValor,txtUnidadMedida);
        if (sp){
            System.out.println("Todo correcto");
        }else {
            Error("Faltan campos por rellenar");
        }
    }

    private Long GenerateNumber() throws SQLException {
        boolean bd=false;
        Random random=new Random();
        long numero = (long)(random.nextDouble()*10000000000L);
        while(!String.valueOf(numero).matches("\\d{10}") && !bd){
            numero = (long)(random.nextDouble()*10000000000L);
            bd= VerifyCB(numero);
        }
        return numero;
    }
    private boolean VerifyCB(long num) throws SQLException {
        ResultSet resultSet = conexion.consultar("SELECT `cb_material` FROM `material` WHERE `cb_material`='"+num+"'");
        boolean bd;
        if (resultSet.next()){
            bd=true;
        }else {
            bd=false;
        }
        return bd;
    }
    private void CleanTextFields(){
        txtCaracteristicas.setText("");
        txtArmario.setText("");
        txtGaveta.setText("");
        txtSubCompartimento.setText("");
        txtTipo.setText("");
        txtNumParte.setText("");
        txtValor.setText("");
        txtUnidadMedida.setText("");
        txtStock .setText("");
        txtStockMin.setText("");


    }
    private boolean VerifyTxt(TextArea txtCar, ComboBox cbMat, TextField... campos){
        for (TextField campo : campos){
            if (campo.getText().isEmpty()){
                return false;
            }
        }
        if (txtCar.getText().isEmpty() || cbMat.getSelectionModel().getSelectedIndex() == -1){
            return false;
        }
        return true;
    }
    private void Error(String mensaje){
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.setTitle("Error");
        alert.show();
    }
    private void Exito(String mensaje){
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(mensaje);
        alert.setTitle("Exito");
        alert.show();
    }
}

package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Articulo;
import com.example.controldeinventario.Datos.Herramienta;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class HerramientasController {
    Conexion conexion;
    @FXML TabPane tabPaneHerramientas;
    @FXML Tab tabNew, tabSearch;
    @FXML TextField txtBusqueda;
    @FXML RadioButton rbID, rbNombre;
    @FXML TableView tableViewHerramientas;
    @FXML Label lblRegistros;
    @FXML TextField txtCB, txtTipo, txtStock, txtStockMin;
    @FXML TextArea txtCaracteristicas;
    @FXML RadioButton rbBajo, rbMedio, rbAlto;
    @FXML ComboBox<String> cbHerramienta = new ComboBox<>();
    @FXML Button btnNew,btnSave,btnEdit,btnCancel,btnExit;

    ToggleGroup toggleGroupBusqueda = new ToggleGroup();
    ToggleGroup toggleGroupFrecuencia = new ToggleGroup();

    TableColumn colID = new TableColumn("CB Herramienta");
    TableColumn colHerramienta= new TableColumn("Herramienta");
    TableColumn colTipo= new TableColumn("Tipo");
    TableColumn colCaracteristicas = new TableColumn("Caracteristicas");
    TableColumn colFUso=new TableColumn("Frecuencia de uso");
    TableColumn colCantidad=new TableColumn("Cantidad");
    TableColumn colCantidadMin=new TableColumn("Cantidad minima");




    @FXML protected void initialize() throws SQLException {
        rbBajo.setToggleGroup(toggleGroupFrecuencia);
        rbMedio.setToggleGroup(toggleGroupFrecuencia);
        rbAlto.setToggleGroup(toggleGroupFrecuencia);

        rbID.setToggleGroup(toggleGroupBusqueda);
        rbNombre.setToggleGroup(toggleGroupBusqueda);

        colID.setCellValueFactory(new PropertyValueFactory<Herramienta,Long>("cb_herramienta"));
        colHerramienta.setCellValueFactory(new PropertyValueFactory<Herramienta,Integer>("id_herramienta"));
        colTipo.setCellValueFactory(new PropertyValueFactory<Herramienta,String>("tipo"));
        colCaracteristicas.setCellValueFactory(new PropertyValueFactory<Herramienta,String>("caracteristicas"));
        colFUso.setCellValueFactory(new PropertyValueFactory<Herramienta,String>("frecuencia_de_uso"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<Herramienta,Integer>("cantidad"));
        colCantidadMin.setCellValueFactory(new PropertyValueFactory<Herramienta,Integer>("cantidad_min"));

        tableViewHerramientas.getItems().addAll(colID,colHerramienta,colTipo,colCaracteristicas,colFUso,colCantidad,colCantidadMin);
        conexion=new Conexion();
        cbHerramienta.getItems().clear();
        ResultSet resultSet= conexion.consultar("SELECT `herramienta` FROM `herramientas`");
        while (resultSet.next()){
            cbHerramienta.getItems().add((String) resultSet.getObject("herramienta"));
        }
        ActualizarTabla(conexion.consultar("SELECT * FROM `herramienta` INNER JOIN herramientas ON herramienta.id_herramienta = herramientas.id_herramienta;"));
    }
    @FXML private void NewHerramienta() throws SQLException {
        txtCB.setDisable(false);
        ActivateBtn(false,false,true,false,false);
        Long cb = GenerateNumber();
        tabPaneHerramientas.getSelectionModel().select(tabNew);
        tabNew.setDisable(false);
        tabSearch.setDisable(true);
        txtCB.setText(String.valueOf(cb));
        CleanTextFields();

    }

    @FXML private void SaveHerramienta() throws SQLException {
        if (VerifyTxt(txtCaracteristicas, cbHerramienta,txtStock,txtTipo,txtStockMin)){
            ResultSet resultSetIDHerramienta = conexion.consultar("SELECT `id_herramienta` FROM `herramientas` WHERE `herramienta`='"+cbHerramienta.getSelectionModel().getSelectedItem()+"' LIMIT 1");
            if (resultSetIDHerramienta.next()){
                int id = resultSetIDHerramienta.getInt("id_herramienta");
                ResultSet resultSetUpdate = conexion.consultar("SELECT * FROM `herramienta` WHERE `cb_herramienta`='"+txtCB.getText()+"' LIMIT 1");
                if (resultSetUpdate.next()){

                }
            }
        }
    }

    private void ActualizarTabla(ResultSet rsHerramientas) throws SQLException {
        int cont=0;
        tableViewHerramientas.getItems().clear();
        while (rsHerramientas.next()){
            Herramienta h= new Herramienta(rsHerramientas.getLong("cb_herramienta"),
                    rsHerramientas.getString("herramienta"),
                    rsHerramientas.getString("tipo"),
                    rsHerramientas.getString("caracteristicas"),
                    rsHerramientas.getString("frecuencia_de_uso"),
                    rsHerramientas.getInt("cantidad"),
                    rsHerramientas.getInt("cantidad_min"));
            tableViewHerramientas.getItems().add(h);
        }
        lblRegistros.setText("Se cargaron "+cont+" herramientas");
    }
    private void ActivateBtn(boolean New, boolean save, boolean edit, boolean cancel, boolean exit){
        btnNew.setDisable(New);
        btnSave.setDisable(save);
        btnEdit.setDisable(edit);
        btnCancel.setDisable(cancel);
        btnExit.setDisable(exit);
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
        ResultSet res= conexion.consultar("SELECT herramienta.id_herramienta\n" +
                "FROM herramienta\n" +
                "LEFT JOIN material\n" +
                "ON herramienta.id_herramienta = material.cb_material\n" +
                "WHERE herramienta.id_herramienta = '"+num+"' OR material.cb_material = '"+num+"'\n" +
                "UNION\n" +
                "SELECT material.cb_material\n" +
                "FROM material\n" +
                "LEFT JOIN herramienta\n" +
                "ON herramienta.id_herramienta = material.cb_material\n" +
                "WHERE herramienta.id_herramienta = '"+num+"' OR material.cb_material = '"+num+"'\n" +
                "AND herramienta.id_herramienta IS NULL;");

        // ResultSet resultSet = conexion.consultar("SELECT `cb_material` FROM `material` WHERE `cb_material`='"+num+"'");
        boolean bd=false;
        if (res.next()){
            bd=true;
        }else {
            return bd;
        }
        return bd;
    }
    private void CleanTextFields(){
        txtTipo.setText("");
        txtCaracteristicas.setText("");
        txtStock.setText("");
        txtStockMin.setText("");
    }

    private boolean VerifyTxt(TextArea txtCar, ComboBox cbHerramienta, TextField... campos){
        for (TextField campo : campos){
            if (campo.getText().isEmpty()){
                return false;
            }
        }
        if (txtCar.getText().isEmpty() || cbHerramienta.getSelectionModel().getSelectedIndex() == -1){
            return false;
        }
        return true;
    }
}

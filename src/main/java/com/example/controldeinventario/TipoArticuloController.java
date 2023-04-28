package com.example.controldeinventario;

import com.example.controldeinventario.Datos.TipoArticulo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoArticuloController {
    Conexion conexion;
    ToggleGroup toggleGroupBusqueda= new ToggleGroup();
    ToggleGroup toggleGroupTMaterial= new ToggleGroup();

    @FXML TabPane tabPaneVentana;
    @FXML Tab tabNew, tabSearch;

    @FXML CheckBox checkBoxHerramienta, checkBoxMaterial;
    @FXML Button btnNew, btnSave, btnEdit, btnDelete, btnCancel, btnExit;
    @FXML RadioButton radioButtonID, radioButtonNombre;
    @FXML TextField txtBusqueda;
    @FXML TableView tableViewTMateriales;
    @FXML Label lblContador;

    @FXML RadioButton rbMaterial, rbHerramienta;
    @FXML TextField txtID, txtNombre;

    TableColumn tableColumnID = new TableColumn("No");
    TableColumn tableColumnNombre = new TableColumn("Nombre");
    TableColumn tableColumnTipo = new TableColumn("Tipo de material");

    @FXML protected void initialize() throws SQLException {
        Platform.runLater(() -> {
            txtBusqueda.requestFocus();
            txtBusqueda.selectEnd();
        });
        tableColumnID.setCellValueFactory(new PropertyValueFactory<TipoArticulo,Integer>("id"));
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<TipoArticulo,String>("nombre"));
        tableColumnTipo.setCellValueFactory(new PropertyValueFactory<TipoArticulo,String>("t_material"));

        tableViewTMateriales.getColumns().addAll(tableColumnID,tableColumnNombre,tableColumnTipo);

        radioButtonID.setToggleGroup(toggleGroupBusqueda);
        radioButtonNombre.setToggleGroup(toggleGroupBusqueda);
        rbMaterial.setToggleGroup(toggleGroupTMaterial);
        rbHerramienta.setToggleGroup(toggleGroupTMaterial);
        conexion = new Conexion();
        ActualizarTabla(conexion.consultar("SELECT * FROM herramientas UNION SELECT * FROM materiales;"));
    }

    private void ActualizarTabla(ResultSet rsTipos) throws SQLException {
        int cont=1;
        tableViewTMateriales.getItems().clear();
        while (rsTipos.next()){
            TipoArticulo tipoArticulo = new TipoArticulo(cont, rsTipos.getString("herramienta"),"Pala");
            tableViewTMateriales.getItems().add(tipoArticulo);
            cont++;
        }
        lblContador.setText("Se cargaron "+(cont-1)+" tipos de herramienta");
    }

    @FXML private void Busqueda() throws SQLException {
        String busqueda= txtBusqueda.getText();
        String criterio="";
        String consulta="SELECT * FROM herramientas UNION SELECT * FROM materiales;";
        if (checkBoxHerramienta.isSelected()){

        }
        if (radioButtonID.isSelected() && !busqueda.equals("")){
            criterio="cb_material";
        } else if (radioButtonNombre.isSelected() && !busqueda.equals("")) {
            criterio="tipo_de_armario";
        } else if (rbMaterial.isSelected() && !busqueda.equals("")) {
            criterio="material";
        }
        if (!busqueda.equals("")){
            ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN materiales ON material.id_material = materiales.id_material WHERE `"+criterio+"` LIKE '%"+busqueda+"%'"));
        }else {
            ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN materiales ON material.id_material = materiales.id_material;"));
        }
    }
}

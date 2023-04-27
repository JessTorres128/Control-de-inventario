package com.example.controldeinventario;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TipoArticuloController {
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

    TableColumn tableColumnID = new TableColumn("ID");
    TableColumn tableColumnNombre = new TableColumn("Nombre");
    TableColumn tableColumnTipo = new TableColumn("Tipo de material");

    @FXML protected void initialize(){

    }
}

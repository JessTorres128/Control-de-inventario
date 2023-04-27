package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Herramienta;
import com.example.controldeinventario.Datos.Usuario;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadosController {
    Conexion conexion;
    ToggleGroup toggleGroupBusqueda = new ToggleGroup();
    ToggleGroup toggleGroupSexo = new ToggleGroup();
    @FXML
    TabPane tabPaneVentana;
    @FXML
    Tab tabNew, tabSearch;
    @FXML
    Button btnNew, btnSave, btnEdit, btnDelete, btnCancel, btnExit;
    @FXML
    RadioButton rbID, rbNombre, rbRol;
    @FXML TextField txtBusqueda;
    @FXML TableView tableViewUsuarios;
    @FXML Label lblContador;
    @FXML TextField txtID, txtNombre, txtUsername;
    @FXML PasswordField txtPass, txtConfirmarPass;
    @FXML ComboBox cbRoles;
    @FXML Button btnBuscarRol;
    @FXML RadioButton rbMasculino, rbFemenino;

    TableColumn tableColumnID = new TableColumn<>("ID");
    TableColumn tableColumnNombre = new TableColumn<>("Nombre");
    TableColumn tableColumnSexo = new TableColumn<>("Sexo");
    TableColumn tableColumnUsername = new TableColumn<>("Nombre de usuario");
    TableColumn tableColumnRol = new TableColumn<>("Rol");

    @FXML protected void initialize() throws SQLException {
        Platform.runLater(() -> {
            txtBusqueda.requestFocus();
            txtBusqueda.selectEnd();
        });
        conexion = new Conexion();
        rbID.setToggleGroup(toggleGroupBusqueda);
        rbNombre.setToggleGroup(toggleGroupBusqueda);
        rbRol.setToggleGroup(toggleGroupBusqueda);
        rbMasculino.setToggleGroup(toggleGroupSexo);
        rbFemenino.setToggleGroup(toggleGroupSexo);

        tableColumnID.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("id_user"));
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nombre_completo"));
        tableColumnSexo.setCellValueFactory(new PropertyValueFactory<Usuario,String>("sexo"));
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory<Usuario,String>("username"));
        tableColumnRol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nombre_rol"));

        tableViewUsuarios.getColumns().addAll(tableColumnID,tableColumnNombre,tableColumnSexo,tableColumnUsername,tableColumnRol);

        cbRoles.getItems().clear();
        ResultSet resultSetRoles = conexion.consultar("SELECT `nombre_rol`FROM `tipo_usuario`");
        while (resultSetRoles.next()){
            cbRoles.getItems().add(resultSetRoles.getString("nombre_rol"));
        }
        ActualizarTabla(conexion.consultar("SELECT * FROM `usuario`"));
    }

    private void ActualizarTabla(ResultSet rsUsuarios) throws SQLException {
        int cont=0;
        tableViewUsuarios.getItems().clear();
        while (rsUsuarios.next()){
            Usuario usuario = new Usuario(rsUsuarios.getInt("id_user"), rsUsuarios.getString("nombre_completo")
            ,rsUsuarios.getString("sexo"), rsUsuarios.getString("username"), rsUsuarios.getString("nombre_rol"));
            tableViewUsuarios.getItems().add(usuario);
            cont++;
        }
        lblContador.setText("Se cargaron "+cont+" herramientas");

    }
    @FXML private void Busqueda() throws SQLException {
        String busqueda = txtBusqueda.getText();
        String criterio = "";
        if (rbID.isSelected() && !busqueda.equals("")){
            criterio="id_user";
        } else if (rbNombre.isSelected() && !busqueda.equals("")) {
            criterio="nombre_completo";
        } else if (rbRol.isSelected() && !busqueda.equals("")) {
            criterio="nombre_rol";
        }
        if (!busqueda.equals("")){
            ActualizarTabla(conexion.consultar("SELECT * FROM `usuario` WHERE `"+criterio+"` LIKE '%"+busqueda+"%'"));
        }else {
            ActualizarTabla(conexion.consultar("SELECT * FROM `usuario`"));
        }
    }
}

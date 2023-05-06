package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Herramienta;
import com.example.controldeinventario.Datos.Usuario;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

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
    @FXML TableView<Usuario> tableViewUsuarios;
    @FXML Label lblContador;
    @FXML TextField txtID, txtNombre, txtUsername;
    @FXML PasswordField txtPass, txtConfirmarPass;
    @FXML ComboBox<String> cbRoles;
    @FXML Button btnBuscarRol;
    @FXML RadioButton rbMasculino, rbFemenino;

    TableColumn<Usuario,Integer> tableColumnID = new TableColumn<>("ID");
    TableColumn<Usuario,String> tableColumnNombre = new TableColumn<>("Nombre");
    TableColumn<Usuario,String> tableColumnSexo = new TableColumn<>("Sexo");
    TableColumn<Usuario,String> tableColumnUsername = new TableColumn<>("Nombre de usuario");
    TableColumn<Usuario,String> tableColumnRol = new TableColumn<>("Rol");

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

        tableColumnID.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_completo"));
        tableColumnSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableColumnRol.setCellValueFactory(new PropertyValueFactory<>("nombre_rol"));

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

    @FXML private void NewEmpleado() throws SQLException {
        txtID.setDisable(false);
        ActivateBtn(false,false,true,false,false,true);

        tabPaneVentana.getSelectionModel().select(tabNew);
        tabNew.setDisable(false);
        tabSearch.setDisable(true);
        CleanTextFields();
    }

    @FXML private void SaveEmpleado() throws SQLException {
        if (VerifyTxt(txtPass, txtConfirmarPass, cbRoles,txtID,txtNombre,txtUsername)){
            ResultSet resultSetRol = conexion.consultar("SELECT `nombre_rol` FROM `tipo_usuario` WHERE `nombre_rol`='"+cbRoles.getSelectionModel().getSelectedItem()+"' LIMIT 1");
            if (resultSetRol.next()){
                ResultSet resultSetUpdate = conexion.consultar("SELECT * FROM `usuario` WHERE `id_user`='"+txtID.getText()+"' LIMIT 1");
                if (resultSetUpdate.next()){
                    conexion.insmodelim("UPDATE `usuario` SET `nombre_completo`='"+txtNombre.getText()+"',`sexo`='"+((RadioButton) toggleGroupSexo.getSelectedToggle()).getText()+"',`username`='"+txtUsername.getText()+"',`password`='"+txtPass.getText()+"',`nombre_rol`='"+cbRoles.getSelectionModel().getSelectedItem()+"' WHERE `id_user`='"+txtID.getText()+"'");
                    Exito("Actualizado con exito");
                }else {
                    conexion.insmodelim("INSERT INTO `usuario`(`nombre_completo`, `sexo`, `username`, `password`, `nombre_rol`) VALUES ('"+txtNombre.getText()+"','"+((RadioButton) toggleGroupSexo.getSelectedToggle()).getText()+"','"+txtUsername.getText()+"','"+txtPass.getText()+"','"+cbRoles.getSelectionModel().getSelectedItem()+"')");
                    Exito(txtNombre.getText()+" agregado");
                }
                tabPaneVentana.getSelectionModel().select(tabSearch);
                tabSearch.setDisable(false);
                tabNew.setDisable(true);
                ActivateBtn(false,true,false,true,false,false);
                txtID.setDisable(false);
                ActualizarTabla(conexion.consultar("SELECT * FROM `usuario`"));

            }else {
                Error("Selecciona el rol");
            }
        }else {
            Error("Faltan campos por rellenar");
        }
    }

    @FXML private void EditEmpleado() throws SQLException {
        if (tableViewUsuarios.getSelectionModel().getSelectedItem() != null){
            Usuario usuario= (Usuario) tableViewUsuarios.getSelectionModel().getSelectedItem();
            ResultSet rsUsuario = conexion.consultar("SELECT * FROM `usuario` WHERE `id_user`='"+usuario.getId_user()+"'");
            if (rsUsuario.next()){
                usuario = new Usuario(rsUsuario.getInt("id_user"), rsUsuario.getString("nombre_completo")
                        ,rsUsuario.getString("sexo"), rsUsuario.getString("username"), rsUsuario.getString("password"), rsUsuario.getString("nombre_rol"));
                tabPaneVentana.getSelectionModel().select(tabNew);
                tabSearch.setDisable(true);
                tabNew.setDisable(false);
                txtID.setText(String.valueOf(usuario.getId_user()));
                txtNombre.setText(String.valueOf(usuario.getNombre_completo()));
                txtUsername.setText(String.valueOf(usuario.getUsername()));
                cbRoles.getSelectionModel().select(usuario.getNombre_rol());
                txtPass.setText(usuario.getPassword());
                txtConfirmarPass.setText(usuario.getPassword());
                switch (usuario.getSexo()) {
                    case "Masculino" -> toggleGroupSexo.selectToggle(rbMasculino);
                    case "Femenino" -> toggleGroupSexo.selectToggle(rbFemenino);
                }
                txtID.setDisable(true);
                ActivateBtn(true,false,true,false,false,true);
            }

        }else {
            Error("Selecciona un registro pa");
        }
    }

    @FXML private void DeleteEmpleado() throws SQLException {
        if (tableViewUsuarios.getSelectionModel().getSelectedItem() != null){
            Usuario usuario= (Usuario) tableViewUsuarios.getSelectionModel().getSelectedItem();
            if (ConfirmarBorrar("Deseas borrar a"+usuario.getNombre_completo())){
                conexion.insmodelim("DELETE FROM `usuario` WHERE `id_user`='"+usuario.getId_user()+"'");
                Exito("Registro borrado exitosamente");
                ActualizarTabla(conexion.consultar("SELECT * FROM `usuario`"));
            }

        }else {
            Error("Selecciona un registro pa");
        }


    }

    @FXML private void CancelEmpleado() throws SQLException {
        txtID.setText("");
        txtID.setDisable(false);
        CleanTextFields();
        ActivateBtn(false,true,false,true,false,false);
        tabPaneVentana.getSelectionModel().select(tabSearch);
        tabSearch.setDisable(false);
        tabNew.setDisable(true);
    }

    @FXML private void ExitEmpleado(){

    }
    private boolean VerifyTxt(PasswordField txtPass, PasswordField txtPassConf, ComboBox<String> cbRol, TextField... campos){
        for (TextField campo : campos){
            if (campo.getText().isEmpty()){
                return false;
            }
        }
        return !txtPass.getText().isEmpty() && cbRol.getSelectionModel().getSelectedIndex() != -1 && !txtPassConf.getText().isEmpty();
    }
    private void ActivateBtn(boolean New, boolean save, boolean edit, boolean cancel, boolean exit, boolean delete) throws SQLException {
        if (LoginController.resultado.getInt("crud_empleados")==0){
            btnNew.setDisable(true);
            btnEdit.setDisable(true);
            btnDelete.setDisable(true);
        }else {
            btnNew.setDisable(New);
            btnEdit.setDisable(edit);
            btnDelete.setDisable(delete);
        }


        btnSave.setDisable(save);
        btnCancel.setDisable(cancel);
        btnExit.setDisable(exit);
    }

    private void CleanTextFields(){
        txtID.setText("");
        txtNombre.setText("");
        txtUsername.setText("");
        txtPass.setText("");
        txtConfirmarPass.setText("");

    }

    public boolean ConfirmarBorrar(String mensaje) {
        AtomicBoolean confirmar = new AtomicBoolean(false);
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Confirmar acción");

        Label lblmsg = new Label(mensaje);
        Button btnConfirmar = new Button("Aceptar");
        Button btnCancelar = new Button("Cancelar");

        btnConfirmar.setOnAction(e -> {
            confirmar.set(true);
            dialog.close();
        });

        btnCancelar.setOnAction(e -> {
            confirmar.set(false);
            dialog.close();
        });

        VBox vbox = new VBox(lblmsg, btnConfirmar, btnCancelar);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        Scene dialogScene = new Scene(vbox, 300, 100);
        dialog.setScene(dialogScene);
        dialog.showAndWait();

        return confirmar.get();
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

package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Tipo_Usuario;
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

public class RolesController {
    Conexion conexion;
    ToggleGroup toggleGroupBusqueda = new ToggleGroup();

    @FXML RadioButton rbID, rbNombre;
    @FXML TextField txtBusqueda;
    @FXML Label lblContRegistros;
    @FXML Button btnNew, btnSave,btnEdit,btnDelete,btnCancel,btnExit;
    @FXML Tab tabSearch, tabNew;
    @FXML TabPane tabPaneVentana;
    @FXML TextField txtId_rol, txtNombre_rol;
    @FXML CheckBox check_create_material, check_update_material, check_delete_material, check_create_herramienta, check_update_herramienta, check_delete_herramienta, check_crud_pedido, check_create_t_articulo, check_update_t_articulo, check_delete_t_articulo,check_crud_roles, check_crud_empleados, check_restaurar_bd, check_respaldar_bd;

    @FXML TableView<Tipo_Usuario> tableTUsuarios = new TableView<>();
    @FXML TableColumn<Tipo_Usuario,Integer>  col_id= new TableColumn<>("ID");
    @FXML TableColumn<Tipo_Usuario,String>  col_nombre= new TableColumn<>("Nombre");
    @FXML TableColumn<Tipo_Usuario,String>  col_c_material= new TableColumn<>("Crear material");
    @FXML TableColumn<Tipo_Usuario,String>  col_u_material= new TableColumn<>("Editar material");
    @FXML TableColumn<Tipo_Usuario,String>  col_d_material= new TableColumn<>("Borrar material");
    @FXML TableColumn<Tipo_Usuario,String>  col_c_herramienta= new TableColumn<>("Crear herramienta");
    @FXML TableColumn<Tipo_Usuario,String>  col_u_herramienta= new TableColumn<>("Editar herramienta");
    @FXML TableColumn<Tipo_Usuario,String>  col_d_herramienta= new TableColumn<>("Borrar herramienta");
    @FXML TableColumn<Tipo_Usuario,String>  col_pedidos= new TableColumn<>("Pedidos");
    @FXML TableColumn<Tipo_Usuario,String>  col_c_t_articulo= new TableColumn<>("Crear tipo de articulo");
    @FXML TableColumn<Tipo_Usuario,String>  col_u_t_articulo= new TableColumn<>("Editar tipo de articulo");
    @FXML TableColumn<Tipo_Usuario,String>  col_d_t_articulo= new TableColumn<>("Borrar tipo de articulo");
    @FXML TableColumn<Tipo_Usuario,String>  col_roles= new TableColumn<>("Roles");
    @FXML TableColumn<Tipo_Usuario,String>  col_empleados= new TableColumn<>("Empleados");
    @FXML TableColumn<Tipo_Usuario,String>  col_restaurarbd= new TableColumn<>("Restaurar BD");
    @FXML TableColumn<Tipo_Usuario,String>  col_respaldarbd= new TableColumn<>("Respaldar BD");
    @FXML protected void initialize() throws SQLException {
        ActivateBtn(false,true,false,true,false,false);
        conexion = new Conexion();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_rol"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre_rol"));
        col_c_material.setCellValueFactory(new PropertyValueFactory<>("create_material"));
        col_u_material.setCellValueFactory(new PropertyValueFactory<>("update_material"));
        col_d_material.setCellValueFactory(new PropertyValueFactory<>("delete_material"));
        col_c_herramienta.setCellValueFactory(new PropertyValueFactory<>("create_herramienta"));
        col_u_herramienta.setCellValueFactory(new PropertyValueFactory<>("update_herramienta"));
        col_d_herramienta.setCellValueFactory(new PropertyValueFactory<>("delete_herramienta"));
        col_pedidos.setCellValueFactory(new PropertyValueFactory<>("crud_pedido"));
        col_d_t_articulo.setCellValueFactory(new PropertyValueFactory<>("create_t_articulo"));
        col_d_t_articulo.setCellValueFactory(new PropertyValueFactory<>("update_t_articulo"));
        col_d_t_articulo.setCellValueFactory(new PropertyValueFactory<>("delete_t_articulo"));
        col_roles.setCellValueFactory(new PropertyValueFactory<>("crud_roles"));
        col_empleados.setCellValueFactory(new PropertyValueFactory<>("crud_empleados"));
        col_restaurarbd.setCellValueFactory(new PropertyValueFactory<>("restaurar_bd"));
        col_respaldarbd.setCellValueFactory(new PropertyValueFactory<>("respaldar_bd"));

        tableTUsuarios.getColumns().addAll(col_id,col_nombre,col_c_material,col_u_material,col_d_material,col_c_herramienta,col_u_herramienta,col_d_herramienta,col_pedidos,col_c_t_articulo,col_u_t_articulo,col_d_t_articulo,col_roles,col_empleados,col_restaurarbd,col_respaldarbd);

        rbID.setToggleGroup(toggleGroupBusqueda);
        rbNombre.setToggleGroup(toggleGroupBusqueda);

        conexion = new Conexion();
        ActualizarTabla(conexion.consultar("SELECT * FROM `tipo_usuario`"));

    }
    @FXML private void Busqueda() throws SQLException {
        String busqueda = txtBusqueda.getText();
        String criterio = "";
        if (rbID.isSelected() && !busqueda.equals("")){
            criterio="id_rol";
        } else if (rbNombre.isSelected() && !busqueda.equals("")) {
            criterio="nombre_rol";
        }
        if (!busqueda.equals("")){
            ActualizarTabla(conexion.consultar("SELECT * FROM `tipo_usuario` WHERE `"+criterio+"` LIKE '%"+busqueda+"%'"));
        }else {
            ActualizarTabla(conexion.consultar("SELECT * FROM `tipo_usuario`"));
        }
    }
    private void ActualizarTabla(ResultSet rsRoles) throws SQLException {
        int cont=0;
        tableTUsuarios.getItems().clear();
        while (rsRoles.next()){
            cont++;
            Tipo_Usuario t = new Tipo_Usuario(rsRoles.getInt("id_rol"),rsRoles.getString("nombre_rol"),VerificarBoolean(rsRoles.getInt("create_material") == 1),VerificarBoolean(rsRoles.getInt("update_material") == 1),VerificarBoolean(rsRoles.getInt("delete_material") == 1),VerificarBoolean(rsRoles.getInt("create_herramienta") == 1),VerificarBoolean(rsRoles.getInt("update_herramienta") == 1),VerificarBoolean(rsRoles.getInt("delete_herramienta") == 1),VerificarBoolean(rsRoles.getInt("crud_pedido") == 1),VerificarBoolean(rsRoles.getInt("create_t_articulo") == 1),VerificarBoolean(rsRoles.getInt("update_t_articulo") == 1),VerificarBoolean(rsRoles.getInt("delete_t_articulo") == 1),VerificarBoolean(rsRoles.getInt("crud_roles") == 1),VerificarBoolean(rsRoles.getInt("crud_empleados") == 1),VerificarBoolean(rsRoles.getInt("restaurar_bd") == 1),VerificarBoolean(rsRoles.getInt("respaldar_bd") == 1));
            tableTUsuarios.getItems().add(t);
        }
        lblContRegistros.setText("Se cargaron "+cont+" articulos");
    }

    @FXML private void NewRol() throws SQLException {
        ActivateBtn(false,false,true,false,false,true);
        tabPaneVentana.getSelectionModel().select(tabNew);
        tabNew.setDisable(false);
        tabSearch.setDisable(true);
        CleanTextFields();
    }

    @FXML private void SaveRol() throws SQLException {
        if (!txtNombre_rol.getText().isEmpty()){
            ResultSet resultSetUpdate = conexion.consultar("SELECT * FROM `tipo_usuario` WHERE `id_rol` = '"+txtId_rol.getText()+"' LIMIT 1");
            if (resultSetUpdate.next()){
                conexion.insmodelim("UPDATE `tipo_usuario` SET `nombre_rol`='"+txtNombre_rol.getText()+"',`create_material`='"+VerificarCheckBox(check_create_material)+"',`update_material`='"+VerificarCheckBox(check_update_material)+"'," +
                        "`delete_material`='"+VerificarCheckBox(check_delete_material)+"',`create_herramienta`='"+VerificarCheckBox(check_create_herramienta)+"',`update_herramienta`='"+VerificarCheckBox(check_update_herramienta)+"',`delete_herramienta`='"+VerificarCheckBox(check_delete_herramienta)+"'," +
                        "`crud_pedido`='"+VerificarCheckBox(check_crud_pedido)+"',`create_t_articulo`='"+VerificarCheckBox(check_create_t_articulo)+"',`update_t_articulo`='"+VerificarCheckBox(check_update_t_articulo)+"',`delete_t_articulo`='"+VerificarCheckBox(check_delete_t_articulo)+"'," +
                        ",`crud_roles`='"+VerificarCheckBox(check_crud_roles)+"'," +
                        "`crud_empleados`='"+VerificarCheckBox(check_crud_empleados)+"',`restaurar_bd`='"+VerificarCheckBox(check_restaurar_bd)+"',`respaldar_bd`='"+VerificarCheckBox(check_respaldar_bd)+"' WHERE `id_rol`='"+resultSetUpdate.getInt("id_rol")+"'");
                ResultSet resultSetEmpleados= conexion.consultar("SELECT * FROM `usuario` WHERE `nombre_rol`='"+resultSetUpdate.getString("nombre_rol")+"'");
                while(resultSetEmpleados.next()){
                    conexion.insmodelim("UPDATE `usuario` SET `nombre_rol`='"+txtNombre_rol.getText()+"' WHERE `id_user`='"+resultSetEmpleados.getInt("id_user")+"'");
                }
            }else {
                conexion.insmodelim("INSERT INTO `tipo_usuario`(`nombre_rol`, `create_material`, `update_material`, `delete_material`, `create_herramienta`, `update_herramienta`, `delete_herramienta`, `crud_pedido`, `create_t_articulo`, `update_t_articulo`, `delete_t_articulo`, `crud_roles`, `crud_empleados`, `restaurar_bd`, `respaldar_bd`) VALUES " +
                        "('"+txtNombre_rol.getText()+"','"+VerificarCheckBox(check_create_material)+"','"+VerificarCheckBox(check_update_material)+"'," +
                        "'"+VerificarCheckBox(check_delete_material)+"','"+VerificarCheckBox(check_create_herramienta)+"','"+VerificarCheckBox(check_update_herramienta)+"','"+VerificarCheckBox(check_delete_herramienta)+"'," +
                        "'"+VerificarCheckBox(check_crud_pedido)+"','"+VerificarCheckBox(check_create_t_articulo)+"','"+VerificarCheckBox(check_update_t_articulo)+"','"+VerificarCheckBox(check_delete_t_articulo)+"'," +
                        "'"+VerificarCheckBox(check_crud_roles)+"'," +
                        "'"+VerificarCheckBox(check_crud_empleados)+"','"+VerificarCheckBox(check_restaurar_bd)+"','"+VerificarCheckBox(check_respaldar_bd)+"')");
            }
            tabPaneVentana.getSelectionModel().select(tabSearch);
            tabSearch.setDisable(false);
            tabNew.setDisable(true);
            ActivateBtn(false,true,false,true,false,false);
            ActualizarTabla(conexion.consultar("SELECT * FROM `tipo_usuario`"));
            ClearCheckBox();

        }
    }
    @FXML private void EditRol() throws SQLException {
        if (tableTUsuarios.getSelectionModel().getSelectedItem() != null){
            Tipo_Usuario tipoUsuario= (Tipo_Usuario) tableTUsuarios.getSelectionModel().getSelectedItem();
            tabPaneVentana.getSelectionModel().select(tabNew);
            tabSearch.setDisable(true);
            tabNew.setDisable(false);
            txtId_rol.setText(String.valueOf(tipoUsuario.getId_rol()));
            check_create_material.setSelected(VerificarString(tipoUsuario.getCreate_material()));
            check_update_material.setSelected(VerificarString(tipoUsuario.getUpdate_material()));
            check_delete_material.setSelected(VerificarString(tipoUsuario.getDelete_material()));
            check_create_herramienta.setSelected(VerificarString(tipoUsuario.getCreate_herramienta()));
            check_update_herramienta.setSelected(VerificarString(tipoUsuario.getUpdate_herramienta()));
            check_delete_herramienta.setSelected(VerificarString(tipoUsuario.getDelete_herramienta()));
            check_crud_pedido.setSelected(VerificarString(tipoUsuario.getCrud_pedido()));
            check_create_t_articulo.setSelected(VerificarString(tipoUsuario.getCreate_t_articulo()));
            check_update_t_articulo.setSelected(VerificarString(tipoUsuario.getUpdate_t_articulo()));
            check_delete_t_articulo.setSelected(VerificarString(tipoUsuario.getDelete_t_articulo()));
            check_crud_roles.setSelected(VerificarString(tipoUsuario.getCrud_roles()));
            check_crud_empleados.setSelected(VerificarString(tipoUsuario.getCrud_empleados()));
            check_restaurar_bd.setSelected(VerificarString(tipoUsuario.getRestaurar_bd()));
            check_respaldar_bd.setSelected(VerificarString(tipoUsuario.getRespaldar_bd()));
            txtNombre_rol.setText(tipoUsuario.getNombre_rol());
            ActivateBtn(true,false,true,false,false,true);
        }else {
            Error("Selecciona un registro pa");
        }
    }

    @FXML private void DeleteRol() throws SQLException {
        if (tableTUsuarios.getSelectionModel().getSelectedItem() != null){
            Tipo_Usuario t = tableTUsuarios.getSelectionModel().getSelectedItem();
            if (ConfirmarBorrar("Deseas borrar a "+t.getNombre_rol()+", realizar esta accion \n tambien borrará a los usuarios que tengan este rol")){
                conexion.insmodelim("DELETE FROM `tipo_usuario` WHERE `id_rol`='"+t.getId_rol()+"'");
                conexion.insmodelim("DELETE FROM `usuario` WHERE `nombre_rol`='"+t.getNombre_rol()+"'");
                Exito("Registro borrado exitosamente");
                ActualizarTabla(conexion.consultar("SELECT * FROM `tipo_usuario`"));

            }

        }else {
            Error("Selecciona un registro pa");
        }
    }

    @FXML private void CancelRol() throws SQLException {
        txtId_rol.setText("");
        CleanTextFields();
        ActivateBtn(false,true,false,true,false,false);
        tabPaneVentana.getSelectionModel().select(tabSearch);
        tabSearch.setDisable(false);
        tabNew.setDisable(true);
        ClearCheckBox();
    }

    @FXML private void ExitRol(){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
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

        Scene dialogScene = new Scene(vbox, 300, 150);
        dialog.setScene(dialogScene);
        dialog.showAndWait();

        return confirmar.get();
    }
    private void ClearCheckBox(){
        check_create_material.setSelected(false);
        check_update_material.setSelected(false);
        check_delete_material.setSelected(false);
        check_create_herramienta.setSelected(false);
        check_update_herramienta.setSelected(false);
        check_delete_herramienta.setSelected(false);
        check_crud_pedido.setSelected(false);
        check_create_t_articulo.setSelected(false);
        check_update_t_articulo.setSelected(false);
        check_delete_t_articulo.setSelected(false);
        check_crud_roles.setSelected(false);
        check_crud_empleados.setSelected(false);
        check_restaurar_bd.setSelected(false);
        check_respaldar_bd.setSelected(false);
    }

    private String VerificarBoolean(Boolean val){
        if (val){
            return "Puede";
        }else {
            return "No puede";
        }
    }

    private void ActivateBtn(boolean New, boolean save, boolean edit, boolean cancel, boolean exit, boolean delete) throws SQLException {
        if (LoginController.resultado.getInt("crud_roles")==0){
            btnNew.setDisable(true);
            btnEdit.setDisable(true);
            btnDelete.setDisable(true);
        }else {
            btnNew.setDisable(New);
            btnDelete.setDisable(delete);
            btnEdit.setDisable(edit);
        }
        btnSave.setDisable(save);
        btnCancel.setDisable(cancel);
        btnExit.setDisable(exit);
    }

    private int VerificarCheckBox(CheckBox cb){
        if (cb.isSelected()){
            return 1;
        }else {
            return 0;
        }
    }
    private boolean VerificarString(String val){
        return val.equals("Puede");
    }
    private void CleanTextFields(){
        txtId_rol.setText("");
        txtNombre_rol.setText("");
    }
    private void Error(String mensaje){
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.setTitle("Error");
        alert.show();
    }
    private void Exito(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(mensaje);
        alert.setTitle("Exito");
    }
    }

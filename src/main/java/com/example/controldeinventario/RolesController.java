package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Articulo;
import com.example.controldeinventario.Datos.Herramienta;
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
    @FXML CheckBox check_create_material, check_update_material, check_delete_material, check_create_herramienta, check_update_herramienta, check_delete_herramienta, check_crud_pedido, check_create_t_material, check_update_t_material, check_delete_t_material, check_create_t_herramienta, check_update_t_herramienta, check_delete_t_herramienta, check_crud_roles, check_crud_empleados, check_restaurar_bd, check_respaldar_bd;

    @FXML TableView tableTUsuarios;
    @FXML TableColumn col_id= new TableColumn("ID");
    @FXML TableColumn col_nombre= new TableColumn("Nombre");
    @FXML TableColumn col_c_material= new TableColumn("Crear material");
    @FXML TableColumn col_u_material= new TableColumn("Editar material");
    @FXML TableColumn col_d_material= new TableColumn("Borrar material");
    @FXML TableColumn col_c_herramienta= new TableColumn("Crear herramienta");
    @FXML TableColumn col_u_herramienta= new TableColumn("Editar herramienta");
    @FXML TableColumn col_d_herramienta= new TableColumn("Borrar herramienta");
    @FXML TableColumn col_pedidos= new TableColumn("Pedidos");
    @FXML TableColumn col_c_t_material= new TableColumn("Crear tipo de material");
    @FXML TableColumn col_u_t_material= new TableColumn("Editar tipo de material");
    @FXML TableColumn col_d_t_material= new TableColumn("Borrar tipo de material");
    @FXML TableColumn col_c_t_herramienta= new TableColumn("Crear tipo de herramienta");
    @FXML TableColumn col_u_t_herramienta= new TableColumn("Editar tipo de herramienta");
    @FXML TableColumn col_d_t_herramienta= new TableColumn("Borrar tipo de herramienta");
    @FXML TableColumn col_roles= new TableColumn("Roles");
    @FXML TableColumn col_empleados= new TableColumn("Empleados");
    @FXML TableColumn col_restaurarbd= new TableColumn("Restaurar BD");
    @FXML TableColumn col_respaldarbd= new TableColumn("Respaldar BD");
    @FXML protected void initialize() throws SQLException {
        conexion = new Conexion();
        col_id.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Integer>("id_rol"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("nombre_rol"));
        col_c_material.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("create_material"));
        col_u_material.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("update_material"));
        col_d_material.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("delete_material"));
        col_c_herramienta.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("create_herramienta"));
        col_u_herramienta.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("update_herramienta"));
        col_d_herramienta.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("delete_herramienta"));
        col_pedidos.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("crud_pedido"));
        col_c_t_material.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("create_t_material"));
        col_u_t_material.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("update_t_material"));
        col_d_t_material.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("delete_t_material"));
        col_c_t_herramienta.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("create_t_herramienta"));
        col_u_t_herramienta.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("update_t_herramienta"));
        col_d_t_herramienta.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("delete_t_herramienta"));
        col_roles.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("crud_roles"));
        col_empleados.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("crud_empleados"));
        col_restaurarbd.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("restaurar_bd"));
        col_respaldarbd.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,String>("respaldar_bd"));

        tableTUsuarios.getColumns().addAll(col_id,col_nombre,col_c_material,col_u_material,col_d_material,col_c_herramienta,col_u_herramienta,col_d_herramienta,col_pedidos,col_c_t_material,col_u_t_material,col_d_t_material,col_c_t_herramienta,col_u_t_herramienta,col_d_t_herramienta,col_roles,col_empleados,col_restaurarbd,col_respaldarbd);

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
            Tipo_Usuario t= new Tipo_Usuario(rsRoles.getInt("id_rol"),rsRoles.getString("nombre_rol"), VerificarBoolean(rsRoles.getInt("create_material") == 1), VerificarBoolean(rsRoles.getInt("update_material") == 1), VerificarBoolean(rsRoles.getInt("delete_material") == 1), VerificarBoolean(rsRoles.getInt("create_herramienta") == 1), VerificarBoolean(rsRoles.getInt("update_herramienta") == 1), VerificarBoolean(rsRoles.getInt("delete_herramienta") == 1), VerificarBoolean(rsRoles.getInt("crud_pedido") == 1), VerificarBoolean(rsRoles.getInt("create_t_material") == 1), VerificarBoolean(rsRoles.getInt("update_t_material") == 1), VerificarBoolean(rsRoles.getInt("delete_t_material") == 1), VerificarBoolean(rsRoles.getInt("create_t_herramienta") == 1), VerificarBoolean(rsRoles.getInt("update_t_herramienta") == 1), VerificarBoolean(rsRoles.getInt("update_t_herramienta") == 1), VerificarBoolean(rsRoles.getInt("crud_roles") == 1), VerificarBoolean(rsRoles.getInt("crud_empleados") == 1), VerificarBoolean(rsRoles.getInt("restaurar_bd") == 1), VerificarBoolean(rsRoles.getInt("respaldar_bd") == 1));
            tableTUsuarios.getItems().add(t);
        }
        lblContRegistros.setText("Se cargaron "+cont+" articulos");
    }

    @FXML private void NewRol() throws SQLException {
        txtId_rol.setDisable(true);
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
                        "`crud_pedido`='"+VerificarCheckBox(check_crud_pedido)+"',`create_t_material`='"+VerificarCheckBox(check_create_t_material)+"',`update_t_material`='"+VerificarCheckBox(check_update_t_material)+"',`delete_t_material`='"+VerificarCheckBox(check_delete_t_material)+"'," +
                        "`create_t_herramienta`='"+VerificarCheckBox(check_create_t_herramienta)+"',`update_t_herramienta`='"+VerificarCheckBox(check_update_t_herramienta)+"',`delete_t_herramienta`='"+VerificarCheckBox(check_delete_t_herramienta)+"',`crud_roles`='"+VerificarCheckBox(check_crud_roles)+"'," +
                        "`crud_empleados`='"+VerificarCheckBox(check_crud_empleados)+"',`restaurar_bd`='"+VerificarCheckBox(check_restaurar_bd)+"',`respaldar_bd`='"+VerificarCheckBox(check_respaldar_bd)+"' WHERE `id_rol`='"+resultSetUpdate.getInt("id_rol")+"'");
                ResultSet resultSetEmpleados= conexion.consultar("SELECT * FROM `usuario` WHERE `nombre_rol`='"+resultSetUpdate.getString("nombre_rol")+"'");
                while(resultSetEmpleados.next()){
                    conexion.insmodelim("UPDATE `usuario` SET `nombre_rol`='"+txtNombre_rol.getText()+"' WHERE `id_user`='"+resultSetEmpleados.getInt("id_user")+"'");
                }
            }else {
                conexion.insmodelim("INSERT INTO `tipo_usuario`(`nombre_rol`, `create_material`, `update_material`, `delete_material`, `create_herramienta`, `update_herramienta`, `delete_herramienta`, `crud_pedido`, `create_t_material`, `update_t_material`, `delete_t_material`, `create_t_herramienta`, `update_t_herramienta`, `delete_t_herramienta`, `crud_roles`, `crud_empleados`, `restaurar_bd`, `respaldar_bd`) VALUES " +
                        "('"+txtNombre_rol.getText()+"','"+VerificarCheckBox(check_create_material)+"','"+VerificarCheckBox(check_update_material)+"'," +
                        "'"+VerificarCheckBox(check_delete_material)+"','"+VerificarCheckBox(check_create_herramienta)+"','"+VerificarCheckBox(check_update_herramienta)+"','"+VerificarCheckBox(check_delete_herramienta)+"'," +
                        "'"+VerificarCheckBox(check_crud_pedido)+"','"+VerificarCheckBox(check_create_t_material)+"','"+VerificarCheckBox(check_update_t_material)+"','"+VerificarCheckBox(check_delete_t_material)+"'," +
                        "'"+VerificarCheckBox(check_create_t_herramienta)+"','"+VerificarCheckBox(check_update_t_herramienta)+"','"+VerificarCheckBox(check_delete_t_herramienta)+"','"+VerificarCheckBox(check_crud_roles)+"'," +
                        "'"+VerificarCheckBox(check_crud_empleados)+"','"+VerificarCheckBox(check_restaurar_bd)+"','"+VerificarCheckBox(check_respaldar_bd)+"')");
            }
            tabPaneVentana.getSelectionModel().select(tabSearch);
            tabSearch.setDisable(false);
            tabNew.setDisable(true);
            ActivateBtn(false,true,false,true,false,false);
            txtId_rol.setDisable(false);
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
            check_create_t_material.setSelected(VerificarString(tipoUsuario.getCreate_t_material()));
            check_update_t_material.setSelected(VerificarString(tipoUsuario.getUpdate_t_material()));
            check_delete_t_material.setSelected(VerificarString(tipoUsuario.getDelete_t_material()));
            check_create_t_herramienta.setSelected(VerificarString(tipoUsuario.getCreate_t_herramienta()));
            check_update_t_herramienta.setSelected(VerificarString(tipoUsuario.getUpdate_t_herramienta()));
            check_delete_t_herramienta.setSelected(VerificarString(tipoUsuario.getDelete_t_herramienta()));
            check_crud_roles.setSelected(VerificarString(tipoUsuario.getCrud_roles()));
            check_crud_empleados.setSelected(VerificarString(tipoUsuario.getCrud_empleados()));
            check_restaurar_bd.setSelected(VerificarString(tipoUsuario.getRestaurar_bd()));
            check_respaldar_bd.setSelected(VerificarString(tipoUsuario.getRespaldar_bd()));
            txtNombre_rol.setText(tipoUsuario.getNombre_rol());
            txtId_rol.setDisable(true);
            ActivateBtn(true,false,true,false,false,true);
        }else {

        }
    }

    @FXML private void DeleteRol() throws SQLException {
        if (tableTUsuarios.getSelectionModel().getSelectedItem() != null){
            Tipo_Usuario t = (Tipo_Usuario) tableTUsuarios.getSelectionModel().getSelectedItem();
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
        txtId_rol.setDisable(false);
        CleanTextFields();
        ActivateBtn(false,true,false,true,false,false);
        tabPaneVentana.getSelectionModel().select(tabSearch);
        tabSearch.setDisable(false);
        tabNew.setDisable(true);
        ClearCheckBox();
    }

    @FXML private void ExitRol(){

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
        check_create_t_material.setSelected(false);
        check_update_t_material.setSelected(false);
        check_delete_t_material.setSelected(false);
                check_create_t_herramienta.setSelected(false);
        check_update_t_herramienta.setSelected(false);
                check_delete_t_herramienta.setSelected(false);
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
        if (LoginController.resultado.getInt("create_herramienta")==0){
            btnNew.setDisable(true);
        }else {btnNew.setDisable(New);}
        if (LoginController.resultado.getInt("update_herramienta")==0){
            btnEdit.setDisable(true);
        }else {btnEdit.setDisable(edit);}
        if (LoginController.resultado.getInt("delete_herramienta")==0){
            btnDelete.setDisable(true);
        }else {btnDelete.setDisable(delete);}

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
        if (val.equals("Puede")){
            return true;
        }else {
            return false;
        }
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

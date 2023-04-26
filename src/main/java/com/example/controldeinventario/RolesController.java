package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Articulo;
import com.example.controldeinventario.Datos.Tipo_Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        col_c_material.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("create_material"));
        col_u_material.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("update_material"));
        col_d_material.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("delete_material"));
        col_c_herramienta.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("create_herramienta"));
        col_u_herramienta.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("update_herramienta"));
        col_d_herramienta.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("delete_herramienta"));
        col_pedidos.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("crud_pedido"));
        col_c_t_material.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("create_t_material"));
        col_u_t_material.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("update_t_material"));
        col_d_t_material.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("delete_t_material"));
        col_c_t_herramienta.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("create_t_herramienta"));
        col_u_t_herramienta.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("update_t_herramienta"));
        col_d_t_herramienta.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("delete_t_herramienta"));
        col_roles.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("crud_roles"));
        col_empleados.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("crud_empleados"));
        col_restaurarbd.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("restaurar_bd"));
        col_respaldarbd.setCellValueFactory(new PropertyValueFactory<Tipo_Usuario,Boolean>("respaldar_bd"));

        tableTUsuarios.getColumns().addAll(col_id,col_nombre,col_c_material,col_u_material,col_d_material,col_c_herramienta,col_u_herramienta,col_d_herramienta,col_pedidos,col_c_t_material,col_u_t_material,col_d_t_material,col_c_t_herramienta,col_u_t_herramienta,col_d_t_herramienta,col_roles,col_empleados,col_restaurarbd,col_respaldarbd);

        rbID.setToggleGroup(toggleGroupBusqueda);
        rbNombre.setToggleGroup(toggleGroupBusqueda);

        conexion = new Conexion();
        ActualizarTabla(conexion.consultar("SELECT * FROM `tipo_usuario`"));

    }
    private void ActualizarTabla(ResultSet rsRoles) throws SQLException {
        int cont=0;
        tableTUsuarios.getItems().clear();
        while (rsRoles.next()){
            cont++;
            Tipo_Usuario t= new Tipo_Usuario(rsRoles.getInt("id_rol"),rsRoles.getString("nombre_rol"), rsRoles.getInt("create_material") == 1, rsRoles.getInt("update_material") == 1, rsRoles.getInt("delete_material") == 1, rsRoles.getInt("create_herramienta") == 1, rsRoles.getInt("update_herramienta") == 1, rsRoles.getInt("delete_herramienta") == 1, rsRoles.getInt("crud_pedido") == 1, rsRoles.getInt("create_t_material") == 1, rsRoles.getInt("update_t_material") == 1, rsRoles.getInt("delete_t_material") == 1, rsRoles.getInt("create_t_herramienta") == 1, rsRoles.getInt("update_t_herramienta") == 1, rsRoles.getInt("delete_t_herramienta") == 1, rsRoles.getInt("crud_roles") == 1, rsRoles.getInt("crud_empleados") == 1, rsRoles.getInt("restaurar_bd") == 1, rsRoles.getInt("respaldar_bd") == 1);
            tableTUsuarios.getItems().add(t);
        }
        lblContRegistros.setText("Se cargaron "+cont+" articulos");
    }

}

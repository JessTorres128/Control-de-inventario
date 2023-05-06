package com.example.controldeinventario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrincipalController {
    Stage ventanaSecundaria;
    @FXML MenuItem menuItemB;
    @FXML MenuItem menuItemIniciarSesion, menuItemCerrarSesion, menuItemCerrarPrograma;
    @FXML MenuItem menuItemMateriales, menuItemHerramientas;
    @FXML MenuItem menuItemPedidos;
    @FXML MenuItem menuItemTMateriales, menuItemTHerramientas;
    @FXML MenuItem menuItemRoles, menuItemEmpleados;
    @FXML MenuItem menuItemRespaldarBD, menuItemRestaurarBD;

    Conexion conexion;

    @FXML protected void initialize() throws SQLException {
        conexion=new Conexion();
        HabilitarMenus(LoginController.resultado);
       // Image imgSearch= new Image("",25,25,false,true);menuItemB.setGraphic(new ImageView(imgSearch));
    }






    //Controles del menú
    @FXML private void IngresarArticulos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Articulos.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);


    }
    @FXML private void IngresarHerramientas() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Herramientas.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);

    }
    @FXML private void IngresarLogin() throws IOException {
        HelloApplication.primarystage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.primarystage.setTitle("Inventario");
        HelloApplication.primarystage.setScene(scene);
        HelloApplication.primarystage.show();

        HelloApplication.primarystage.setResizable(false);

    }
    @FXML private void IngresarRoles() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Roles.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);

    }
    @FXML private void IngresarEmpleados() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Empleados.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);

    }
    @FXML private void IngresarPedidos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Pedidos.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);

    }
    @FXML private void IngresarTipos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TipoArticulo.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);
    }
    public void CerrarVentana(){
        ventanaSecundaria.close();
    }
    private void AbrirVentana(Parent root){
        ventanaSecundaria = new Stage();
        ventanaSecundaria.initModality(Modality.APPLICATION_MODAL);
        ventanaSecundaria.initOwner(HelloApplication.primarystage);
        ventanaSecundaria.setScene(new Scene(root));
        ventanaSecundaria.show();
    }
    private void HabilitarMenus(ResultSet resultSetUsuario) throws SQLException {
        menuItemMateriales.setDisable(resultSetUsuario.getInt("create_material") != 1 && resultSetUsuario.getInt("update_material") != 1 && resultSetUsuario.getInt("delete_material") != 1);
        menuItemHerramientas.setDisable(resultSetUsuario.getInt("create_herramienta") != 1 && resultSetUsuario.getInt("update_herramienta") != 1 && resultSetUsuario.getInt("delete_herramienta") != 1);
        menuItemPedidos.setDisable(resultSetUsuario.getInt("crud_pedido") != 1);
        menuItemTMateriales.setDisable(resultSetUsuario.getInt("create_t_articulo") != 1 && resultSetUsuario.getInt("update_t_articulo") != 1 && resultSetUsuario.getInt("delete_t_articulo") != 1);
        menuItemRoles.setDisable(resultSetUsuario.getInt("crud_roles") != 1);
        menuItemEmpleados.setDisable(resultSetUsuario.getInt("crud_empleados") != 1);
        menuItemRestaurarBD.setDisable(resultSetUsuario.getInt("restaurar_bd") != 1);
        menuItemRespaldarBD.setDisable(resultSetUsuario.getInt("respaldar_bd") != 1);
    }
}

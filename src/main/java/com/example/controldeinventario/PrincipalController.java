package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Articulo;
import com.example.controldeinventario.Datos.Herramienta;
import com.example.controldeinventario.Datos.Pedido;
import com.example.controldeinventario.Datos.Registro;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Material;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrincipalController {
    Stage ventanaSecundaria;
    @FXML
    MenuItem menuItemB;
    @FXML
    MenuItem menuItemIniciarSesion, menuItemCerrarSesion, menuItemCerrarPrograma;
    @FXML
    MenuItem menuItemMateriales, menuItemHerramientas;
    @FXML
    MenuItem menuItemPedidos;
    @FXML
    MenuItem menuItemTMateriales;
    @FXML
    MenuItem menuItemRoles, menuItemEmpleados;
    @FXML
    MenuItem menuItemGenerarBD, menuItemEliminarBD, menuItemRestaurarBD;

    Conexion conexion;

    @FXML
    protected void initialize() throws SQLException {
        conexion = new Conexion();
        HabilitarMenus(LoginController.resultado);
        // Image imgSearch= new Image("",25,25,false,true);menuItemB.setGraphic(new ImageView(imgSearch));


    }


    //Controles del menú
    @FXML
    private void IngresarArticulos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Articulos.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);


    }
                      //   E      X     C      E     L     L
    @FXML
    private void ExportarBD() throws IOException, SQLException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Materiales");
        Sheet sheet1 = workbook.createSheet("Herramientas");
        Sheet sheet2 = workbook.createSheet("Pedidos");

        Font headerFont = workbook.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 12);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        CellStyle dataStyle = workbook.createCellStyle();
        CellStyle dataStyleColor = workbook.createCellStyle();
        dataStyleColor.setFillForegroundColor(IndexedColors.RED.getIndex()); // Cambia "RED" al color deseado
        dataStyleColor.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Crear una fila para el título
        Row titleRow = sheet.createRow(0);
        Row titleRow1 = sheet1.createRow(0);
        Row titleRow2 = sheet2.createRow(0);


        // Crear una celda para el título
        Cell titleCell = titleRow.createCell(0);
        Cell titleCell1 = titleRow1.createCell(0);
        Cell titleCell2 = titleRow2.createCell(0);
        titleCell.setCellValue("Inventario de materiales");
        titleCell1.setCellValue("Inventario de herramientas");
        titleCell2.setCellValue("Registro de pedidos");

        // Combinar las celdas para crear una celda de título grande
        CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, 11);
        sheet.addMergedRegion(titleRange);
        sheet1.addMergedRegion(titleRange);
        sheet2.addMergedRegion(titleRange);

        // Establecer el estilo de la celda de título
        CellStyle titleStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();

        titleFont.setFontHeightInPoints((short) 16);
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
//        titleStyle.setAlignment((short) 2);
        titleCell.setCellStyle(titleStyle);
        titleCell1.setCellStyle(titleStyle);
        titleCell2.setCellStyle(titleStyle);


        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);


        sheet.autoSizeColumn(0); // ajustar automáticamente el ancho de la columna 0
        sheet1.autoSizeColumn(0); // ajustar automáticamente el ancho de la columna 0
        sheet2.autoSizeColumn(0); // ajustar automáticamente el ancho de la columna 0











            // Crear el archivo de selección


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
    @FXML private void IngresarGenerar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Generar.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);
    }
    @FXML private void IngresarRestaurar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restaurar.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);
    }
    @FXML private void IngresarBorrar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Borrar.fxml"));
        Parent root = fxmlLoader.load();
        AbrirVentana(root);
    }
    @FXML public void CerrarVentana(){
        Platform.exit();
        System.exit(0);
    }
    private void AbrirVentana(Parent root){
        ventanaSecundaria = new Stage();
        ventanaSecundaria.initModality(Modality.APPLICATION_MODAL);
        ventanaSecundaria.initOwner(HelloApplication.primarystage);
        ventanaSecundaria.setScene(new Scene(root));
        ventanaSecundaria.show();

    }
    private void HabilitarMenus(ResultSet resultSetUsuario) throws SQLException {
    /*    menuItemMateriales.setDisable(resultSetUsuario.getInt("create_material") != 1 && resultSetUsuario.getInt("update_material") != 1 && resultSetUsuario.getInt("delete_material") != 1);
        menuItemHerramientas.setDisable(resultSetUsuario.getInt("create_herramienta") != 1 && resultSetUsuario.getInt("update_herramienta") != 1 && resultSetUsuario.getInt("delete_herramienta") != 1);
        menuItemPedidos.setDisable(resultSetUsuario.getInt("crud_pedido") != 1);
        menuItemTMateriales.setDisable(resultSetUsuario.getInt("create_t_articulo") != 1 && resultSetUsuario.getInt("update_t_articulo") != 1 && resultSetUsuario.getInt("delete_t_articulo") != 1);
        menuItemRoles.setDisable(resultSetUsuario.getInt("crud_roles") != 1);
        menuItemEmpleados.setDisable(resultSetUsuario.getInt("crud_empleados") != 1);*/
        menuItemGenerarBD.setDisable(resultSetUsuario.getInt("generar_bd") != 1);
        menuItemRestaurarBD.setDisable(resultSetUsuario.getInt("respaldar_bd") != 1);
        menuItemEliminarBD.setDisable(resultSetUsuario.getInt("eliminar_bd") != 1);

    }
}

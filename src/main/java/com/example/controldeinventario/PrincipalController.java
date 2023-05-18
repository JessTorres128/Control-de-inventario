package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Articulo;
import com.example.controldeinventario.Datos.Herramienta;
import com.example.controldeinventario.Datos.Pedido;
import com.example.controldeinventario.Datos.Registro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

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
    MenuItem menuItemTMateriales, menuItemTHerramientas;
    @FXML
    MenuItem menuItemRoles, menuItemEmpleados;
    @FXML
    MenuItem menuItemRespaldarBD, menuItemRestaurarBD;

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

    @FXML
    private void ExportarBD() throws IOException, SQLException {

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Materiales");
        Sheet sheet1 = workbook.createSheet("Herramientas");
        Sheet sheet2 = workbook.createSheet("Pedidos");

        Font headerFont = workbook.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 12);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment((short) 2);
        dataStyle.setVerticalAlignment((short) 2);

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
        titleStyle.setAlignment((short) 2);
        titleCell.setCellStyle(titleStyle);
        titleCell1.setCellStyle(titleStyle);
        titleCell2.setCellStyle(titleStyle);


        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setVerticalAlignment((short) 2);


        sheet.autoSizeColumn(0); // ajustar automáticamente el ancho de la columna 0
        sheet1.autoSizeColumn(0); // ajustar automáticamente el ancho de la columna 0
        sheet2.autoSizeColumn(0); // ajustar automáticamente el ancho de la columna 0


        Row headerRow = sheet.createRow(2);
        headerRow.createCell(3).setCellValue("Codigo");
        headerRow.createCell(4).setCellValue("Armario");
        headerRow.createCell(5).setCellValue("Gaveta");
        headerRow.createCell(6).setCellValue("Sub_compartimiento");
        headerRow.createCell(7).setCellValue("Material");
        headerRow.createCell(8).setCellValue("Tipo");
        headerRow.createCell(9).setCellValue("Numero de parte");
        headerRow.createCell(10).setCellValue("Valor");
        headerRow.createCell(11).setCellValue("Unidad de medida");
        headerRow.createCell(12).setCellValue("Caracteristicas");
        headerRow.createCell(13).setCellValue("Cantidad");
        headerRow.createCell(14).setCellValue("Cantidad minima");


        for (int i = 3; i < 15; i++) {
            headerRow.getCell(i).setCellStyle(headerCellStyle);
        }
        // Después de crear todas las celdas
        // ajustar automáticamente el ancho de las columnas de 0 a 2
        for (int i = 3; i <= 14; i++) {
            sheet.autoSizeColumn(i);
        }


        int rowIndex = 3;
        ResultSet rsArticulos = conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material;");

        while (rsArticulos.next()) {
            Articulo producto = new Articulo(rsArticulos.getLong("cb_material"), rsArticulos.getString("tipo_de_armario"), rsArticulos.getString("gaveta"), rsArticulos.getString("sub_compartimento"), rsArticulos.getString("material"),
                    rsArticulos.getString("tipo"), rsArticulos.getString("numero_parte"), rsArticulos.getDouble("valor"), rsArticulos.getString("unidad_de_medida"), rsArticulos.getString("caracteristicas"), rsArticulos.getString("frecuencia_de_uso"),
                    rsArticulos.getInt("cantidad"), rsArticulos.getInt("cantidad_min"));

            Row dataRow = sheet.createRow(rowIndex++);
            dataRow.createCell(3).setCellValue(producto.getCodigo_barras());
            dataRow.createCell(4).setCellValue(producto.getTipo_de_armario());
            dataRow.createCell(5).setCellValue(producto.getGaveta());
            dataRow.createCell(6).setCellValue(producto.getSub_compartimento());
            dataRow.createCell(7).setCellValue(producto.getMaterial());
            dataRow.createCell(8).setCellValue(producto.getTipo());
            dataRow.createCell(9).setCellValue(producto.getNumero_parte());
            dataRow.createCell(10).setCellValue(producto.getValor());
            dataRow.createCell(11).setCellValue(producto.getUnidad_medida());
            dataRow.createCell(12).setCellValue(producto.getCaracteristicas());
            dataRow.createCell(13).setCellValue(producto.getCantidad());
            dataRow.createCell(14).setCellValue(producto.getCantidad_min());


            for (int i = 3; i < sheet.getRow(0).getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }

            for (int i = 3; i <= 14; i++) {
                sheet.autoSizeColumn(i);
            }

            for (int i = 3; i < sheet.getRow(0).getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }

        }


        Row headerRow1 = sheet1.createRow(2);

        headerRow1.createCell(3).setCellValue("CB Herramienta");
        headerRow1.createCell(4).setCellValue("Herramienta");
        headerRow1.createCell(5).setCellValue("Tipo");
        headerRow1.createCell(6).setCellValue("Caracteristicas");
        headerRow1.createCell(7).setCellValue("Frecuencia de uso");
        headerRow1.createCell(8).setCellValue("Cantidad");
        headerRow1.createCell(9).setCellValue("Cantidad minima");

        for (int i = 3; i < 10; i++) {
            headerRow1.getCell(i).setCellStyle(headerCellStyle);
        }

        for (int i = 3; i <= 9; i++) {
            sheet1.autoSizeColumn(i);
        }
        int rowIndex1 = 3;
        ResultSet rsHerramienta = conexion.consultar("SELECT * FROM `herramienta` INNER JOIN tipo_material ON herramienta.id_herramienta = tipo_material.id_material;");
        while (rsHerramienta.next()) {
            Herramienta herramienta = new Herramienta(rsHerramienta.getLong("cb_herramienta"), rsHerramienta.getString("material"), rsHerramienta.getString("tipo"), rsHerramienta.getString("caracteristicas"), rsHerramienta.getString("frecuencia_de_uso"),
                    rsHerramienta.getInt("cantidad"), rsHerramienta.getInt("cantidad_min"));

            Row dataRow1 = sheet1.createRow(rowIndex1++);
            dataRow1.createCell(3).setCellValue(herramienta.getCb_herramienta());
            dataRow1.createCell(4).setCellValue(herramienta.getHerramienta());
            dataRow1.createCell(5).setCellValue(herramienta.getTipo());
            dataRow1.createCell(6).setCellValue(herramienta.getCaracteristicas());
            dataRow1.createCell(7).setCellValue(herramienta.getFrecuencia_de_uso());
            dataRow1.createCell(8).setCellValue(herramienta.getCantidad());
            dataRow1.createCell(9).setCellValue(herramienta.getCantidad_min());

            for (int i = 3; i < sheet1.getRow(0).getLastCellNum(); i++) {
                sheet1.autoSizeColumn(i);
            }

            for (int i = 3; i <= 14; i++) {
                sheet1.autoSizeColumn(i);
            }

            for (int i = 3; i < sheet1.getRow(0).getLastCellNum(); i++) {
                sheet1.autoSizeColumn(i);
            }
        }
        Row headerRow2 = sheet2.createRow(2);

        headerRow2.createCell(3).setCellValue("ID Pedido");
        headerRow2.createCell(4).setCellValue("Nombre");
        headerRow2.createCell(5).setCellValue("Numero de control");
        headerRow2.createCell(6).setCellValue("Estado");
        headerRow2.createCell(7).setCellValue("Fecha");
        headerRow2.createCell(8).setCellValue("Profesor");
        headerRow2.createCell(9).setCellValue("Materia");


        for (int i = 3; i < 10; i++) {
            headerRow2.getCell(i).setCellStyle(headerCellStyle);
        }

        for (int i = 3; i <= 9; i++) {
            sheet2.autoSizeColumn(i);
        }


        int rowIndex2 = 3;


        ResultSet rsPedidos = conexion.consultar("SELECT * FROM `pedido`");

        ResultSet rsArticuloPedido = conexion.consultar("SELECT `tipo`,`cantidad`,`valor`,`unidad_de_medida`,tipo_material.material FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material WHERE cb_material='"+rsArticulos.getLong("cb_material")+"'");
        ResultSet rsHerramientaPedido = conexion.consultar("SELECT tipo_material.material,`tipo`,`cantidad` FROM `herramienta` INNER JOIN tipo_material ON herramienta.id_herramienta = tipo_material.id_material WHERE cb_herramienta='"+rsArticulos.getLong("cb_material")+"'");

        while (rsPedidos.next()) {
            Pedido pedido = new Pedido(rsPedidos.getInt("id_pedido"), rsPedidos.getString("nombre_persona"), rsPedidos.getString("num_control"), rsPedidos.getString("estado"), rsPedidos.getDate("fecha"),
                    rsPedidos.getString("profesor"), rsPedidos.getString("materia"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


            Row dataRow2 = sheet2.createRow(rowIndex2++);
            dataRow2.createCell(3).setCellValue(pedido.getId_pedido());
            dataRow2.createCell(4).setCellValue(pedido.getNombre_persona());
            dataRow2.createCell(5).setCellValue(pedido.getNum_control());
            dataRow2.createCell(6).setCellValue(pedido.getEstado());
            dataRow2.createCell(7).setCellValue(dateFormat.format(pedido.getFecha()));
            dataRow2.createCell(8).setCellValue(pedido.getProfesor());
            dataRow2.createCell(9).setCellValue(pedido.getMateria());


            for (int i = 3; i < sheet1.getRow(0).getLastCellNum(); i++) {
                sheet1.autoSizeColumn(i);
            }

            for (int i = 3; i <= 14; i++) {
                sheet2.autoSizeColumn(i);
            }

            for (int i = 3; i < sheet1.getRow(0).getLastCellNum(); i++) {
                sheet2.autoSizeColumn(i);
            }
        }

            // Crear el archivo de selección
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo Excel");
        // Agregar filtros para que solo se muestren archivos Excel
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel", "*.xls"),
                new FileChooser.ExtensionFilter("Excel", "*.xlsx")
        );
        // Mostrar el cuadro de diálogo de selección de archivos
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {

            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                workbook.write(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
    /*    menuItemMateriales.setDisable(resultSetUsuario.getInt("create_material") != 1 && resultSetUsuario.getInt("update_material") != 1 && resultSetUsuario.getInt("delete_material") != 1);
        menuItemHerramientas.setDisable(resultSetUsuario.getInt("create_herramienta") != 1 && resultSetUsuario.getInt("update_herramienta") != 1 && resultSetUsuario.getInt("delete_herramienta") != 1);
        menuItemPedidos.setDisable(resultSetUsuario.getInt("crud_pedido") != 1);
        menuItemTMateriales.setDisable(resultSetUsuario.getInt("create_t_articulo") != 1 && resultSetUsuario.getInt("update_t_articulo") != 1 && resultSetUsuario.getInt("delete_t_articulo") != 1);
        menuItemRoles.setDisable(resultSetUsuario.getInt("crud_roles") != 1);
        menuItemEmpleados.setDisable(resultSetUsuario.getInt("crud_empleados") != 1);*/
        menuItemRestaurarBD.setDisable(resultSetUsuario.getInt("restaurar_bd") != 1);
        menuItemRespaldarBD.setDisable(resultSetUsuario.getInt("respaldar_bd") != 1);
    }
}

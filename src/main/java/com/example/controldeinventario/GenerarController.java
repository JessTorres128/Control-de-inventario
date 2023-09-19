package com.example.controldeinventario;

import com.example.controldeinventario.Datos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class GenerarController {
    public ObservableList<TipoArticulo> registros = FXCollections.observableArrayList();
    Conexion conexion;
    @FXML ComboBox<String> cbFormato;
    @FXML  CheckBox checkBoxMaterial, checkBoxHerramienta, checkBoxAlumnos, checkBoxPedidos, checkBoxHorarios, checkBoxTiposMaterial, checkBoxTiposUsuarios, checkBoxUsuarios;
    @FXML HBox pdf;
    @FXML VBox excel,imagencb;
    TableColumn<TipoArticulo,String> tableColumnNo= new TableColumn<>();
    TableColumn<TipoArticulo,String> tableColumnName= new TableColumn<>();
    TableColumn<TipoArticulo,String> tableColumnCat= new TableColumn<>();
    @FXML TableView<TipoArticulo> tableViewCategoria;
    @FXML protected void initialize() throws SQLException {
        conexion = new Conexion();
        cbFormato.getItems().clear();
        cbFormato.getItems().addAll("Excel","PDF","JPG");
        cbFormato.getSelectionModel().select(0);

        tableColumnName.setCellValueFactory(new PropertyValueFactory<TipoArticulo,String>("nombre"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<TipoArticulo,String>("nombre"));
        tableColumnCat.setCellValueFactory(new PropertyValueFactory<TipoArticulo,String>("t_material"));
        tableViewCategoria.getColumns().addAll(tableColumnName,tableColumnCat);
        tableViewCategoria.setItems(registros);
        ActualizarTabla(conexion.consultar("SELECT * FROM `tipo_material`"));
    }

    private void ActualizarTabla(ResultSet rsCategoria) throws SQLException {
        tableViewCategoria.getItems().clear();
        while (rsCategoria.next()){
            TipoArticulo tipoArticulo = new TipoArticulo(rsCategoria.getInt("id_material"),rsCategoria.getString("material"),rsCategoria.getString("tipo_material"));
            tableViewCategoria.getItems().add(tipoArticulo);
        }

    }
    @FXML private void TurnOff(){
        excel.setVisible(cbFormato.getSelectionModel().getSelectedItem().equals("Excel"));
        pdf.setVisible(cbFormato.getSelectionModel().getSelectedItem().equals("PDF"));
        imagencb.setVisible(cbFormato.getSelectionModel().getSelectedItem().equals("JPG"));
    }

    @FXML protected void GenerarExcel(boolean bmaterial, boolean bherramienta, boolean bpedido, boolean balumnos, boolean bhorarios, boolean btmateriales, boolean broles, boolean busuarios) throws SQLException {
        Workbook excel = new HSSFWorkbook();
        Font headerFont = excel.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 12);

        CellStyle headerCellStyle = excel.createCellStyle();
        headerCellStyle.setFont(headerFont);

        CellStyle dataStyle = excel.createCellStyle();
        CellStyle dataStyleColor = excel.createCellStyle();
        dataStyleColor.setFillForegroundColor(IndexedColors.RED.getIndex()); // Cambia "RED" al color deseado
        dataStyleColor.setFillPattern((short) 1);
        dataStyle.setAlignment((short) 2);
        dataStyle.setVerticalAlignment((short) 2);

        CellStyle titleStyle = excel.createCellStyle();
        Font titleFont = excel.createFont();

        titleFont.setFontHeightInPoints((short) 16);
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment((short) 2);

        CellStyle headerStyle = excel.createCellStyle();
        headerStyle.setVerticalAlignment((short) 2);
        if (checkBoxMaterial.isSelected()){
            Sheet material = excel.createSheet("Material");
            Row titleRow = material.createRow(0);
            Cell titleCell = titleRow.createCell(3);
            titleCell.setCellValue("Inventario de Materiales");
            CellRangeAddress rango = new CellRangeAddress(0, 0, 3, 15);
            material.addMergedRegion(rango);

            titleCell.setCellStyle(titleStyle);
            material.autoSizeColumn(0);

            //SETUPPPPP------------------------------------------------------------------------------------------------------------------------------------------------
            Row headerRow = material.createRow(2);

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
            headerRow.createCell(15).setCellValue("Tipo de material");



            for (int i = 3; i < 16; i++) {
                headerRow.getCell(i).setCellStyle(headerCellStyle);
            }
            for (int i = 3; i <= 15; i++) {
                material.autoSizeColumn(i);
            }


            int rowIndex = 3;
            ResultSet rsArticulos = conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material;");

            while (rsArticulos.next()) {
                Articulo producto = new Articulo(rsArticulos.getLong("cb_material"), rsArticulos.getString("tipo_de_armario"), rsArticulos.getString("gaveta"), rsArticulos.getString("sub_compartimento"), rsArticulos.getString("material"),
                        rsArticulos.getString("tipo"), rsArticulos.getString("numero_parte"), rsArticulos.getString("valor"), rsArticulos.getString("unidad_de_medida"), rsArticulos.getString("caracteristicas"), rsArticulos.getString("frecuencia_de_uso"),
                        rsArticulos.getInt("cantidad"), rsArticulos.getInt("cantidad_min"));

                Row dataRow = material.createRow(rowIndex++);
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
                dataRow.createCell(15).setCellValue(rsArticulos.getString("tipo_material"));

                if (producto.getCantidad() < producto.getCantidad_min()){
                    dataRow.getCell(3).setCellStyle(dataStyleColor);
                    dataRow.getCell(4).setCellStyle(dataStyleColor);
                    dataRow.getCell(5).setCellStyle(dataStyleColor);
                    dataRow.getCell(6).setCellStyle(dataStyleColor);
                    dataRow.getCell(7).setCellStyle(dataStyleColor);
                    dataRow.getCell(8).setCellStyle(dataStyleColor);
                    dataRow.getCell(9).setCellStyle(dataStyleColor);
                    dataRow.getCell(10).setCellStyle(dataStyleColor);
                    dataRow.getCell(11).setCellStyle(dataStyleColor);
                    dataRow.getCell(12).setCellStyle(dataStyleColor);
                    dataRow.getCell(13).setCellStyle(dataStyleColor);
                    dataRow.getCell(14).setCellStyle(dataStyleColor);
                    dataRow.getCell(15).setCellStyle(dataStyleColor);
                }

                for (int i = 3; i < material.getRow(0).getLastCellNum(); i++) {
                    material.autoSizeColumn(i);
                }

                for (int i = 3; i <= 15; i++) {
                    material.autoSizeColumn(i);
                }

                for (int i = 3; i < material.getRow(0).getLastCellNum(); i++) {
                    material.autoSizeColumn(i);
                }

            }
        }

        if (checkBoxHerramienta.isSelected()){
            Sheet herramienta = excel.createSheet("Herramientas");
            Row titleRow = herramienta.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Inventario de herramientas");
            CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, 11);
            herramienta.addMergedRegion(titleRange);
            titleCell.setCellStyle(titleStyle);
            herramienta.autoSizeColumn(0);
            //SETUP---------------------------------------------------------------------------------------------------------------------------------------------

            Row headerRow1 = herramienta.createRow(2);

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
                herramienta.autoSizeColumn(i);
            }
            int rowIndex = 3;
            ResultSet rsHerramienta = conexion.consultar("SELECT * FROM `herramienta` INNER JOIN tipo_material ON herramienta.id_herramienta = tipo_material.id_material;");
            while (rsHerramienta.next()) {
                Herramienta herramientas = new Herramienta(rsHerramienta.getLong("cb_herramienta"), rsHerramienta.getString("material"), rsHerramienta.getString("tipo"), rsHerramienta.getString("caracteristicas"), rsHerramienta.getString("frecuencia_de_uso"),
                        rsHerramienta.getInt("cantidad"), rsHerramienta.getInt("cantidad_min"));

                Row dataRow = herramienta.createRow(rowIndex++);
                dataRow.createCell(3).setCellValue(herramientas.getCb_herramienta());
                dataRow.createCell(4).setCellValue(herramientas.getHerramienta());
                dataRow.createCell(5).setCellValue(herramientas.getTipo());
                dataRow.createCell(6).setCellValue(herramientas.getCaracteristicas());
                dataRow.createCell(7).setCellValue(herramientas.getFrecuencia_de_uso());
                dataRow.createCell(8).setCellValue(herramientas.getCantidad());
                dataRow.createCell(9).setCellValue(herramientas.getCantidad_min());


                if (herramientas.getCantidad() < herramientas.getCantidad_min()){
                    dataRow.getCell(3).setCellStyle(dataStyleColor);
                    dataRow.getCell(4).setCellStyle(dataStyleColor);
                    dataRow.getCell(5).setCellStyle(dataStyleColor);
                    dataRow.getCell(6).setCellStyle(dataStyleColor);
                    dataRow.getCell(7).setCellStyle(dataStyleColor);
                    dataRow.getCell(8).setCellStyle(dataStyleColor);
                    dataRow.getCell(9).setCellStyle(dataStyleColor);
                }
                for (int i = 3; i < herramienta.getRow(0).getLastCellNum(); i++) {
                    herramienta.autoSizeColumn(i);
                }

                for (int i = 3; i <= 14; i++) {
                    herramienta.autoSizeColumn(i);
                }

                for (int i = 3; i < herramienta.getRow(0).getLastCellNum(); i++) {
                    herramienta.autoSizeColumn(i);
                }
            }
        }

        if (checkBoxPedidos.isSelected()){
            Sheet pedidos = excel.createSheet("Pedidos");
            Row titleRow = pedidos.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Registro de pedidos");
            CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, 11);
            pedidos.addMergedRegion(titleRange);
            titleCell.setCellStyle(titleStyle);
            pedidos.autoSizeColumn(0);
            //SE>TUP_-------------------------
            int rowIndex2 = 2;


            ResultSet rsPedidos = conexion.consultar("SELECT * FROM `pedido`");


            //ResultSet rsHerramientaPedido = conexion.consultar("SELECT tipo_material.material,`tipo`,`cantidad` FROM `herramienta` INNER JOIN tipo_material ON herramienta.id_herramienta = tipo_material.id_material WHERE cb_herramienta='"+rsArticulos.getLong("cb_material")+"'");

            while (rsPedidos.next()) {
                Row headerRow2 = pedidos.createRow(rowIndex2++);

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
                    pedidos.autoSizeColumn(i);
                }

                Pedido pedido = new Pedido(rsPedidos.getInt("id_pedido"), rsPedidos.getString("nombre_persona"), rsPedidos.getString("num_control"), rsPedidos.getString("estado"), rsPedidos.getDate("fecha"),
                        rsPedidos.getString("profesor"), rsPedidos.getString("materia"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


                Row dataRow2 = pedidos.createRow(rowIndex2++);
                dataRow2.createCell(3).setCellValue(pedido.getId_pedido());
                dataRow2.createCell(4).setCellValue(pedido.getNombre_persona());
                dataRow2.createCell(5).setCellValue(pedido.getNum_control());
                dataRow2.createCell(6).setCellValue(pedido.getEstado());
                dataRow2.createCell(7).setCellValue(dateFormat.format(pedido.getFecha()));
                dataRow2.createCell(8).setCellValue(pedido.getProfesor());
                dataRow2.createCell(9).setCellValue(pedido.getMateria());
                ResultSet rsPedido = conexion.consultar("SELECT * FROM `pedido_material` WHERE `id_pedido`='"+pedido.getId_pedido()+"'");
                Row filaInfoP = pedidos.createRow(rowIndex2++);
                filaInfoP.createCell(3).setCellValue("Código de barras");
                filaInfoP.createCell(4).setCellValue("Cantidad");
                filaInfoP.createCell(5).setCellValue("Material");
                filaInfoP.createCell(6).setCellValue("Tipo");
                filaInfoP.createCell(7).setCellValue("Valor");
                filaInfoP.createCell(8).setCellValue("Unidad de medida");
                filaInfoP.createCell(9).setCellValue("Estado");
                while (rsPedido.next()){
                    Row filaMaterialP = pedidos.createRow(rowIndex2++);
                    ResultSet rsArticulo = conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material WHERE `cb_material`='"+rsPedido.getLong("cb_material")+"'");
                    ResultSet rsHerramientaDN = conexion.consultar("SELECT * FROM `herramienta` INNER JOIN tipo_material ON herramienta.id_herramienta = tipo_material.id_material WHERE `cb_herramienta`='"+rsPedido.getLong("cb_material")+"'");
                    if (rsArticulo.next()){
                        filaMaterialP.createCell(3).setCellValue(rsPedido.getLong("cb_material"));
                        filaMaterialP.createCell(4).setCellValue(rsPedido.getInt("cantidad"));
                        filaMaterialP.createCell(5).setCellValue(rsArticulo.getString("material"));
                        filaMaterialP.createCell(6).setCellValue(rsArticulo.getString("tipo"));
                        filaMaterialP.createCell(7).setCellValue(rsArticulo.getString("valor"));
                        filaMaterialP.createCell(8).setCellValue(rsArticulo.getString("unidad_de_medida"));
                        filaMaterialP.createCell(9).setCellValue(rsPedido.getString("estado"));
                    }else if (rsHerramientaDN.next()){
                        filaMaterialP.createCell(3).setCellValue(rsPedido.getLong("cb_material"));
                        filaMaterialP.createCell(4).setCellValue(rsPedido.getLong("cantidad"));
                        filaMaterialP.createCell(5).setCellValue(rsHerramientaDN.getString("material"));
                        filaMaterialP.createCell(6).setCellValue(rsHerramientaDN.getString("tipo"));
                        filaMaterialP.createCell(7).setCellValue("N/A");
                        filaMaterialP.createCell(8).setCellValue("N/A");
                        filaMaterialP.createCell(9).setCellValue(rsPedido.getString("estado"));
                    }


                }
                // dataRow2.createCell(10).setCellValue(pal);



                for (int i = 3; i < pedidos.getRow(0).getLastCellNum(); i++) {
                    pedidos.autoSizeColumn(i);
                }

                for (int i = 3; i <= 14; i++) {
                    pedidos.autoSizeColumn(i);
                }

                for (int i = 3; i < pedidos.getRow(0).getLastCellNum(); i++) {
                    pedidos.autoSizeColumn(i);
                }
                rowIndex2++;
            }
        }

        if (checkBoxAlumnos.isSelected()){//FALTAN A PARTIR DE AQUI

        }
        if (checkBoxHorarios.isSelected()){

        }
        if (checkBoxTiposMaterial.isSelected()){

        }
        if (checkBoxTiposUsuarios.isSelected()){

        }
        if (checkBoxUsuarios.isSelected()){
            Sheet usuarios = excel.createSheet("Usuarios");
            Row titleRow = usuarios.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Usuarios");
            CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, 11);
            usuarios.addMergedRegion(titleRange);
            titleCell.setCellStyle(titleStyle);
            usuarios.autoSizeColumn(0);

            int rowIndex = 3;
            Row headerRow1 = usuarios.createRow(2);

            headerRow1.createCell(3).setCellValue("No");
            headerRow1.createCell(4).setCellValue("Nombre Completo");
            headerRow1.createCell(5).setCellValue("Sexo");
            headerRow1.createCell(6).setCellValue("Usuario");
            headerRow1.createCell(7).setCellValue("Password");
            headerRow1.createCell(8).setCellValue("Rol");

            for (int i = 3; i < 9; i++) {
                headerRow1.getCell(i).setCellStyle(headerCellStyle);
            }

            for (int i = 3; i <= 9; i++) {
                usuarios.autoSizeColumn(i);
            }
            ResultSet rsUsers = conexion.consultar("SELECT * FROM `usuario`");
            int cont = 1;
            while(rsUsers.next()){

                Usuario usuario= new Usuario(rsUsers.getInt("id_user"),rsUsers.getString("nombre_completo"),rsUsers.getString("sexo"),
                        rsUsers.getString("username"),rsUsers.getString("password"),rsUsers.getString("nombre_rol"));

                Row dataRow = usuarios.createRow(rowIndex++);
                dataRow.createCell(3).setCellValue(cont);
                dataRow.createCell(4).setCellValue(usuario.getNombre_completo());
                dataRow.createCell(5).setCellValue(usuario.getSexo());
                dataRow.createCell(6).setCellValue(usuario.getUsername());
                dataRow.createCell(7).setCellValue(usuario.getPassword());
                dataRow.createCell(8).setCellValue(usuario.getNombre_rol());
                cont++;
                for (int i = 3; i < usuarios.getRow(0).getLastCellNum(); i++) {
                    usuarios.autoSizeColumn(i);
                }

                for (int i = 3; i <= 14; i++) {
                    usuarios.autoSizeColumn(i);
                }

                for (int i = 3; i < usuarios.getRow(0).getLastCellNum(); i++) {
                    usuarios.autoSizeColumn(i);
                }

            }
        }







        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo Excel");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel", "*.xls"),
                new FileChooser.ExtensionFilter("Excel", "*.xlsx")
        );
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {

            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                excel.write(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

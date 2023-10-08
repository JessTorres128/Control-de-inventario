package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Articulo;
import com.example.controldeinventario.Datos.Herramienta;
import com.example.controldeinventario.Datos.TipoArticulo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurarController {
    Conexion conexion;
    String ruta="";
    @FXML HBox hboxMat,hboxHerra;
    @FXML Label lblArchivo;
    @FXML CheckBox cbMat, cbHerramienta;
    @FXML TextField txtNHojaMat,txtNHojaHerra;
    @FXML TextField txtNColMat,txtNColHerra;
    @FXML Button btnCargar;


    @FXML protected void initialize(){
        conexion= new Conexion();
        ruta="";
        hboxMat.setDisable(true);
        hboxHerra.setDisable(true);
        btnCargar.setDisable(true);
        cbMat.setDisable(true);
        cbHerramienta.setDisable(true);
    }

    @FXML private void ElegirArchivo() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elegir archivo Excel");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Todos los archivos", "*.*"));
        //fileChooser.showOpenDialog(null);
        //fileChooser.showOpenMultipleDialog(null);
        File selectedDirectory = fileChooser.showOpenDialog(null);


        if (selectedDirectory != null) {
            System.out.println("Carpeta seleccionada: " + selectedDirectory.getAbsolutePath());
            ruta=selectedDirectory.getAbsolutePath();
            if (selectedDirectory.getName().endsWith(".xlsx") || selectedDirectory.getName().endsWith(".xls") || selectedDirectory.getName().endsWith(".xlsx") ){ // EXTENSIONES DE EXCEL <-------------------------------------------------------------
                System.out.println("Eres apto pa");
                lblArchivo.setText("Archivo cargado: "+selectedDirectory.getName());
                cbMat.setDisable(false);
                cbHerramienta.setDisable(false);
                btnCargar.setDisable(false);





            }else {
                System.out.println("No eres apto p");
            }


        } else {
            System.out.println("El usuario canceló la selección de carpeta.");
        }
    }
    private Workbook DetectarArchivo(String ruta) throws IOException {
        if (ruta.endsWith(".xls")){
            return new HSSFWorkbook(new FileInputStream(ruta));
        } else if (ruta.endsWith(".xlsx")) {
            return new XSSFWorkbook(new FileInputStream(ruta));
        }else {
            throw new IllegalArgumentException("El archivo no es valido");
        }
    }

    @FXML private void Cargar() throws IOException, SQLException {
        Workbook workbook = DetectarArchivo(ruta);
        int cont=0;
            if (cbMat.isSelected()){
                System.out.println("Entra a checkbox");
                if (VerificarTxts(txtNHojaMat,txtNColMat)){
                    int colInicial=-1;
                    Sheet sheet = workbook.getSheetAt(Integer.parseInt(txtNHojaMat.getText())-1);
                    for (Row row : sheet){
                        if (row.getRowNum()+1 == Integer.parseInt(txtNColMat.getText())){
                            System.out.println("Entra auqi");
                            for (Cell cell : row){
                                System.out.println(cell.getColumnIndex());
                                if (!cell.getStringCellValue().isEmpty()){
                                    colInicial = cell.getColumnIndex();
                                    break;
                                }
                            }

                        }else if (colInicial !=-1){
                            cont++;
                            System.out.println(cont);
                            Cell cb = row.getCell(colInicial);
                            Cell tipodeArmario = row.getCell(colInicial+1);
                            Cell gaveta = row.getCell(colInicial+2);
                            Cell sub = row.getCell(colInicial+3);
                            Cell material = row.getCell(colInicial+4);
                            Cell tipo = row.getCell(colInicial+5);
                            Cell nparte = row.getCell(colInicial+6);
                            Cell valor = row.getCell(colInicial+7);
                            Cell u_medida = row.getCell(colInicial+8);
                            Cell caracteristicas = row.getCell(colInicial+9);
                            Cell f_uso = row.getCell(colInicial+10);
                            Cell cantidad = row.getCell(colInicial+11);
                            Cell cantidad_min = row.getCell(colInicial+12);
                            Cell tmat = row.getCell(colInicial+13);
                            if (cb.getCellType() == CellType.NUMERIC && !String.valueOf(cb.getNumericCellValue()).isEmpty()) {
                                Articulo articulo = new Articulo((cb.getCellType() == CellType.NUMERIC) ? (long) cb.getNumericCellValue() : Long.valueOf(cb.getStringCellValue())
                                        , (tipodeArmario.getCellType() == CellType.NUMERIC) ? String.valueOf(tipodeArmario.getNumericCellValue()) : tipodeArmario.getStringCellValue()
                                        , (gaveta.getCellType() == CellType.NUMERIC) ? String.valueOf(gaveta.getNumericCellValue()) : gaveta.getStringCellValue()
                                        , (sub.getCellType() == CellType.NUMERIC) ? String.valueOf(sub.getNumericCellValue()) : sub.getStringCellValue()
                                        , (material.getCellType() == CellType.NUMERIC) ? String.valueOf(material.getNumericCellValue()) : material.getStringCellValue()
                                        , (tipo.getCellType() == CellType.NUMERIC) ? String.valueOf(tipo.getNumericCellValue()) : tipo.getStringCellValue()
                                        , (nparte.getCellType() == CellType.NUMERIC) ? String.valueOf(nparte.getNumericCellValue()) : nparte.getStringCellValue()
                                        , (valor.getCellType() == CellType.NUMERIC) ? String.valueOf(valor.getNumericCellValue()) : valor.getStringCellValue()
                                        , (u_medida.getCellType() == CellType.NUMERIC) ? String.valueOf(u_medida.getNumericCellValue()) : u_medida.getStringCellValue()
                                        , (caracteristicas.getCellType() == CellType.NUMERIC) ? String.valueOf(caracteristicas.getNumericCellValue()) : caracteristicas.getStringCellValue()
                                        , (f_uso.getCellType() == CellType.NUMERIC) ? String.valueOf(f_uso.getNumericCellValue()) : f_uso.getStringCellValue()
                                        , (cantidad.getCellType() == CellType.NUMERIC) ? (int) cantidad.getNumericCellValue() : Integer.parseInt(cantidad.getStringCellValue())
                                        , (cantidad_min.getCellType() == CellType.NUMERIC) ? (int) cantidad_min.getNumericCellValue() : Integer.parseInt(cantidad_min.getStringCellValue())
                                );
                                System.out.println(articulo.getValor());
                                ResultSet resultSet = conexion.consultar("SELECT * FROM `tipo_material` WHERE `material`= ? LIMIT 1", articulo.getMaterial());
                                if (resultSet.next()) {
                                    conexion.insmodelim("INSERT INTO `material`(`cb_material`, `tipo_de_armario`, `gaveta`, `sub_compartimento`, `id_material`, `tipo`, `numero_parte`, `valor`, `unidad_de_medida`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", String.valueOf(articulo.getCodigo_barras()), articulo.getTipo_de_armario(), articulo.getGaveta(), articulo.getSub_compartimento(), String.valueOf(resultSet.getInt("id_material")), articulo.getTipo(), articulo.getNumero_parte(), articulo.getValor(), articulo.getUnidad_medida(), articulo.getCaracteristicas(), articulo.getF_uso(), String.valueOf(articulo.getCantidad()), String.valueOf(articulo.getCantidad_min()));

                                } else {
                                    conexion.insmodelim("INSERT INTO `tipo_material`(`material`, `tipo_material`) VALUES (?,'Material Consumible')", articulo.getMaterial());
                                    ResultSet resultSet1 = conexion.consultar("SELECT `id_material` FROM `tipo_material` ORDER BY `id_material` DESC LIMIT 1;");
                                    if (resultSet1.next()) {
                                        conexion.insmodelim("INSERT INTO `material`(`cb_material`, `tipo_de_armario`, `gaveta`, `sub_compartimento`, `id_material`, `tipo`, `numero_parte`, `valor`, `unidad_de_medida`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", String.valueOf(articulo.getCodigo_barras()), articulo.getTipo_de_armario(), articulo.getGaveta(), articulo.getSub_compartimento(), String.valueOf(resultSet1.getInt("id_material")), articulo.getTipo(), articulo.getNumero_parte(), articulo.getValor(), articulo.getUnidad_medida(), articulo.getCaracteristicas(), articulo.getF_uso(), String.valueOf(articulo.getCantidad()), String.valueOf(articulo.getCantidad_min()));

                                    }
                                }

                            }
                    }
                }
                }
            }
            if (cbHerramienta.isSelected()){
                System.out.println("Entra a checkbox");
                if (VerificarTxts(txtNHojaHerra,txtNColHerra)){
                    int colInicial=-1;
                    Sheet sheet = workbook.getSheetAt(Integer.parseInt(txtNHojaHerra.getText())-1);
                    for (Row row : sheet){
                        if (row.getRowNum()+1 == Integer.parseInt(txtNColHerra.getText())){
                            System.out.println("Entra auqi");
                            for (Cell cell : row){
                                System.out.println(cell.getColumnIndex());
                                if (!cell.getStringCellValue().isEmpty()){
                                    colInicial = cell.getColumnIndex();
                                    break;
                                }
                            }

                        }else if (colInicial !=-1){
                            cont++;
                            System.out.println(cont);
                            Cell cb = row.getCell(colInicial);
                            Cell therramienta = row.getCell(colInicial+1);
                            Cell tipo = row.getCell(colInicial+2);
                            Cell caracteristicas = row.getCell(colInicial+3);
                            Cell f_uso = row.getCell(colInicial+4);
                            Cell cantidad = row.getCell(colInicial+5);
                            Cell cantidad_min = row.getCell(colInicial+6);
                            if (cb.getCellType() == CellType.NUMERIC && !String.valueOf(cb.getNumericCellValue()).isEmpty()) {
                                Herramienta herramienta = new Herramienta((cb.getCellType() == CellType.NUMERIC) ? (long) cb.getNumericCellValue() : Long.valueOf(cb.getStringCellValue())
                                        ,(therramienta.getCellType() == CellType.NUMERIC) ? String.valueOf(therramienta.getNumericCellValue()) : therramienta.getStringCellValue()
                                        , (tipo.getCellType() == CellType.NUMERIC) ? String.valueOf(tipo.getNumericCellValue()) : tipo.getStringCellValue()
                                        , (caracteristicas.getCellType() == CellType.NUMERIC) ? String.valueOf(caracteristicas.getNumericCellValue()) : caracteristicas.getStringCellValue()
                                        , (f_uso.getCellType() == CellType.NUMERIC) ? String.valueOf(f_uso.getNumericCellValue()) : f_uso.getStringCellValue()
                                        , (cantidad.getCellType() == CellType.NUMERIC) ? (int) cantidad.getNumericCellValue() : Integer.parseInt(cantidad.getStringCellValue())
                                        , (cantidad_min.getCellType() == CellType.NUMERIC) ? (int) cantidad_min.getNumericCellValue() : Integer.parseInt(cantidad_min.getStringCellValue())
                                        );

                                ResultSet resultSet3= conexion.consultar("SELECT * FROM `tipo_material` WHERE `material`= ? LIMIT 1",herramienta.getHerramienta());
                                if (resultSet3.next()){
                                    conexion.insmodelim("INSERT INTO `herramienta`(`cb_herramienta`, `id_herramienta`, `tipo`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES (?, ?, ?, ?, ?, ?, '0')",String.valueOf(herramienta.getCb_herramienta()), String.valueOf(resultSet3.getInt("id_material")), herramienta.getTipo(), herramienta.getCaracteristicas(), herramienta.getFrecuencia_de_uso(), String.valueOf(herramienta.getCantidad()));

                                }else {
                                    conexion.insmodelim("INSERT INTO `tipo_material`(`material`, `tipo_material`) VALUES ( ?,'Herramienta')", herramienta.getHerramienta());
                                    ResultSet resultSet4 = conexion.consultar("SELECT `id_material` FROM `tipo_material` ORDER BY `id_material` DESC LIMIT 1;");
                                    if(resultSet4.next()){
                                        conexion.insmodelim("INSERT INTO `herramienta`(`cb_herramienta`, `id_herramienta`, `tipo`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES (?, ?, ?, ?, ?, ?,'0')", String.valueOf(herramienta.getCb_herramienta()), String.valueOf(resultSet4.getInt("id_material")), herramienta.getTipo(), herramienta.getCaracteristicas(), herramienta.getFrecuencia_de_uso(), String.valueOf(herramienta.getCantidad()));

                                    }
                                }

                            }
                        }
                    }
                }
            }

            Sheet sheet = workbook.getSheetAt(0);

            int columnIndexToRead = 2;
            for (Row row : sheet) {




                /*
                Long cb = GenerateNumber();
                Cell tipodeArmario = row.getCell(columnIndexToRead);
                Cell gaveta = row.getCell(columnIndexToRead+1);
                Cell sub = row.getCell(columnIndexToRead+2);
                Cell material = row.getCell(columnIndexToRead+3);
                Cell tipo = row.getCell(columnIndexToRead+4);
                Cell nparte = row.getCell(columnIndexToRead+5);
                Cell valor = row.getCell(columnIndexToRead+6);
                Cell u_medida = row.getCell(columnIndexToRead+7);
                Cell caracteristicas = row.getCell(columnIndexToRead+8);
                Cell f_uso = row.getCell(columnIndexToRead+9);
                Cell cantidad = row.getCell(columnIndexToRead+10);

                if (!tipodeArmario.getStringCellValue().equals("HERRAMIENTA") && !gaveta.getStringCellValue().equals("HERRAMIENTA") && !sub.getStringCellValue().equals("HERRAMIENTA") && tipodeArmario != null && !tipodeArmario.getStringCellValue().equals("TIPO DE ARMARIO") && gaveta != null && sub != null){
                    Articulo articulo= new Articulo(10L,"fdsf","fdfd","gfdgdf","gfdg","gfdgdf","gfdgdfg","fdfds","gfdgf","fdfdsfds","fdsfdsf",12,21);
                    if (valor.getCellType() == valor.CELL_TYPE_STRING && nparte.getCellType() == nparte.CELL_TYPE_STRING) {
                        articulo = new Articulo((long) cont, tipodeArmario.getStringCellValue(), gaveta.getStringCellValue(),
                                sub.getStringCellValue(), material.getStringCellValue(), tipo.getStringCellValue(), nparte.getStringCellValue(),
                                valor.getStringCellValue(), u_medida.getStringCellValue(), caracteristicas.getStringCellValue(), f_uso.getStringCellValue(),
                                (int) cantidad.getNumericCellValue(), 0);

                    } else if (valor.getCellType() == valor.CELL_TYPE_NUMERIC && nparte.getCellType() == nparte.CELL_TYPE_NUMERIC) {
                        articulo = new Articulo((long) cont, tipodeArmario.getStringCellValue(), gaveta.getStringCellValue(),
                                sub.getStringCellValue(), material.getStringCellValue(), tipo.getStringCellValue(), String.valueOf(nparte.getNumericCellValue()),
                                String.valueOf(valor.getNumericCellValue()), u_medida.getStringCellValue(), caracteristicas.getStringCellValue(), f_uso.getStringCellValue(),
                                (int) cantidad.getNumericCellValue(), 0);
                    }else if (valor.getCellType() == valor.CELL_TYPE_STRING && nparte.getCellType() == nparte.CELL_TYPE_NUMERIC) {
                        articulo = new Articulo((long) cont, tipodeArmario.getStringCellValue(), gaveta.getStringCellValue(),
                                sub.getStringCellValue(), material.getStringCellValue(), tipo.getStringCellValue(), String.valueOf(nparte.getNumericCellValue()),
                                valor.getStringCellValue(), u_medida.getStringCellValue(), caracteristicas.getStringCellValue(), f_uso.getStringCellValue(),
                                (int) cantidad.getNumericCellValue(), 0);
                    }else if (valor.getCellType() == valor.CELL_TYPE_NUMERIC && nparte.getCellType() == nparte.CELL_TYPE_STRING) {
                        articulo = new Articulo((long) cont, tipodeArmario.getStringCellValue(), gaveta.getStringCellValue(),
                                sub.getStringCellValue(), material.getStringCellValue(), tipo.getStringCellValue(), String.valueOf(nparte.getStringCellValue()),
                                String.valueOf(valor.getNumericCellValue()), u_medida.getStringCellValue(), caracteristicas.getStringCellValue(), f_uso.getStringCellValue(),
                                (int) cantidad.getNumericCellValue(), 0);
                    }

                    System.out.println(articulo.getCodigo_barras());
                    System.out.println(articulo.getTipo_de_armario());
                    System.out.println(articulo.getGaveta());
                    System.out.println(articulo.getSub_compartimento());
                    System.out.println(articulo.getMaterial());
                    System.out.println(articulo.getTipo());
                    System.out.println(articulo.getNumero_parte());
                    System.out.println(articulo.getValor());
                    System.out.println(articulo.getUnidad_medida());
                    System.out.println(articulo.getCaracteristicas());
                    System.out.println(articulo.getF_uso());
                    System.out.println(articulo.getCantidad());

                    ResultSet resultSet= conexion.consultar("SELECT * FROM `tipo_material` WHERE `material`= ? LIMIT 1", articulo.getMaterial());
                    if (resultSet.next()){
                        conexion.insmodelim("INSERT INTO `material`(`cb_material`, `tipo_de_armario`, `gaveta`, `sub_compartimento`, `id_material`, `tipo`, `numero_parte`, `valor`, `unidad_de_medida`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",String.valueOf(articulo.getCodigo_barras()), articulo.getTipo_de_armario(), articulo.getGaveta(), articulo.getSub_compartimento(), String.valueOf(resultSet.getInt("id_material")), articulo.getTipo(), articulo.getNumero_parte(), articulo.getValor(), articulo.getUnidad_medida(), articulo.getCaracteristicas(), articulo.getF_uso(), String.valueOf(articulo.getCantidad()), String.valueOf(articulo.getCantidad_min()));

                    }else {
                        conexion.insmodelim("INSERT INTO `tipo_material`(`material`, `tipo_material`) VALUES (?,'Material Consumible')", articulo.getMaterial());
                        ResultSet resultSet1 = conexion.consultar("SELECT `id_material` FROM `tipo_material` ORDER BY `id_material` DESC LIMIT 1;");
                        if(resultSet1.next()){
                            conexion.insmodelim("INSERT INTO `material`(`cb_material`, `tipo_de_armario`, `gaveta`, `sub_compartimento`, `id_material`, `tipo`, `numero_parte`, `valor`, `unidad_de_medida`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",String.valueOf(articulo.getCodigo_barras()), articulo.getTipo_de_armario(), articulo.getGaveta(), articulo.getSub_compartimento(), String.valueOf(resultSet1.getInt("id_material")), articulo.getTipo(), articulo.getNumero_parte(), articulo.getValor(), articulo.getUnidad_medida(), articulo.getCaracteristicas(), articulo.getF_uso(), String.valueOf(articulo.getCantidad()), String.valueOf(articulo.getCantidad_min()));

                        }
                    }

                } if (tipodeArmario.getStringCellValue().equals("HERRAMIENTA") && gaveta.getStringCellValue().equals("HERRAMIENTA") && sub.getStringCellValue().equals("HERRAMIENTA")) {
                    Herramienta herramienta = new Herramienta((long) cont,material.getStringCellValue(),tipo.getStringCellValue(),caracteristicas.getStringCellValue(),f_uso.getStringCellValue(),(int) cantidad.getNumericCellValue(),0);

                    ResultSet resultSet3= conexion.consultar("SELECT * FROM `tipo_material` WHERE `material`= ? LIMIT 1",herramienta.getHerramienta());
                    if (resultSet3.next()){
                        conexion.insmodelim("INSERT INTO `herramienta`(`cb_herramienta`, `id_herramienta`, `tipo`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES (?, ?, ?, ?, ?, ?, '0')",String.valueOf(herramienta.getCb_herramienta()), String.valueOf(resultSet3.getInt("id_material")), herramienta.getTipo(), herramienta.getCaracteristicas(), herramienta.getFrecuencia_de_uso(), String.valueOf(herramienta.getCantidad()));

                    }else {
                        conexion.insmodelim("INSERT INTO `tipo_material`(`material`, `tipo_material`) VALUES ( ?,'Herramienta')", herramienta.getHerramienta());
                        ResultSet resultSet4 = conexion.consultar("SELECT `id_material` FROM `tipo_material` ORDER BY `id_material` DESC LIMIT 1;");
                        if(resultSet4.next()){
                            conexion.insmodelim("INSERT INTO `herramienta`(`cb_herramienta`, `id_herramienta`, `tipo`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES (?, ?, ?, ?, ?, ?,'0')", String.valueOf(herramienta.getCb_herramienta()), String.valueOf(resultSet4.getInt("id_material")), herramienta.getTipo(), herramienta.getCaracteristicas(), herramienta.getFrecuencia_de_uso(), String.valueOf(herramienta.getCantidad()));

                        }
                    }
                }


                cont++;*/
            }


    }
    private boolean VerificarTxts(TextField... txts){
        for (TextField txt : txts){
            if (txt.getText().isEmpty() || !txt.getText().matches("^[0-9]*$")){
                return false;
            }
        }
        return true;
    }

    @FXML private void CheckBoxChange(){
        hboxMat.setDisable(!cbMat.isSelected());
        hboxHerra.setDisable(!cbHerramienta.isSelected());
    }
}


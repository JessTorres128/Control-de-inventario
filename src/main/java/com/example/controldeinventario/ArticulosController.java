package com.example.controldeinventario;
import com.example.controldeinventario.Datos.Herramienta;
import com.itextpdf.text.*;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import javafx.scene.input.RotateEvent;
import javafx.scene.transform.Rotate;
import org.apache.poi.ss.usermodel.*;
import com.example.controldeinventario.Datos.Articulo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.krysalis.barcode4j.impl.code39.Code39;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArticulosController {
    @FXML ImageView imgCodeBar = new ImageView();
    @FXML RadioButton rbCBPequeño, rbCBMedio, rbCBGrande;
    @FXML Label lblContador;
    @FXML TextField txtBusqueda;
    @FXML ComboBox<String> cbMaterial = new ComboBox<>();
    @FXML TextArea txtCaracteristicas;
    @FXML TextField txtCodigoBarras, txtArmario,txtGaveta,txtSubCompartimento,txtTipo,txtNumParte,txtValor,txtUnidadMedida,txtStock,txtStockMin;
    Conexion conexion;
    boolean edit= false;
    long cbedit = 0L;
    KeyCombination keyCombination= new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    @FXML
    Button btnNew, btnSave, btnEdit, btnCancel, btnExit, btnDelete;


    @FXML TabPane tabV;
    @FXML Tab tabSearch, tabNew;
    @FXML RadioButton rbCodigoBarras, rbArmario, rbMaterial, rbTipo;
    @FXML RadioButton rbBajo, rbMedio,rbAlto, rbSinUtilizar;
    @FXML CheckBox checkBoxNA1,checkBoxNA2,checkBoxNA3,checkBoxNA4;
    ToggleGroup toggleGroupBusqueda = new ToggleGroup();
    ToggleGroup toogleGroupCBSize = new ToggleGroup();
    ToggleGroup toggleGroupFrecuencia = new ToggleGroup();
    @FXML TableView<Articulo> tableViewArticulos;
    TableColumn <Articulo,Long> colCB=new TableColumn<>("Codigo de barras");
    TableColumn <Articulo, String> colTArmario=new TableColumn<>("Tipo de armario");
    TableColumn <Articulo, String> colGaveta=new TableColumn<>("Gaveta");
    TableColumn <Articulo, String> colSubCom=new TableColumn<>("Sub-compartimento");
    TableColumn <Articulo,String> colMaterial=new TableColumn<>("Material");
    TableColumn <Articulo,String> colTipo=new TableColumn<>("Tipo");
    TableColumn <Articulo, String> colNumParte=new TableColumn<>("Número de parte");
    TableColumn <Articulo, String> colValor=new TableColumn<>("Valor");
    TableColumn <Articulo,String> colUMedida=new TableColumn<>("Unidad de medida");
    TableColumn <Articulo, String> colCaracteristicas=new TableColumn<>("Caracteristicas");
    TableColumn <Articulo, String> colFUso=new TableColumn<>("Frecuencia de uso");
    TableColumn <Articulo, Integer> colCantidad=new TableColumn<>("Cantidad");
    TableColumn <Articulo,Integer> colCantidadMin=new TableColumn<>("Cantidad minima");




    @FXML protected void initialize() throws SQLException {






        Platform.runLater(() -> {
            txtBusqueda.requestFocus();
            txtBusqueda.selectEnd();
        });

        colCB.setCellValueFactory(new PropertyValueFactory<>("codigo_barras"));
        colTArmario.setCellValueFactory(new PropertyValueFactory<>("tipo_de_armario"));
        colGaveta.setCellValueFactory(new PropertyValueFactory<>("gaveta"));
        colSubCom.setCellValueFactory(new PropertyValueFactory<>("sub_compartimento"));
        colMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colNumParte.setCellValueFactory(new PropertyValueFactory<>("numero_parte"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colUMedida.setCellValueFactory(new PropertyValueFactory<>("unidad_medida"));
        colCaracteristicas.setCellValueFactory(new PropertyValueFactory<>("caracteristicas"));
        colFUso.setCellValueFactory(new PropertyValueFactory<>("f_uso"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colCantidadMin.setCellValueFactory(new PropertyValueFactory<>("cantidad_min"));

        tableViewArticulos.getColumns().addAll(colCB,colTArmario,colGaveta,colSubCom,colMaterial,colTipo,colNumParte,colValor,colUMedida,colCaracteristicas,colFUso,colCantidad,colCantidadMin);
        rbCodigoBarras.setToggleGroup(toggleGroupBusqueda);
        rbArmario.setToggleGroup(toggleGroupBusqueda);
        rbMaterial.setToggleGroup(toggleGroupBusqueda);
        rbTipo.setToggleGroup(toggleGroupBusqueda);
        rbCBPequeño.setToggleGroup(toogleGroupCBSize);
        rbCBMedio.setToggleGroup(toogleGroupCBSize);
        rbCBGrande.setToggleGroup(toogleGroupCBSize);

        rbBajo.setToggleGroup(toggleGroupFrecuencia);
        rbMedio.setToggleGroup(toggleGroupFrecuencia);
        rbAlto.setToggleGroup(toggleGroupFrecuencia);
        rbSinUtilizar.setToggleGroup(toggleGroupFrecuencia);


        conexion = new Conexion();
        cbMaterial.getItems().clear();
        ResultSet resultSet = conexion.consultar("SELECT * FROM `tipo_material` WHERE `tipo_material` LIKE '%Material%'");
        while (resultSet.next()){
            cbMaterial.getItems().add((String) resultSet.getObject("material"));
        }
        cbMaterial.getSelectionModel().select(0);
        ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material;"));
        ActivateBtn(false,true,false,true,false,false);




 /*
=======
        btnSave.setOnKeyPressed(event -> {
            if (keyCombination.match(event)){
                try {
                    SaveArticulo();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
  /*btnNew.setGraphic(new ImageView(new Image("/img/new.png",25,25,false,true)));
>>>>>>> 916adfcce1eb0531d206cfe52f254d8d5de85be6
    btnSave.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnEdit.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnCancel.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnExit.setGraphic(new ImageView(new Image("",25,25,false,true)));
*/
    }



    private void ActualizarTabla(ResultSet rsArticulos) throws SQLException {
        int cont=0;
        tableViewArticulos.getItems().clear();
        while (rsArticulos.next()){
            cont++;
            Articulo a=new Articulo(rsArticulos.getLong("cb_material"), rsArticulos.getString("tipo_de_armario"), rsArticulos.getString("gaveta"), rsArticulos.getString("sub_compartimento"), rsArticulos.getString("material"),
                    rsArticulos.getString("tipo"), rsArticulos.getString("numero_parte"), rsArticulos.getString("valor"), rsArticulos.getString("unidad_de_medida"), rsArticulos.getString("caracteristicas"), rsArticulos.getString("frecuencia_de_uso"),
                    rsArticulos.getInt("cantidad"), rsArticulos.getInt("cantidad_min"));
            tableViewArticulos.getItems().add(a);
        }
        lblContador.setText("Se cargaron "+cont+" articulos");
    }
    @FXML private void NewArticulo() throws SQLException {
        ActivateBtn(false,false,true,false,false,true);
        Long cb = GenerateNumber();
        tabV.getSelectionModel().select(tabNew);
        tabNew.setDisable(false);
        tabSearch.setDisable(true);
       // txtCodigoBarras.setText(String.valueOf(cb));
        CleanTextFields();
        CheckBoxChange1();
        CheckBoxChange2();
        CheckBoxChange3();
        CheckBoxChange4();


    }
    @FXML private void SaveArticulo() throws SQLException {
        if (!txtCodigoBarras.getText().matches("\\d{10}")){
            Error("Formato de código de barras no valido, asegurese de que sea un número de 10 digitos");
        }else {
            if (VerifyTxt(txtCaracteristicas, cbMaterial,txtArmario,txtCodigoBarras,txtGaveta,txtSubCompartimento,txtStock,txtStockMin,txtNumParte,txtTipo,txtValor,txtUnidadMedida)){
                if (!txtStock.getText().matches("^\\d+$") || !txtStockMin.getText().matches("^\\d+$") || !txtValor.getText().matches("^(N\\/A|-?\\d*\\.?\\d+)$")){

                    Error("Cantidades incorrectas, revise que contenga solamente números");
                }else {
                    if (txtCodigoBarras.getText().equals(String.valueOf(cbedit))){
                       /* ResultSet res= conexion.consultar("SELECT herramienta.cb_herramienta FROM herramienta\n" +
                                "                LEFT JOIN material\n" +
                                "                ON herramienta.cb_herramienta = material.cb_material\n" +
                                "                WHERE herramienta.cb_herramienta = ? OR material.cb_material = ?\n" +
                                "                UNION\n" +
                                "                SELECT material.cb_material\n" +
                                "                FROM material\n" +
                                "                LEFT JOIN herramienta\n" +
                                "                ON herramienta.cb_herramienta = material.cb_material\n" +
                                "                WHERE herramienta.cb_herramienta = ? OR material.cb_material = ?\n" +
                                "                AND herramienta.cb_herramienta IS NULL;",txtCodigoBarras.getText(),txtCodigoBarras.getText(),txtCodigoBarras.getText(),txtCodigoBarras.getText());
                        if (res.next()){
                            Error("Ya existe un articulo con este código de barras");
                        }else {*/
                            ResultSet resultado = conexion.consultar("SELECT `id_material` FROM `tipo_material` WHERE `material`='"+cbMaterial.getSelectionModel().getSelectedItem()+"' AND `tipo_material` LIKE '%Material%' LIMIT 1");
                            if (!resultado.next()){
                                Error("Selecciona el material");
                            }else {
                                if (edit){
                                    conexion.insmodelim("UPDATE `material` SET `cb_material`= ?, `tipo_de_armario`= ?, `gaveta`= ?, `sub_compartimento`= ?, `id_material`= ?, `tipo`= ?, `numero_parte`= ?, `valor`= ?, `unidad_de_medida`= ?, `caracteristicas`= ?, `frecuencia_de_uso`= ?, `cantidad`= ?, `cantidad_min`= ? WHERE `cb_material`= ? ",txtCodigoBarras.getText(), txtArmario.getText(), txtGaveta.getText(), txtSubCompartimento.getText(),String.valueOf(resultado.getInt("id_material")), txtTipo.getText(), txtNumParte.getText(),(txtValor.getText().equals("N/A") ? "N/A" : txtValor.getText()),txtUnidadMedida.getText(),txtCaracteristicas.getText(),((RadioButton) toggleGroupFrecuencia.getSelectedToggle()).getText(),txtStock.getText(),txtStockMin.getText(),cbedit);
                                    //  conexion.insmodelim("UPDATE `material` SET `cb_material`='"++"', `tipo_de_armario`='" +  + "', `gaveta`='" +  + "', `sub_compartimento`='" +  + "', `id_material`='"++"', `tipo`='" +  + "', `numero_parte`='" +  + "', `valor`='" +  + "', `unidad_de_medida`='" +  + "', `caracteristicas`='" +  + "', `frecuencia_de_uso`='" +  + "', `cantidad`='" +  + "', `cantidad_min`='" +  + "' WHERE `cb_material`='"++"'");
                                    Exito("Actualizado con exito");
                                }else {
                                    conexion.insmodelim("INSERT INTO `material`(`cb_material`, `tipo_de_armario`, `gaveta`, `sub_compartimento`, `id_material`, `tipo`, `numero_parte`, `valor`, `unidad_de_medida`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",txtCodigoBarras.getText(),txtArmario.getText(), txtGaveta.getText(), txtSubCompartimento.getText(), resultado.getString("id_material"), txtTipo.getText(), txtNumParte.getText(), txtValor.getText(), txtUnidadMedida.getText(), txtCaracteristicas.getText(), ((RadioButton) toggleGroupFrecuencia.getSelectedToggle()).getText(), txtStock.getText(), txtStockMin.getText());
                                    Exito("Lo logro señor");
                                }

                            //}
                        }
                    }else {
                        ResultSet res= conexion.consultar("SELECT herramienta.cb_herramienta FROM herramienta\n" +
                                "                LEFT JOIN material\n" +
                                "                ON herramienta.cb_herramienta = material.cb_material\n" +
                                "                WHERE herramienta.cb_herramienta = ? OR material.cb_material = ?\n" +
                                "                UNION\n" +
                                "                SELECT material.cb_material\n" +
                                "                FROM material\n" +
                                "                LEFT JOIN herramienta\n" +
                                "                ON herramienta.cb_herramienta = material.cb_material\n" +
                                "                WHERE herramienta.cb_herramienta = ? OR material.cb_material = ?\n" +
                                "                AND herramienta.cb_herramienta IS NULL;",txtCodigoBarras.getText(),txtCodigoBarras.getText(),txtCodigoBarras.getText(),txtCodigoBarras.getText());
                        if (res.next()){
                            Error("Ya existe un articulo con este código de barras");
                        }else {
                            ResultSet resultado = conexion.consultar("SELECT `id_material` FROM `tipo_material` WHERE `material`='"+cbMaterial.getSelectionModel().getSelectedItem()+"' AND `tipo_material` LIKE '%Material%' LIMIT 1");
                            if (!resultado.next()){
                                Error("Selecciona el material");
                            }else {
                                if (edit){
                                    conexion.insmodelim("UPDATE `material` SET `cb_material`= ?, `tipo_de_armario`= ?, `gaveta`= ?, `sub_compartimento`= ?, `id_material`= ?, `tipo`= ?, `numero_parte`= ?, `valor`= ?, `unidad_de_medida`= ?, `caracteristicas`= ?, `frecuencia_de_uso`= ?, `cantidad`= ?, `cantidad_min`= ? WHERE `cb_material`= ? ",txtCodigoBarras.getText(), txtArmario.getText(), txtGaveta.getText(), txtSubCompartimento.getText(),String.valueOf(resultado.getInt("id_material")), txtTipo.getText(), txtNumParte.getText(),(txtValor.getText().equals("N/A") ? "N/A" : txtValor.getText()),txtUnidadMedida.getText(),txtCaracteristicas.getText(),((RadioButton) toggleGroupFrecuencia.getSelectedToggle()).getText(),txtStock.getText(),txtStockMin.getText(),cbedit);
                                    //  conexion.insmodelim("UPDATE `material` SET `cb_material`='"++"', `tipo_de_armario`='" +  + "', `gaveta`='" +  + "', `sub_compartimento`='" +  + "', `id_material`='"++"', `tipo`='" +  + "', `numero_parte`='" +  + "', `valor`='" +  + "', `unidad_de_medida`='" +  + "', `caracteristicas`='" +  + "', `frecuencia_de_uso`='" +  + "', `cantidad`='" +  + "', `cantidad_min`='" +  + "' WHERE `cb_material`='"++"'");
                                    Exito("Actualizado con exito");
                                }else {
                                    conexion.insmodelim("INSERT INTO `material`(`cb_material`, `tipo_de_armario`, `gaveta`, `sub_compartimento`, `id_material`, `tipo`, `numero_parte`, `valor`, `unidad_de_medida`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",txtCodigoBarras.getText(),txtArmario.getText(), txtGaveta.getText(), txtSubCompartimento.getText(), resultado.getString("id_material"), txtTipo.getText(), txtNumParte.getText(), txtValor.getText(), txtUnidadMedida.getText(), txtCaracteristicas.getText(), ((RadioButton) toggleGroupFrecuencia.getSelectedToggle()).getText(), txtStock.getText(), txtStockMin.getText());
                                    Exito("Lo logro señor");
                                }

                            }
                        }
                    }// otra cosa







                }
                tabV.getSelectionModel().select(tabSearch);
                tabSearch.setDisable(false);
                tabNew.setDisable(true);
                ActivateBtn(false,true,false,true,false,false);
                ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material;"));
                edit=false;
                cbedit = 0L;

            }else {Error("Faltan campos por rellenar");}
        }




    }
    @FXML private void EditArticulo() throws SQLException {
        if (tableViewArticulos.getSelectionModel().getSelectedItem() != null){
            Articulo articulo = tableViewArticulos.getSelectionModel().getSelectedItem();
            tabV.getSelectionModel().select(tabNew);
            tabSearch.setDisable(true);
            tabNew.setDisable(false);
            txtCodigoBarras.setText(String.valueOf(articulo.getCodigo_barras()));
            txtArmario.setText(articulo.getTipo_de_armario());
            txtGaveta.setText(articulo.getGaveta());
            txtSubCompartimento.setText(articulo.getSub_compartimento());
            cbMaterial.getSelectionModel().select(articulo.getMaterial());
            txtTipo.setText(articulo.getTipo());
            txtNumParte.setText(articulo.getNumero_parte());
            txtValor.setText(String.valueOf(articulo.getValor()));
            txtUnidadMedida.setText(articulo.getUnidad_medida());
            txtCaracteristicas.setText(articulo.getCaracteristicas());
            if (txtArmario.getText().equals("N/A")){
                checkBoxNA1.setSelected(true);
                txtArmario.setDisable(true);
            }
            if (txtGaveta.getText().equals("N/A")){
                checkBoxNA2.setSelected(true);
                txtGaveta.setDisable(true);
            }
            if (txtSubCompartimento.getText().equals("N/A")){
                checkBoxNA3.setSelected(true);
                txtSubCompartimento.setDisable(true);
            }
            if (txtValor.getText().equals("N/A") && txtUnidadMedida.getText().equals("N/A")){
                checkBoxNA4.setSelected(true);
                txtValor.setDisable(true);
                txtUnidadMedida.setDisable(true);
            }
            switch (articulo.getF_uso()) {
                case "Bajo" -> toggleGroupFrecuencia.selectToggle(rbBajo);
                case "Medio" -> toggleGroupFrecuencia.selectToggle(rbMedio);
                case "Alto" -> toggleGroupFrecuencia.selectToggle(rbAlto);
                case "Sin utilizar" -> toggleGroupFrecuencia.selectToggle(rbSinUtilizar);
            }
            txtStock.setText(String.valueOf(articulo.getCantidad()));
            txtStockMin.setText(String.valueOf(articulo.getCantidad_min()));
            ActivateBtn(true,false,true,false,false,true);
            edit = true;
            cbedit = articulo.getCodigo_barras();
        }else {Error("Selecciona un registro pa");}

    }
    @FXML private void CancelArticulo() throws SQLException {
        txtCodigoBarras.setText("");
        CleanTextFields();
        ActivateBtn(false,true,false,true,false,false);
        tabV.getSelectionModel().select(tabSearch);
        tabSearch.setDisable(false);
        tabNew.setDisable(true);
    }
    @FXML private void DeleteArticulo() throws SQLException {
        if (tableViewArticulos.getSelectionModel().getSelectedItem() != null){
            Articulo articulo = tableViewArticulos.getSelectionModel().getSelectedItem();
            if (ConfirmarBorrar("Deseas borrar "+articulo.getMaterial()+" "+articulo.getTipo())){
                conexion.insmodelim("DELETE FROM `material` WHERE `cb_material`= ?",String.valueOf(articulo.getCodigo_barras()));
                Exito("Registro borrado exitosamente");
                ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material;"));
            }

        }else {
            Error("Selecciona un registro pa");
        }
    }
    @FXML private void ExitArticulo(){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
    @FXML private void GenerateCodeBar() throws IOException, SQLException, DocumentException {
        Code39Bean code39Bean = new Code39Bean();
        final int dpi = 150;
        code39Bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));
        code39Bean.setWideFactor(3);
        code39Bean.doQuietZone(true);
        ResultSet rsMaterial= conexion.consultar("SELECT `material` FROM `tipo_material` WHERE 1;");
        while (rsMaterial.next()){
            ResultSet resultSet = conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material WHERE tipo_material.material= ?;", rsMaterial.getString("material"));
            Document documento = new Document(PageSize.A4.rotate(), 10f, 10f, 0f, 0f);
            if (rsMaterial.getString("material").equals("N/A")){
                PdfWriter pdfWriter=PdfWriter.getInstance(documento, new FileOutputStream("Libro_NA_material.pdf"));

            }else {
                PdfWriter pdfWriter=PdfWriter.getInstance(documento, new FileOutputStream("Libro_"+rsMaterial.getString("material")+".pdf"));

            }

            // pdfWriter.addPageDictEntry(PdfName.ROTATE, PdfPage.LANDSCAPE);
            documento.open();
            PdfPTable pdfPTableCB = new PdfPTable(7);

            pdfPTableCB.setWidthPercentage(100);
            PdfPCell cellCB = new PdfPCell(Phrase.getInstance("Código"));
            cellCB.setPadding(5);
            cellCB.setBorder(Rectangle.ALIGN_CENTER);
            cellCB.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTableCB.addCell(cellCB);
            PdfPCell cellMat = new PdfPCell(Phrase.getInstance("Material"));
            cellMat.setPadding(5);
            cellMat.setBorder(com.itextpdf.text.Rectangle.ALIGN_CENTER);
            cellMat.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTableCB.addCell(cellMat);
            PdfPCell cellTipo = new PdfPCell(Phrase.getInstance("Tipo"));
            cellTipo.setPadding(5);
            cellTipo.setBorder(com.itextpdf.text.Rectangle.ALIGN_CENTER);
            cellTipo.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTableCB.addCell(cellTipo);
            PdfPCell cellNParte = new PdfPCell(Phrase.getInstance("Número de parte"));
            cellNParte.setPadding(5);
            cellNParte.setBorder(com.itextpdf.text.Rectangle.ALIGN_CENTER);
            cellNParte.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTableCB.addCell(cellNParte);
            PdfPCell cellValor = new PdfPCell(Phrase.getInstance("Valor"));
            cellValor.setPadding(5);
            cellValor.setBorder(com.itextpdf.text.Rectangle.ALIGN_CENTER);
            cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTableCB.addCell(cellValor);
            PdfPCell cellUM = new PdfPCell(Phrase.getInstance("Unidad de medida"));
            cellUM.setPadding(5);
            cellUM.setBorder(com.itextpdf.text.Rectangle.ALIGN_CENTER);
            cellUM.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTableCB.addCell(cellUM);
            PdfPCell cellCar = new PdfPCell(Phrase.getInstance("Caracteristicas"));
            cellCar.setPadding(5);
            cellCar.setBorder(com.itextpdf.text.Rectangle.ALIGN_CENTER);
            cellCar.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTableCB.addCell(cellCar);


            while (resultSet.next()){
                OutputStream out = new  FileOutputStream(resultSet.getString("cb_material")+".png");
                BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
                code39Bean.generateBarcode(canvas, resultSet.getString("cb_material"));
                canvas.finish();
                Image image = null;
                try {
                    image = new Image(new FileInputStream(resultSet.getString("cb_material")+".png"));
                    cellCB.setImage(com.itextpdf.text.Image.getInstance(resultSet.getString("cb_material")+".png"));
                    cellCB.setPaddingTop(5);
                    pdfPTableCB.addCell(cellCB);
                    cellMat.setPhrase(Phrase.getInstance(resultSet.getString("material")));
                    cellMat.setPaddingTop(5);
                    pdfPTableCB.addCell(cellMat);
                    cellTipo.setPhrase(Phrase.getInstance(resultSet.getString("tipo")));
                    cellTipo.setPaddingTop(5);
                    pdfPTableCB.addCell(cellTipo);
                    cellNParte.setPhrase(Phrase.getInstance(resultSet.getString("numero_parte")));
                    cellNParte.setPaddingTop(5);
                    pdfPTableCB.addCell(cellNParte);
                    cellValor.setPhrase(Phrase.getInstance(resultSet.getString("valor")));
                    cellValor.setPaddingTop(5);
                    pdfPTableCB.addCell(cellValor);
                    cellUM.setPhrase(Phrase.getInstance(resultSet.getString("unidad_de_medida")));
                    cellUM.setPaddingTop(5);
                    pdfPTableCB.addCell(cellUM);
                    cellCar.setPhrase(Phrase.getInstance(resultSet.getString("caracteristicas")));
                    cellCar.setPaddingTop(5);
                    pdfPTableCB.addCell(cellCar);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                imgCodeBar.setImage(image);
            }
            documento.add(pdfPTableCB);
            documento.close();
            if (rsMaterial.getString("material").equals("N/A")){
                Desktop.getDesktop().browse(new File("Libro_NA_material.pdf").toURI());
            }else {
                Desktop.getDesktop().browse(new File("Libro_"+rsMaterial.getString("material")+".pdf").toURI());
            }
            }




    }
    @FXML private void PrintCodeBar() throws IOException, DocumentException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, new FileOutputStream("CodigoDeBarras.pdf"));
        documento.open();
        if (rbCBPequeño.isSelected()){
            PdfPTable pdfPTableCB = new PdfPTable(8);
            pdfPTableCB.setWidthPercentage(100);
            BufferedImage image = ImageIO.read(new File("code39.png"));
            com.itextpdf.text.Image barcode = com.itextpdf.text.Image.getInstance(image, null);
            //Para modificar el tamaño se edita estos valores
            barcode.scaleToFit(100, 30);
            for (int i = 0; i < 40; i++) {
                PdfPCell cell = new PdfPCell(barcode);
                cell.setPadding(5);
                cell.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTableCB.addCell(cell);
            }
            documento.add(pdfPTableCB);
        } else if (rbCBMedio.isSelected()) {
            PdfPTable pdfPTableCB = new PdfPTable(4);
            pdfPTableCB.setWidthPercentage(100);
            BufferedImage image = ImageIO.read(new File("code39.png"));
            com.itextpdf.text.Image barcode = com.itextpdf.text.Image.getInstance(image, null);
            //Para modificar el tamaño se edita estos valores
            barcode.scaleToFit(150, 50);
            for (int i = 0; i < 40; i++) {
                PdfPCell cell = new PdfPCell(barcode);
                cell.setPadding(5);
                cell.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTableCB.addCell(cell);
            }
            documento.add(pdfPTableCB);
        } else if (rbCBGrande.isSelected()) {
            PdfPTable pdfPTableCB = new PdfPTable(2);
            pdfPTableCB.setWidthPercentage(100);
            BufferedImage image = ImageIO.read(new File("code39.png"));
            com.itextpdf.text.Image barcode = com.itextpdf.text.Image.getInstance(image, null);
            //Para modificar el tamaño se edita estos valores
            barcode.scaleToFit(200, 100);
            for (int i = 0; i < 20; i++) {
                PdfPCell cell = new PdfPCell(barcode);
                cell.setPadding(5);
                cell.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTableCB.addCell(cell);
            }
            documento.add(pdfPTableCB);
        }


        documento.close();
        Desktop.getDesktop().browse(new File("CodigoDeBarras.pdf").toURI());

    /*    int copies=5;
        BufferedImage image = ImageIO.read(new File("code39.png"));
        Printable printable = new Printable() {
            @Overridet
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex >= copies) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) graphics;
                int x = (int) pageFormat.getImageableX();
                int y = (int) pageFormat.getImageableY();
                int width = (int) pageFormat.getImageableWidth();
                int height = (int) pageFormat.getImageableHeight();
                g2d.translate(x, y);

                g2d.drawImage(image, 0, 0, width, height, null);

                return Printable.PAGE_EXISTS;
            }
        };

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(printable);

        if (job.printDialog()) {
            job.print();
        }*/
    }


   @FXML private Long GenerateNumber() throws SQLException {
        boolean bd=false;
        Random random=new Random();
        //long numero = 1000000002L;

        long numero = (long)(random.nextDouble()*10000000000L);
        /*if (!String.valueOf(numero).matches("\\d{10}")){

        }*/
        while(!bd){
            bd= VerifyCB(numero);
            numero = (long)(random.nextDouble()*10000000000L);
        }
        txtCodigoBarras.setText(String.valueOf(numero));
        return numero;
    }
    private void ActivateBtn(boolean New, boolean save, boolean edit, boolean cancel, boolean exit, boolean delete) throws SQLException {
        if (LoginController.resultado.getInt("create_material")==0){
            btnNew.setDisable(true);
        }else {btnNew.setDisable(New);}
        if (LoginController.resultado.getInt("update_material")==0){
            btnEdit.setDisable(true);
        }else {btnEdit.setDisable(edit);}
        if (LoginController.resultado.getInt("delete_material")==0){
            btnDelete.setDisable(true);
        }else {btnDelete.setDisable(delete);}

        btnSave.setDisable(save);
        btnCancel.setDisable(cancel);
        btnExit.setDisable(exit);
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

    private boolean VerifyCB(long num) throws SQLException {
        ResultSet res= conexion.consultar("SELECT herramienta.cb_herramienta FROM herramienta\n" +
                "                LEFT JOIN material\n" +
                "                ON herramienta.cb_herramienta = material.cb_material\n" +
                "                WHERE herramienta.cb_herramienta = ? OR material.cb_material = ?\n" +
                "                UNION\n" +
                "                SELECT material.cb_material\n" +
                "                FROM material\n" +
                "                LEFT JOIN herramienta\n" +
                "                ON herramienta.cb_herramienta = material.cb_material\n" +
                "                WHERE herramienta.cb_herramienta = ? OR material.cb_material = ?\n" +
                "                AND herramienta.cb_herramienta IS NULL;",String.valueOf(num),String.valueOf(num),String.valueOf(num),String.valueOf(num));

        // ResultSet resultSet = conexion.consultar("SELECT `cb_material` FROM `material` WHERE `cb_material`='"+num+"'");
        boolean bd=true;
        if (res.next()){
            bd=false;
        }else {
            return bd;
        }
        return bd;
    }
    @FXML private void CheckBoxChange1(){
        if (checkBoxNA1.isSelected()){
            txtArmario.setText("N/A");
            txtArmario.setDisable(true);
        }else{
            txtArmario.setText("");
            txtArmario.setDisable(false);
        }
    }
    @FXML private void CheckBoxChange2(){
        if (checkBoxNA2.isSelected()) {
            txtGaveta.setText("N/A");
            txtGaveta.setDisable(true);
        }else {
            txtGaveta.setText("");
            txtGaveta.setDisable(false);
        }
    }
    @FXML private void CheckBoxChange3(){
        if (checkBoxNA3.isSelected()) {
            txtSubCompartimento.setText("N/A");
            txtSubCompartimento.setDisable(true);
        }else {
            txtSubCompartimento.setText("");
            txtSubCompartimento.setDisable(false);
        }
    }
    @FXML private void CheckBoxChange4(){
        if (checkBoxNA4.isSelected()) {
            txtValor.setText("N/A");
            txtValor.setDisable(true);
            txtUnidadMedida.setText("N/A");
            txtUnidadMedida.setDisable(true);
        }else {
            txtValor.setText("");
            txtValor.setDisable(false);
            txtUnidadMedida.setText("");
            txtUnidadMedida.setDisable(false);
        }
    }
    private void CleanTextFields(){
        txtCaracteristicas.setText("");
        txtArmario.setText("");
        txtGaveta.setText("");
        txtSubCompartimento.setText("");
        txtTipo.setText("");
        checkBoxNA1.setSelected(false);
        checkBoxNA2.setSelected(false);
        checkBoxNA3.setSelected(false);
        checkBoxNA4.setSelected(false);
        txtNumParte.setText("");
        txtValor.setText("");
        txtUnidadMedida.setText("");
        txtStock .setText("");
        txtStockMin.setText("");


    }
    private boolean VerifyTxt(TextArea txtCar, ComboBox<String> cbMat, TextField... campos){
        for (TextField campo : campos){
            if (campo.getText().isEmpty()){
                return false;
            }
        }
        return !txtCar.getText().isEmpty() && cbMat.getSelectionModel().getSelectedIndex() != -1;
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
    @FXML private void Busqueda() throws SQLException {
        String busqueda= txtBusqueda.getText();
        String criterio="";

        if (rbCodigoBarras.isSelected() && !busqueda.isEmpty()){
            criterio="cb_material";
        } else if (rbArmario.isSelected() && !busqueda.isEmpty()) {
            criterio="tipo_de_armario";
        } else if (rbMaterial.isSelected() && !busqueda.isEmpty()) {
            criterio="material";
        } else if (rbTipo.isSelected() && !busqueda.isEmpty()) {
            criterio="tipo";
        }
        if (!busqueda.equals("")){
            ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material WHERE `"+criterio+"` LIKE '%"+busqueda+"%'")); // Puede fallar
        }else {
            ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material;"));
        }
    }

    @FXML private void ExcelReader(){
        try {//592
            FileInputStream fis = new FileInputStream("C:\\Users\\jesse\\Desktop\\inventario.xls");
            Workbook workbook = new HSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            int cont = 1000000000;
            int columnIndexToRead = 2;
            for (Row row : sheet) {
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


                cont++;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML private  void Comprobacion() throws SQLException {
        int cont = 1000000000;
        ResultSet resultSet = conexion.consultar("SELECT `cb_material` FROM `material` WHERE 1");
        while (resultSet.next()){
            cont++;
            if (resultSet.getInt("cb_material") != cont){
                System.out.println(cont);
                cont++;
            }
        }

    }
}


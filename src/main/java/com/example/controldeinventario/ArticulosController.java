package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Articulo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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
    @FXML Label lblContador;
    @FXML TextField txtBusqueda;
    @FXML ComboBox<String> cbMaterial = new ComboBox<>();
    @FXML TextArea txtCaracteristicas;
    @FXML TextField txtCodigoBarras, txtArmario,txtGaveta,txtSubCompartimento,txtTipo,txtNumParte,txtValor,txtUnidadMedida,txtStock,txtStockMin;
    Conexion conexion;
    KeyCombination keyCombination= new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    @FXML
    Button btnNew, btnSave, btnEdit, btnCancel, btnExit, btnDelete;
    @FXML TabPane tabV;
    @FXML Tab tabSearch, tabNew;
    @FXML RadioButton rbCodigoBarras, rbArmario, rbMaterial;
    @FXML RadioButton rbBajo, rbMedio,rbAlto;
    ToggleGroup toggleGroupBusqueda = new ToggleGroup();
    ToggleGroup toggleGroupFrecuencia = new ToggleGroup();
    @FXML TableView<Articulo> tableViewArticulos;
    TableColumn <Articulo,Long> colCB=new TableColumn<>("Codigo de barras");
    TableColumn <Articulo, String> colTArmario=new TableColumn<>("Tipo de armario");
    TableColumn <Articulo, String> colGaveta=new TableColumn<>("Gaveta");
    TableColumn <Articulo, String> colSubCom=new TableColumn<>("Sub-compartimento");
    TableColumn <Articulo,String> colMaterial=new TableColumn<>("Material");
    TableColumn <Articulo,String> colTipo=new TableColumn<>("Tipo");
    TableColumn <Articulo, String> colNumParte=new TableColumn<>("Número de parte");
    TableColumn <Articulo, Double> colValor=new TableColumn<>("Valor");
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

        rbBajo.setToggleGroup(toggleGroupFrecuencia);
        rbMedio.setToggleGroup(toggleGroupFrecuencia);
        rbAlto.setToggleGroup(toggleGroupFrecuencia);


        conexion = new Conexion();
        cbMaterial.getItems().clear();
        ResultSet resultSet = conexion.consultar("SELECT * FROM `tipo_material` WHERE `tipo_material`='Material'");
        while (resultSet.next()){
            cbMaterial.getItems().add((String) resultSet.getObject("material"));
        }
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

    @FXML private void exportButton() throws SQLException {


        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Datos");

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

        // Crear una celda para el título
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Inventario de materiales");

        // Combinar las celdas para crear una celda de título grande
        CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, 11);
        sheet.addMergedRegion(titleRange);

        // Establecer el estilo de la celda de título
        CellStyle titleStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();

        titleFont.setFontHeightInPoints((short) 16);
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment((short) 2);
        titleCell.setCellStyle(titleStyle);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setVerticalAlignment((short) 2);


        Row row = sheet.createRow(3);
        Cell cell = row.createCell(3);


        sheet.autoSizeColumn(0); // ajustar automáticamente el ancho de la columna 0

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



    private void ActualizarTabla(ResultSet rsArticulos) throws SQLException {
        int cont=0;
        tableViewArticulos.getItems().clear();
        while (rsArticulos.next()){
            cont++;
            Articulo a=new Articulo(rsArticulos.getLong("cb_material"), rsArticulos.getString("tipo_de_armario"), rsArticulos.getString("gaveta"), rsArticulos.getString("sub_compartimento"), rsArticulos.getString("material"),
                    rsArticulos.getString("tipo"), rsArticulos.getString("numero_parte"), rsArticulos.getDouble("valor"), rsArticulos.getString("unidad_de_medida"), rsArticulos.getString("caracteristicas"), rsArticulos.getString("frecuencia_de_uso"),
                    rsArticulos.getInt("cantidad"), rsArticulos.getInt("cantidad_min"));
            tableViewArticulos.getItems().add(a);
        }
        lblContador.setText("Se cargaron "+cont+" articulos");
    }
    @FXML private void NewArticulo() throws SQLException {
        txtCodigoBarras.setDisable(false);
        ActivateBtn(false,false,true,false,false,true);
        Long cb = GenerateNumber();
        tabV.getSelectionModel().select(tabNew);
        tabNew.setDisable(false);
        tabSearch.setDisable(true);
        txtCodigoBarras.setText(String.valueOf(cb));
        CleanTextFields();
    }
    @FXML private void SaveArticulo() throws SQLException {

        if (VerifyTxt(txtCaracteristicas, cbMaterial,txtArmario,txtCodigoBarras,txtGaveta,txtSubCompartimento,txtStock,txtStockMin,txtNumParte,txtTipo,txtValor,txtUnidadMedida)){
            ResultSet resultado = conexion.consultar("SELECT `id_material` FROM `tipo_material` WHERE `material`='"+cbMaterial.getSelectionModel().getSelectedItem()+"' AND `tipo_material`='Material' LIMIT 1");
            System.out.println(cbMaterial.getSelectionModel().getSelectedItem());
            if (resultado.next()){
                int id = resultado.getInt("id_material");
                ResultSet resultado2 = conexion.consultar("SELECT * FROM `material` WHERE `cb_material`='"+txtCodigoBarras.getText()+"' LIMIT 1");
                if (resultado2.next()){
                    conexion.insmodelim("UPDATE `material` SET `tipo_de_armario`='" + txtArmario.getText() + "', `gaveta`='" + txtGaveta.getText() + "', `sub_compartimento`='" + txtSubCompartimento.getText() + "', `id_material`='"+id+"', `tipo`='" + txtTipo.getText() + "', `numero_parte`='" + txtNumParte.getText() + "', `valor`='" + txtValor.getText() + "', `unidad_de_medida`='" + txtUnidadMedida.getText() + "', `caracteristicas`='" + txtCaracteristicas.getText() + "', `frecuencia_de_uso`='" + ((RadioButton) toggleGroupFrecuencia.getSelectedToggle()).getText() + "', `cantidad`='" + txtStock.getText() + "', `cantidad_min`='" + txtStockMin.getText() + "' WHERE `cb_material`='"+txtCodigoBarras.getText()+"'");
                    Exito("Actualizado con exito");

                }else {
                    conexion.insmodelim("INSERT INTO `material`(`cb_material`, `tipo_de_armario`, `gaveta`, `sub_compartimento`, `id_material`, `tipo`, `numero_parte`, `valor`, `unidad_de_medida`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES ('"+txtCodigoBarras.getText()+"','"+txtArmario.getText()+"','"+txtGaveta.getText()+"','"+txtSubCompartimento.getText()+"','"+id+"','"+txtTipo.getText()+"','"+txtNumParte.getText()+"','"+txtValor.getText()+"','"+txtUnidadMedida.getText()+"','"+txtCaracteristicas.getText()+"','"+((RadioButton) toggleGroupFrecuencia.getSelectedToggle()).getText()+"','"+txtStock.getText()+"','"+txtStockMin.getText()+"')");
                      /*      conexion.insmodelim("INSERT INTO `material`(`cb_material`, `tipo_de_armario`, `gaveta`, `sub_compartimento`, " +
                                    "`id_material`, `tipo`, `numero_parte`, `valor`, `unidad_de_medida`, `caracteristicas`, " +
                                    "`frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES ('"+txtCodigoBarras.getText()+"','"+txtArmario.getText()+"','"+txtGaveta.getText()+"'," +
                                    "'"+txtSubCompartimento.getText()+"','"+id+"','"+txtTipo.getText()+"','"+txtNumParte.getText()+"','"+txtValor.getText()+"','"+txtUnidadMedida.getText()+"','"+txtCaracteristicas.getText()+"','"+((RadioButton) toggleGroupFrecuencia.getSelectedToggle()).getText()+"'," +
                                    "'"+txtStock.getText()+"','"+txtStockMin.getText()+"'");*/
                    Exito("Lo logro señor");
                }
                tabV.getSelectionModel().select(tabSearch);
                tabSearch.setDisable(false);
                tabNew.setDisable(true);
                ActivateBtn(false,true,false,true,false,false);
                txtCodigoBarras.setDisable(false);
                ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material;"));

            }else {
                Error("Selecciona el material");
            }
        }else {Error("Faltan campos por rellenar");}



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
            switch (articulo.getF_uso()) {
                case "Bajo" -> toggleGroupFrecuencia.selectToggle(rbBajo);
                case "Medio" -> toggleGroupFrecuencia.selectToggle(rbMedio);
                case "Alto" -> toggleGroupFrecuencia.selectToggle(rbAlto);
            }
            txtStock.setText(String.valueOf(articulo.getCantidad()));
            txtStockMin.setText(String.valueOf(articulo.getCantidad_min()));
            txtCodigoBarras.setDisable(true);
            ActivateBtn(true,false,true,false,false,true);
        }else {Error("Selecciona un registro pa");}
    }
    @FXML private void CancelArticulo() throws SQLException {
        txtCodigoBarras.setText("");
        txtCodigoBarras.setDisable(false);
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
                conexion.insmodelim("DELETE FROM `material` WHERE `cb_material`='"+articulo.getCodigo_barras()+"'");
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
    @FXML private void GenerateCodeBar() throws IOException {
        Code39Bean code39Bean = new Code39Bean();
        final int dpi = 150;
        code39Bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));
        code39Bean.setWideFactor(3);
        code39Bean.doQuietZone(true);
        OutputStream out = new  FileOutputStream("code39.png");
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        code39Bean.generateBarcode(canvas, txtCodigoBarras.getText());
        canvas.finish();
        Image image = null;
        try {
            image = new Image(new FileInputStream("code39.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        imgCodeBar.setImage(image);

    }
    @FXML private void PrintCodeBar() throws IOException, DocumentException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, new FileOutputStream("CodigoDeBarras.pdf"));
        documento.open();

        PdfPTable pdfPTableCB = new PdfPTable(4);
        pdfPTableCB.setWidthPercentage(100);
        BufferedImage image = ImageIO.read(new File("code39.png"));
        com.itextpdf.text.Image barcode = com.itextpdf.text.Image.getInstance(image, null);
        barcode.scaleToFit(150, 50);
        for (int i = 0; i < 40; i++) {
            PdfPCell cell = new PdfPCell(barcode);
            cell.setPadding(5);
            cell.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTableCB.addCell(cell);
        }
        documento.add(pdfPTableCB);
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


    private Long GenerateNumber() throws SQLException {
        boolean bd=false;
        Random random=new Random();
        long numero = (long)(random.nextDouble()*10000000000L);
        while(!String.valueOf(numero).matches("\\d{10}") && !bd){
            numero = (long)(random.nextDouble()*10000000000L);
            bd= VerifyCB(numero);
        }
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
        ResultSet res= conexion.consultar("SELECT herramienta.id_herramienta\n" +
                "FROM herramienta\n" +
                "LEFT JOIN material\n" +
                "ON herramienta.id_herramienta = material.cb_material\n" +
                "WHERE herramienta.id_herramienta = '"+num+"' OR material.cb_material = '"+num+"'\n" +
                "UNION\n" +
                "SELECT material.cb_material\n" +
                "FROM material\n" +
                "LEFT JOIN herramienta\n" +
                "ON herramienta.id_herramienta = material.cb_material\n" +
                "WHERE herramienta.id_herramienta = '"+num+"' OR material.cb_material = '"+num+"'\n" +
                "AND herramienta.id_herramienta IS NULL;");

        // ResultSet resultSet = conexion.consultar("SELECT `cb_material` FROM `material` WHERE `cb_material`='"+num+"'");
        boolean bd=false;
        if (res.next()){
            bd=true;
        }else {
            return bd;
        }
        return bd;
    }
    private void CleanTextFields(){
        txtCaracteristicas.setText("");
        txtArmario.setText("");
        txtGaveta.setText("");
        txtSubCompartimento.setText("");
        txtTipo.setText("");
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

        if (rbCodigoBarras.isSelected() && !busqueda.equals("")){
            criterio="cb_material";
        } else if (rbArmario.isSelected() && !busqueda.equals("")) {
            criterio="tipo_de_armario";
        } else if (rbMaterial.isSelected() && !busqueda.equals("")) {
            criterio="material";
        }
        if (!busqueda.equals("")){
            ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material WHERE `"+criterio+"` LIKE '%"+busqueda+"%'"));
        }else {
            ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material;"));
        }
    }
}


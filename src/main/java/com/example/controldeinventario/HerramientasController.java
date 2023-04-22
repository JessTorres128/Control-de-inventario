package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Herramienta;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class HerramientasController {
    @FXML ImageView imgCodeBar = new ImageView();
    Conexion conexion;
    @FXML TabPane tabPaneHerramientas;
    @FXML Tab tabNew, tabSearch;
    @FXML TextField txtBusqueda;
    @FXML RadioButton rbID, rbNombre;
    @FXML TableView tableViewHerramientas;
    @FXML Label lblRegistros;
    @FXML TextField txtCB, txtTipo, txtStock, txtStockMin;
    @FXML TextArea txtCaracteristicas;
    @FXML RadioButton rbBajo, rbMedio, rbAlto;
    @FXML ComboBox<String> cbHerramienta = new ComboBox<>();
    @FXML Button btnNew,btnSave,btnEdit,btnCancel,btnExit;

    ToggleGroup toggleGroupBusqueda = new ToggleGroup();
    ToggleGroup toggleGroupFrecuencia = new ToggleGroup();

    TableColumn colID = new TableColumn("CB Herramienta");
    TableColumn colHerramienta= new TableColumn("Herramienta");
    TableColumn colTipo= new TableColumn("Tipo");
    TableColumn colCaracteristicas = new TableColumn("Caracteristicas");
    TableColumn colFUso=new TableColumn("Frecuencia de uso");
    TableColumn colCantidad=new TableColumn("Cantidad");
    TableColumn colCantidadMin=new TableColumn("Cantidad minima");




    @FXML protected void initialize() throws SQLException {
        rbBajo.setToggleGroup(toggleGroupFrecuencia);
        rbMedio.setToggleGroup(toggleGroupFrecuencia);
        rbAlto.setToggleGroup(toggleGroupFrecuencia);

        rbID.setToggleGroup(toggleGroupBusqueda);
        rbNombre.setToggleGroup(toggleGroupBusqueda);

        colID.setCellValueFactory(new PropertyValueFactory<Herramienta,Long>("cb_herramienta"));
        colHerramienta.setCellValueFactory(new PropertyValueFactory<Herramienta,String>("herramienta"));
        colTipo.setCellValueFactory(new PropertyValueFactory<Herramienta,String>("tipo"));
        colCaracteristicas.setCellValueFactory(new PropertyValueFactory<Herramienta,String>("caracteristicas"));
        colFUso.setCellValueFactory(new PropertyValueFactory<Herramienta,String>("frecuencia_de_uso"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<Herramienta,Integer>("cantidad"));
        colCantidadMin.setCellValueFactory(new PropertyValueFactory<Herramienta,Integer>("cantidad_min"));

        tableViewHerramientas.getColumns().addAll(colID,colHerramienta,colTipo,colCaracteristicas,colFUso,colCantidad,colCantidadMin);
        conexion=new Conexion();
        cbHerramienta.getItems().clear();
        ResultSet resultSet= conexion.consultar("SELECT `herramienta` FROM `herramientas`");
        while (resultSet.next()){
            cbHerramienta.getItems().add((String) resultSet.getObject("herramienta"));
        }
        ActualizarTabla(conexion.consultar("SELECT * FROM `herramienta` INNER JOIN herramientas ON herramienta.id_herramienta = herramientas.id_herramienta;"));
    }
    @FXML private void NewHerramienta() throws SQLException {
        txtCB.setDisable(false);
        ActivateBtn(false,false,true,false,false);
        Long cb = GenerateNumber();
        tabPaneHerramientas.getSelectionModel().select(tabNew);
        tabNew.setDisable(false);
        tabSearch.setDisable(true);
        txtCB.setText(String.valueOf(cb));
        CleanTextFields();

    }

    @FXML private void SaveHerramienta() throws SQLException {
        if (VerifyTxt(txtCaracteristicas, cbHerramienta,txtStock,txtTipo,txtStockMin)){
            ResultSet resultSetIDHerramienta = conexion.consultar("SELECT `id_herramienta` FROM `herramientas` WHERE `herramienta`='"+cbHerramienta.getSelectionModel().getSelectedItem()+"' LIMIT 1");
            if (resultSetIDHerramienta.next()){
                int id = resultSetIDHerramienta.getInt("id_herramienta");
                ResultSet resultSetUpdate = conexion.consultar("SELECT * FROM `herramienta` WHERE `cb_herramienta`='"+txtCB.getText()+"' LIMIT 1");
                if (resultSetUpdate.next()){
                    conexion.insmodelim("UPDATE `herramienta` SET `id_herramienta`='"+id+"',`tipo`='"+txtTipo.getText()+"',`caracteristicas`='"+txtCaracteristicas.getText()+"',`frecuencia_de_uso`='"+((RadioButton) toggleGroupFrecuencia.getSelectedToggle()).getText()+"',`cantidad`='"+txtStock.getText()+"',`cantidad_min`='"+txtStockMin.getText()+"' WHERE `cb_herramienta`='"+txtCB.getText()+"'");
                    Exito("Actualizado con exito");
                }else {
                    conexion.insmodelim("INSERT INTO `herramienta`(`cb_herramienta`,`id_herramienta`, `tipo`, `caracteristicas`, `frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES ('"+txtCB.getText()+"','"+id+"','"+txtTipo.getText()+"','"+txtCaracteristicas.getText()+"','"+((RadioButton) toggleGroupFrecuencia.getSelectedToggle()).getText()+"','"+txtStock.getText()+"','"+txtStockMin.getText()+"')");

                }
                tabPaneHerramientas.getSelectionModel().select(tabSearch);
                tabSearch.setDisable(false);
                tabNew.setDisable(true);
                ActivateBtn(false,true,false,true,false);
                txtCB.setDisable(false);
                ActualizarTabla(conexion.consultar("SELECT * FROM `herramienta` INNER JOIN herramientas ON herramienta.id_herramienta = herramientas.id_herramienta;"));
                Exito(cbHerramienta.getSelectionModel().getSelectedItem()+" "+txtTipo.getText()+" agregado");
            }else {
                Error("Selecciona el material");
            }
        }else {
            Error("Faltan campos por rellenar");
        }
    }
    @FXML private void EditHerramienta(){
        if (tableViewHerramientas.getSelectionModel().getSelectedItem() != null){
            Herramienta herramienta= (Herramienta) tableViewHerramientas.getSelectionModel().getSelectedItem();
            tabPaneHerramientas.getSelectionModel().select(tabNew);
            tabSearch.setDisable(true);
            tabNew.setDisable(false);
            txtCB.setText(String.valueOf(herramienta.getCb_herramienta()));
            cbHerramienta.getSelectionModel().select(herramienta.getHerramienta());
            txtTipo.setText(herramienta.getTipo());
            txtCaracteristicas.setText(herramienta.getCaracteristicas());
            switch (herramienta.getFrecuencia_de_uso()){
                case "Bajo":
                    toggleGroupFrecuencia.selectToggle(rbBajo);break;
                case "Medio":
                    toggleGroupFrecuencia.selectToggle(rbMedio);break;
                case "Alto":
                    toggleGroupFrecuencia.selectToggle(rbAlto);break;
            }
            txtStock.setText(String.valueOf(herramienta.getCantidad()));
            txtStockMin.setText(String.valueOf(herramienta.getCantidad_min()));
            txtCB.setDisable(true);
            ActivateBtn(true,false,true,false,false);
        }else {
            Error("Selecciona un registro pa");
        }
    }
    @FXML private void CancelHerramienta(){
        txtCB.setText("");
        txtCB.setDisable(false);
        CleanTextFields();
        ActivateBtn(false,true,false,true,false);
        tabPaneHerramientas.getSelectionModel().select(tabSearch);
        tabSearch.setDisable(false);
        tabNew.setDisable(true);
    }
    @FXML private void ExitHerramienta(){

    }
    @FXML private void GenerateCodeBar() throws IOException {
        Code39Bean code39Bean = new Code39Bean();
        final int dpi = 150;
        code39Bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));
        code39Bean.setWideFactor(3);
        code39Bean.doQuietZone(true);
        OutputStream out = new FileOutputStream("code39.png");
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        code39Bean.generateBarcode(canvas, txtCB.getText());
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

    }
    @FXML private void Busqueda() throws SQLException {
        String busqueda = txtBusqueda.getText();
        String criterio = "";
        if (rbID.isSelected() && !busqueda.equals("")){
            criterio="cb_herramienta";
        } else if (rbNombre.isSelected() && !busqueda.equals("")) {
            criterio="herramienta";
        }
        if (!busqueda.equals("")){
            ActualizarTabla(conexion.consultar("SELECT * FROM `herramienta` INNER JOIN herramientas ON herramienta.id_herramienta = herramientas.id_herramienta WHERE `"+criterio+"` LIKE '%"+busqueda+"%'"));
        }else {
            ActualizarTabla(conexion.consultar("SELECT * FROM `herramienta` INNER JOIN herramientas ON herramienta.id_herramienta = herramientas.id_herramienta;"));
        }
    }

    private void ActualizarTabla(ResultSet rsHerramientas) throws SQLException {
        int cont=0;
        tableViewHerramientas.getItems().clear();
        while (rsHerramientas.next()){
            Herramienta h= new Herramienta(rsHerramientas.getLong("cb_herramienta"),
                    rsHerramientas.getString("herramienta"),
                    rsHerramientas.getString("tipo"),
                    rsHerramientas.getString("caracteristicas"),
                    rsHerramientas.getString("frecuencia_de_uso"),
                    rsHerramientas.getInt("cantidad"),
                    rsHerramientas.getInt("cantidad_min"));
            tableViewHerramientas.getItems().add(h);
            cont++;
        }
        lblRegistros.setText("Se cargaron "+cont+" herramientas");
    }

    private void ActivateBtn(boolean New, boolean save, boolean edit, boolean cancel, boolean exit){
        btnNew.setDisable(New);
        btnSave.setDisable(save);
        btnEdit.setDisable(edit);
        btnCancel.setDisable(cancel);
        btnExit.setDisable(exit);
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
        txtTipo.setText("");
        txtCaracteristicas.setText("");
        txtStock.setText("");
        txtStockMin.setText("");
    }

    private boolean VerifyTxt(TextArea txtCar, ComboBox cbHerramienta, TextField... campos){
        for (TextField campo : campos){
            if (campo.getText().isEmpty()){
                return false;
            }
        }
        if (txtCar.getText().isEmpty() || cbHerramienta.getSelectionModel().getSelectedIndex() == -1){
            return false;
        }
        return true;
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
}

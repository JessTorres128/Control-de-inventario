package com.example.controldeinventario;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import com.example.controldeinventario.Datos.Articulo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.krysalis.barcode4j.tools.UnitConv;
import java.awt.image.BufferedImage;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ArticulosController {
    @FXML ImageView imgCodeBar = new ImageView();
    @FXML Label lblContador;
@FXML TextField txtBusqueda;
    @FXML ComboBox<String> cbMaterial = new ComboBox<>();
    @FXML TextArea txtCaracteristicas;
    @FXML TextField txtCodigoBarras, txtArmario,txtGaveta,txtSubCompartimento,txtTipo,txtNumParte,txtValor,txtUnidadMedida,txtStock,txtStockMin;
    Conexion conexion;
    @FXML
    Button btnNew, btnSave, btnEdit, btnCancel, btnExit;
    @FXML
    TabPane tabV;
    @FXML
    Tab tabSearch, tabNew;
    @FXML RadioButton rbCodigoBarras, rbArmario, rbMaterial;
    @FXML RadioButton rbBajo, rbMedio,rbAlto;
    ToggleGroup toggleGroupBusqueda = new ToggleGroup();
    ToggleGroup toggleGroupFrecuencia = new ToggleGroup();
    @FXML TableView tableViewArticulos;
    TableColumn colCB=new TableColumn("Codigo de barras");
    TableColumn colTArmario=new TableColumn("Tipo de armario");
    TableColumn colGaveta=new TableColumn("Gaveta");
    TableColumn colSubCom=new TableColumn("Sub-compartimento");
    TableColumn colMaterial=new TableColumn("Material");
    TableColumn colTipo=new TableColumn("Tipo");
    TableColumn colNumParte=new TableColumn("Número de parte");
    TableColumn colValor=new TableColumn("Valor");
    TableColumn colUMedida=new TableColumn("Unidad de medida");
    TableColumn colCaracteristicas=new TableColumn("Caracteristicas");
    TableColumn colFUso=new TableColumn("Frecuencia de uso");
    TableColumn colCantidad=new TableColumn("Cantidad");
    TableColumn colCantidadMin=new TableColumn("Cantidad minima");


    @FXML protected void initialize() throws SQLException {
        colCB.setCellValueFactory(new PropertyValueFactory<Articulo,Long>("codigo_barras"));
        colTArmario.setCellValueFactory(new PropertyValueFactory<Articulo, String>("tipo_de_armario"));
        colGaveta.setCellValueFactory(new PropertyValueFactory<Articulo, String>("gaveta"));
        colSubCom.setCellValueFactory(new PropertyValueFactory<Articulo, String>("sub_compartimento"));
        colMaterial.setCellValueFactory(new PropertyValueFactory<Articulo,String>("material"));
        colTipo.setCellValueFactory(new PropertyValueFactory<Articulo,String>("tipo"));
        colNumParte.setCellValueFactory(new PropertyValueFactory<Articulo, String>("numero_parte"));
        colValor.setCellValueFactory(new PropertyValueFactory<Articulo, Double>("valor"));
        colUMedida.setCellValueFactory(new PropertyValueFactory<Articulo,String>("unidad_medida"));
        colCaracteristicas.setCellValueFactory(new PropertyValueFactory<Articulo, String>("caracteristicas"));
        colFUso.setCellValueFactory(new PropertyValueFactory<Articulo, String>("f_uso"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<Articulo, Integer>("cantidad"));
        colCantidadMin.setCellValueFactory(new PropertyValueFactory<Articulo,Integer>("cantidad_min"));

        tableViewArticulos.getColumns().addAll(colCB,colTArmario,colGaveta,colSubCom,colMaterial,colTipo,colNumParte,colValor,colUMedida,colCaracteristicas,colFUso,colCantidad,colCantidadMin);
        rbCodigoBarras.setToggleGroup(toggleGroupBusqueda);
        rbArmario.setToggleGroup(toggleGroupBusqueda);
        rbMaterial.setToggleGroup(toggleGroupBusqueda);

        rbBajo.setToggleGroup(toggleGroupFrecuencia);
        rbMedio.setToggleGroup(toggleGroupFrecuencia);
        rbAlto.setToggleGroup(toggleGroupFrecuencia);


        conexion = new Conexion();
        cbMaterial.getItems().clear();
        ResultSet resultSet = conexion.consultar("SELECT `material` FROM `materiales`");
            while (resultSet.next()){
                cbMaterial.getItems().add((String) resultSet.getObject("material"));
            }

            ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN materiales ON material.id_material = materiales.id_material;"));



 /*   btnNew.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnSave.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnEdit.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnCancel.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnExit.setGraphic(new ImageView(new Image("",25,25,false,true))); */

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
    @FXML private void NewArticulo(ActionEvent event) throws SQLException {
        txtCodigoBarras.setDisable(false);
        ActivateBtn(false,false,true,false,false);
        Long cb = GenerateNumber();
        tabV.getSelectionModel().select(tabNew);
        tabNew.setDisable(false);
        tabSearch.setDisable(true);
        txtCodigoBarras.setText(String.valueOf(cb));
        CleanTextFields();
    }
    @FXML private void SaveArticulo(ActionEvent event) throws SQLException {

            if (VerifyTxt(txtCaracteristicas, cbMaterial,txtArmario,txtCodigoBarras,txtGaveta,txtSubCompartimento,txtStock,txtStockMin,txtNumParte,txtTipo,txtValor,txtUnidadMedida)){
                    ResultSet resultado = conexion.consultar("SELECT `id_material` FROM `materiales` WHERE `material`='"+cbMaterial.getSelectionModel().getSelectedItem().toString()+"' LIMIT 1");
                    System.out.println(cbMaterial.getSelectionModel().getSelectedItem());
                    if (resultado.next()){
                        int id = resultado.getInt("id_material");
                        ResultSet resultado2 = conexion.consultar("SELECT * FROM `material` WHERE `cb_material`='"+txtCodigoBarras.getText()+"' LIMIT 1");
                        if (resultado2.next()){
                            conexion.insmodelim("UPDATE `material` SET `tipo_de_armario`='" + txtArmario.getText() + "', `gaveta`='" + txtGaveta.getText() + "', `sub_compartimento`='" + txtSubCompartimento.getText() + "', `tipo`='" + txtTipo.getText() + "', `numero_parte`='" + txtNumParte.getText() + "', `valor`='" + txtValor.getText() + "', `unidad_de_medida`='" + txtUnidadMedida.getText() + "', `caracteristicas`='" + txtCaracteristicas.getText() + "', `frecuencia_de_uso`='" + ((RadioButton) toggleGroupFrecuencia.getSelectedToggle()).getText() + "', `cantidad`='" + txtStock.getText() + "', `cantidad_min`='" + txtStockMin.getText() + "' WHERE `cb_material`='"+txtCodigoBarras.getText()+"'");
                            Exito("Actualizado con exito");

                        }else {
                            conexion.insmodelim("INSERT INTO `material`(`cb_material`, `tipo_de_armario`, `gaveta`, `sub_compartimento`, " +
                                    "`id_material`, `tipo`, `numero_parte`, `valor`, `unidad_de_medida`, `caracteristicas`, " +
                                    "`frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES ('"+txtCodigoBarras.getText()+"','"+txtArmario.getText()+"','"+txtGaveta.getText()+"'," +
                                    "'"+txtSubCompartimento.getText()+"','"+id+"','"+txtTipo.getText()+"','"+txtNumParte.getText()+"','"+txtValor.getText()+"','"+txtUnidadMedida.getText()+"','"+txtCaracteristicas.getText()+"','"+((RadioButton) toggleGroupFrecuencia.getSelectedToggle()).getText()+"'," +
                                    "'"+txtStock.getText()+"','"+txtStockMin.getText()+"'");
                            Exito("Lo logro señor");
                        }
                        tabV.getSelectionModel().select(tabSearch);
                        tabSearch.setDisable(false);
                        tabNew.setDisable(true);
                        ActivateBtn(false,true,false,true,false);
                        txtCodigoBarras.setDisable(false);
                        ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN materiales ON material.id_material = materiales.id_material"));
                    }else {
                        Error("Selecciona el material");
                    }
                }else {Error("Faltan campos por rellenar");}



    }
    @FXML private void EditArticulo(ActionEvent event){
        if (tableViewArticulos.getSelectionModel().getSelectedItem() != null){
            Articulo articulo = (Articulo) tableViewArticulos.getSelectionModel().getSelectedItem();
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
            switch (articulo.getF_uso()){
                case "Bajo":
                    toggleGroupFrecuencia.selectToggle(rbBajo);break;
                case "Medio":
                    toggleGroupFrecuencia.selectToggle(rbMedio);break;
                case "Alto":
                    toggleGroupFrecuencia.selectToggle(rbAlto);break;
            }
            txtStock.setText(String.valueOf(articulo.getCantidad()));
                    txtStockMin.setText(String.valueOf(articulo.getCantidad_min()));
            txtCodigoBarras.setDisable(true);
            ActivateBtn(true,false,true,false,false);
        }else {Error("Selecciona un registro pa");}
    }
    @FXML private void CancelArticulo(ActionEvent event){
        txtCodigoBarras.setText("");
        txtCodigoBarras.setDisable(false);
        CleanTextFields();
        ActivateBtn(false,true,false,true,false);
        tabV.getSelectionModel().select(tabSearch);
        tabSearch.setDisable(false);
        tabNew.setDisable(true);
    }
    @FXML private void ExitArticulo(ActionEvent event){

    }
    @FXML private void GenerateCodeBar(ActionEvent event) throws IOException {
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
    private void ActivateBtn(boolean New, boolean save, boolean edit, boolean cancel, boolean exit){
        btnNew.setDisable(New);
        btnSave.setDisable(save);
        btnEdit.setDisable(edit);
        btnCancel.setDisable(cancel);
        btnExit.setDisable(exit);
    }
    private boolean VerifyCB(long num) throws SQLException {
        ResultSet resultSet = conexion.consultar("SELECT `cb_material` FROM `material` WHERE `cb_material`='"+num+"'");
        boolean bd=false;
        if (resultSet.next()){
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
    private boolean VerifyTxt(TextArea txtCar, ComboBox cbMat, TextField... campos){
        for (TextField campo : campos){
            if (campo.getText().isEmpty()){
                return false;
            }
        }
        if (txtCar.getText().isEmpty() || cbMat.getSelectionModel().getSelectedIndex() == -1){
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
    @FXML private void Busqueda(KeyEvent event) throws SQLException {
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
          ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN materiales ON material.id_material = materiales.id_material WHERE `"+criterio+"` LIKE '%"+busqueda+"%'"));
        }else {
            ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN materiales ON material.id_material = materiales.id_material;"));
        }
    }
}

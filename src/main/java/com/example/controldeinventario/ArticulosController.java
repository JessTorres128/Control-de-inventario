package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Articulo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ArticulosController {

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
        ResultSet resultSet = conexion.consultar("SELECT `material` FROM `materiales`");
            while (resultSet.next()){
                cbMaterial.getItems().add((String) resultSet.getObject("material"));
            }

            ResultSet rsArticulos= conexion.consultar("SELECT * FROM `material`");
            while (rsArticulos.next()){
                ResultSet rsMaterial = conexion.consultar("SELECT `material` FROM `materiales` WHERE `id_material`='"+rsArticulos.getInt("id_material")+"' LIMIT 1");
                if (rsMaterial.next()){
                    Articulo a=new Articulo(rsArticulos.getLong("cb_material"), rsArticulos.getString("tipo_de_armario"), rsArticulos.getString("gaveta"), rsArticulos.getString("sub_compartimento"), rsMaterial.getString("material"),
                            rsArticulos.getString("tipo"), rsArticulos.getString("numero_parte"), rsArticulos.getDouble("valor"), rsArticulos.getString("unidad_de_medida"), rsArticulos.getString("caracteristicas"), rsArticulos.getString("frecuencia_de_uso"),
                            rsArticulos.getInt("cantidad"), rsArticulos.getInt("cantidad_min"));
                    tableViewArticulos.getItems().add(a);
                }

            }



 /*   btnNew.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnSave.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnEdit.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnCancel.setGraphic(new ImageView(new Image("",25,25,false,true)));
    btnExit.setGraphic(new ImageView(new Image("",25,25,false,true))); */

    }





    @FXML private void NewArticulo(ActionEvent event) throws SQLException {
        txtCodigoBarras.setDisable(false);
        ActivateBtn(false,false,false,false,false);
        Long cb = GenerateNumber();
        tabV.getSelectionModel().select(tabNew);
        tabNew.setDisable(false);
        tabSearch.setDisable(true);
        txtCodigoBarras.setText(String.valueOf(cb));
        CleanTextFields();
    }
    @FXML private void SaveArticulo(ActionEvent event) throws SQLException {
        boolean sp= VerifyTxt(txtCaracteristicas, cbMaterial,txtArmario,txtCodigoBarras,txtGaveta,txtSubCompartimento,txtStock,txtStockMin,txtNumParte,txtTipo,txtValor,txtUnidadMedida);
        if (sp){
            ResultSet resultado = conexion.consultar("SELECT `id_material` FROM `materiales` WHERE `material`='"+cbMaterial.getSelectionModel().getSelectedItem().toString()+"' LIMIT 1");
            System.out.println(cbMaterial.getSelectionModel().getSelectedItem());
            if (resultado.next()){
                int id = resultado.getInt("id_material");
                System.out.println(id);
                conexion.insmodelim("INSERT INTO `material`(`cb_material`, `tipo_de_armario`, `gaveta`, `sub_compartimento`, " +
                        "`id_material`, `tipo`, `numero_parte`, `valor`, `unidad_de_medida`, `caracteristicas`, " +
                        "`frecuencia_de_uso`, `cantidad`, `cantidad_min`) VALUES ('"+txtCodigoBarras.getText()+"','"+txtArmario.getText()+"','"+txtGaveta.getText()+"'," +
                        "'"+txtSubCompartimento.getText()+"','"+id+"','"+txtTipo.getText()+"','"+txtNumParte.getText()+"','"+txtValor.getText()+"','"+txtUnidadMedida.getText()+"','"+txtCaracteristicas.getText()+"','"+((RadioButton) toggleGroupFrecuencia.getSelectedToggle()).getText()+"'," +
                        "'"+txtStock.getText()+"','"+txtStockMin.getText()+"')");
                Exito("Lo logro señor");
                System.out.println("Todo correcto");
                tabV.getSelectionModel().select(tabSearch);
                tabSearch.setDisable(false);
                tabNew.setDisable(true);
                ActivateBtn(false,true,false,true,false);
                txtCodigoBarras.setDisable(false);
            }else {
                Error("Selecciona el material");
            }


        }else {
            Error("Faltan campos por rellenar");
        }
    }
    @FXML private void EditArticulo(ActionEvent event){

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
        boolean bd;
        if (resultSet.next()){
            bd=true;
        }else {
            bd=false;
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
}

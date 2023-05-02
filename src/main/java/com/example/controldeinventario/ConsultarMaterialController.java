package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Articulo;
import com.example.controldeinventario.Datos.Herramienta;
import com.example.controldeinventario.Datos.Registro;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultarMaterialController {
    PedidosController pedidosController;
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

    Conexion conexion;
    @FXML Button btnSalir;
    ToggleGroup toggleGroupMostrar = new ToggleGroup();
    ToggleGroup toggleGroupBuscar = new ToggleGroup();
    @FXML TableView tableViewMats;
    @FXML TextField txtBusqueda;
    @FXML RadioButton rbMaterial, rbHerramienta;
    @FXML RadioButton rbCodigo_Barras, rbNombre, rbTipo;

    TableColumn colID = new TableColumn("CB Herramienta");
    TableColumn colHerramienta= new TableColumn("Herramienta");
    TableColumn colTipoHerramienta= new TableColumn("Tipo");
    TableColumn colCaracteristicasHerramienta = new TableColumn("Caracteristicas");
    TableColumn colFUsoHerramienta=new TableColumn("Frecuencia de uso");
    TableColumn colCantidadHerramienta=new TableColumn("Cantidad");
    TableColumn colCantidadMinHerramienta=new TableColumn("Cantidad minima");


    @FXML protected void initialize() throws SQLException {
        pedidosController = new PedidosController();
        //Material
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

        conexion = new Conexion();

        //Herramienta
        colID.setCellValueFactory(new PropertyValueFactory<Herramienta,Long>("cb_herramienta"));
        colHerramienta.setCellValueFactory(new PropertyValueFactory<Herramienta,String>("herramienta"));
        colTipoHerramienta.setCellValueFactory(new PropertyValueFactory<Herramienta,String>("tipo"));
        colCaracteristicasHerramienta.setCellValueFactory(new PropertyValueFactory<Herramienta,String>("caracteristicas"));
        colFUsoHerramienta.setCellValueFactory(new PropertyValueFactory<Herramienta,String>("frecuencia_de_uso"));
        colCantidadHerramienta.setCellValueFactory(new PropertyValueFactory<Herramienta,Integer>("cantidad"));
        colCantidadMinHerramienta.setCellValueFactory(new PropertyValueFactory<Herramienta,Integer>("cantidad_min"));
        rbMaterial.setToggleGroup(toggleGroupMostrar);
        rbHerramienta.setToggleGroup(toggleGroupMostrar);
        rbCodigo_Barras.setToggleGroup(toggleGroupBuscar);
        rbNombre.setToggleGroup(toggleGroupBuscar);
        rbTipo.setToggleGroup(toggleGroupBuscar);
        tableViewMats.getColumns().addAll(colCB,colTArmario,colGaveta,colSubCom,colMaterial,colTipo,colNumParte,colValor,colUMedida,colCaracteristicas,colFUso,colCantidad,colCantidadMin);
        ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material;"));
    }
    @FXML private void SalirConsultaMat(){

        if (tableViewMats.getSelectionModel().getSelectedItem() != null){
            cerrarVentana();
            if (rbMaterial.isSelected()){
                Articulo articulo= (Articulo) tableViewMats.getSelectionModel().getSelectedItem();
                Registro registro = new Registro(articulo.getMaterial(),articulo.getTipo(),articulo.getValor(), articulo.getUnidad_medida(),1 );
                pedidosController.AgregarMaterial(registro);
            }else {
                Herramienta herramienta = (Herramienta) tableViewMats.getSelectionModel().getSelectedItem();
                Registro registro = new Registro(herramienta.getHerramienta(), herramienta.getTipo(), 1);
                pedidosController.AgregarMaterial(registro);
            }

        }

    }

    @FXML private void Busqueda() throws SQLException {
        String busqueda= txtBusqueda.getText();
        String criterio="";

        if (rbMaterial.isSelected()){
            if (rbCodigo_Barras.isSelected() && !busqueda.equals("")){
                criterio="cb_material";
            } else if (rbNombre.isSelected() && !busqueda.equals("")) {
                criterio="material";
            } else if (rbTipo.isSelected() && !busqueda.equals("")) {
                criterio="tipo";
            }
            if (!busqueda.equals("")){
                ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material WHERE `"+criterio+"` LIKE '%"+busqueda+"%'"));
            }else {
                ActualizarTabla(conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material;"));
            }
        }else if (rbHerramienta.isSelected()){
            if (rbCodigo_Barras.isSelected() && !busqueda.equals("")){
                criterio="cb_herramienta";
            } else if (rbNombre.isSelected() && !busqueda.equals("")) {
                criterio="material";
            } else if (rbTipo.isSelected() && !busqueda.equals("")) {
                criterio="tipo";
            }
            if (!busqueda.equals("")){
                ActualizarTabla(conexion.consultar("SELECT * FROM `herramienta` INNER JOIN tipo_material ON herramienta.id_herramienta = tipo_material.id_material WHERE `"+criterio+"` LIKE '%"+busqueda+"%'"));
            }else {
                ActualizarTabla(conexion.consultar("SELECT * FROM `herramienta` INNER JOIN tipo_material ON herramienta.id_herramienta = tipo_material.id_material;"));
            }
        }

    }

    private void ActualizarTabla(ResultSet rsMats) throws SQLException {
        tableViewMats.getItems().clear();
        if (rbMaterial.isSelected()){
            while (rsMats.next()){
                Articulo material=new Articulo(rsMats.getLong("cb_material"), rsMats.getString("tipo_de_armario"), rsMats.getString("gaveta"), rsMats.getString("sub_compartimento"), rsMats.getString("material"),
                        rsMats.getString("tipo"), rsMats.getString("numero_parte"), rsMats.getDouble("valor"), rsMats.getString("unidad_de_medida"), rsMats.getString("caracteristicas"), rsMats.getString("frecuencia_de_uso"),
                        rsMats.getInt("cantidad"), rsMats.getInt("cantidad_min"));
                tableViewMats.getItems().add(material);

            }
        }else {
            while (rsMats.next()){
                Herramienta h= new Herramienta(rsMats.getLong("cb_herramienta"),
                        rsMats.getString("material"),
                        rsMats.getString("tipo"),
                        rsMats.getString("caracteristicas"),
                        rsMats.getString("frecuencia_de_uso"),
                        rsMats.getInt("cantidad"),
                        rsMats.getInt("cantidad_min"));
                tableViewMats.getItems().add(h);
            }
        }


    }
    @FXML private void CambiarColumnas() throws SQLException {
        tableViewMats.getColumns().clear();
        if (rbMaterial.isSelected()){
            tableViewMats.getColumns().addAll(colCB,colTArmario,colGaveta,colSubCom,colMaterial,colTipo,colNumParte,colValor,colUMedida,colCaracteristicas,colFUso,colCantidad,colCantidadMin);
            Busqueda();
         } else if (rbHerramienta.isSelected()) {
            tableViewMats.getColumns().addAll(colID,colHerramienta,colTipoHerramienta,colCaracteristicasHerramienta,colFUsoHerramienta,colCantidadHerramienta,colCantidadMinHerramienta);
            Busqueda();
        }

    }
    public void cerrarVentana() {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
}



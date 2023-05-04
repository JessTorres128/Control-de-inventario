package com.example.controldeinventario;

import com.example.controldeinventario.Datos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class PedidosController {
    ToggleGroup toggleGroupBusqueda = new ToggleGroup();
    @FXML TabPane tabPaneVentana;
    @FXML Tab tabSearch, tabNew;
    Conexion conexion;
    @FXML Label lblContador;
    @FXML RadioButton rbID, rbNumControl, rbProfesor, rbMaterial;
    @FXML TextField txtBusqueda;
    @FXML TableView tableViewPedidos;
    @FXML Button btnNew, btnSave, btnEdit, btnDelete, btnCancel, btnExit;
    @FXML TextField txtID, txtNumControl, txtFecha, txtProfesor, txtMateria, txtBusquedaID;
    @FXML CheckBox checkBoxNA1,checkBoxNA2,checkBoxNA3;
    public static ObservableList<Registro> productos;
    @FXML
    TableView<Registro> tableViewPedidoMaterial = new TableView<>();
    TableColumn tableColumnIDPedido = new TableColumn<>();
    TableColumn tableColumnNumControl = new TableColumn<>();
    TableColumn tableColumnEstado = new TableColumn<>();
    TableColumn tableColumnFecha = new TableColumn<>();
    TableColumn tableColumnProfesor = new TableColumn<>();
    TableColumn tableColumnMateria = new TableColumn<>();
    public Stage ventanaSecundaria = new Stage();
    TableColumn tableColumnNumero = new TableColumn("No");
    TableColumn tableColumnNombre = new TableColumn("Nombre");
    TableColumn tableColumnModelo = new TableColumn("Modelo");
    TableColumn tableColumnValor = new TableColumn("Valor");
    TableColumn tableColumnMedida = new TableColumn("Medida");
    TableColumn tableColumnBtnMinus = new TableColumn("     ");
    TableColumn tableColumnItemCount = new TableColumn("Cantidad");
    TableColumn tableColumnBtnPlus = new TableColumn("      ");
    TableColumn tableColumnBtnDelete = new TableColumn("        ");

    Callback<TableColumn<Registro, Integer>, TableCell<Registro, Integer>> celdaNo =
            objectStringTableColumn -> {
                TableCell<Registro, Integer> cell = new TableCell<Registro, Integer>() {
                    @Override
                    protected void updateItem(Integer s, boolean b) {
                        if (b) {
                            setText(null);
                        } else {
                            int index = getIndex() + 1;
                            setText(String.valueOf(index));
                        }


                    }
                };
                return cell;
            };
    Callback<TableColumn<Registro,String>, TableCell<Registro,String>> celdaMinus=
            objectStringTableColumn -> {
                TableCell<Registro,String> cell = new TableCell<Registro,String>(){
                    Button btnMinus = new Button("-");

                    @Override
                    protected void updateItem(String s, boolean b) {
                        if (b){
                            setGraphic(null);
                            setText(null);
                        }else {
                            btnMinus.setOnAction(event -> {
                                Registro r = productos.get(getIndex());
                                r.setCantidad(r.getCantidad()-1);
                                if (r.getCantidad() <=0){
                                    productos.remove(getIndex());
                                }else {
                                    productos.set(getIndex(),r);
                                }


                            });
                            setGraphic(btnMinus);
                            setText(null);
                        }


                    }
                };
                return cell;
            };

    Callback<TableColumn<Registro,String>, TableCell<Registro,String>> celdaPlus=
            objectStringTableColumn -> {
                TableCell<Registro,String> cell = new TableCell<Registro,String>(){
                    Button btnPlus = new Button("+");

                    @Override
                    protected void updateItem(String s, boolean b) {
                        if (b){
                            setGraphic(null);
                            setText(null);
                        }else {
                            btnPlus.setOnAction(event -> {
                                Registro r = productos.get(getIndex());
                                r.setCantidad(r.getCantidad()+1);
                                if (r.getCantidad() <=0){
                                    productos.remove(getIndex());
                                }else {
                                    productos.set(getIndex(),r);
                                }
                            });
                            setGraphic(btnPlus);
                            setText(null);
                        }


                    }
                };
                return cell;
            };

    Callback<TableColumn<Registro,String>, TableCell<Registro,String>> celdaDelete=
            objectStringTableColumn -> {
                TableCell<Registro,String> cell = new TableCell<Registro,String>(){
                    Button btnDlte = new Button("Quitar");

                    @Override
                    protected void updateItem(String s, boolean b) {
                        if (b){
                            setGraphic(null);
                            setText(null);
                        }else {
                            btnDlte.setOnAction(event -> {
                                productos.remove(getIndex());
                            });
                            setGraphic(btnDlte);
                            setText(null);
                        }


                    }
                };
                return cell;
            };





    @FXML protected void initialize(){
        rbID.setToggleGroup(toggleGroupBusqueda);
        rbMaterial.setToggleGroup(toggleGroupBusqueda);
        rbNumControl.setToggleGroup(toggleGroupBusqueda);
        rbProfesor.setToggleGroup(toggleGroupBusqueda);
        conexion= new Conexion();
        productos = FXCollections.observableArrayList();
        tableColumnNumero.setCellFactory(celdaNo);
        tableColumnNumero.setMaxWidth(40);
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<Registro,String>("nombre"));
        tableColumnModelo.setCellValueFactory(new PropertyValueFactory<Registro,String>("tipo"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<Registro,Double>("valor"));
        tableColumnMedida.setCellValueFactory(new PropertyValueFactory<Registro,String>("unidad_medida"));
        tableColumnBtnMinus.setCellFactory(celdaMinus);
        tableColumnBtnMinus.setMaxWidth(30);
        tableColumnItemCount.setCellValueFactory(new PropertyValueFactory<Registro,Integer>("cantidad"));
        tableColumnBtnPlus.setCellFactory(celdaPlus);
        tableColumnBtnPlus.setMaxWidth(35);
        tableColumnBtnDelete.setCellFactory(celdaDelete);

        tableViewPedidoMaterial.getColumns().addAll( tableColumnNumero,tableColumnNombre,tableColumnModelo,tableColumnValor,tableColumnMedida,tableColumnBtnMinus, tableColumnItemCount,tableColumnBtnPlus,tableColumnBtnDelete);
        tableViewPedidoMaterial.setItems(productos);

        tableColumnIDPedido.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("id_pedido"));
        tableColumnNumControl.setCellValueFactory(new PropertyValueFactory<Pedido, String>("num_control"));
        tableColumnEstado.setCellValueFactory(new PropertyValueFactory<Pedido, String>("estado"));
        tableColumnFecha.setCellValueFactory(new PropertyValueFactory<Pedido, Date>("fecha"));
        tableColumnProfesor.setCellValueFactory(new PropertyValueFactory<Pedido, String>("profesor"));
        tableColumnMateria.setCellValueFactory(new PropertyValueFactory<Pedido, String>("materia"));
    }

    @FXML private void NewPedido() throws SQLException {
        txtID.setDisable(false);
        ActivateBtn(false,false,true,false,false,true);
        tabPaneVentana.getSelectionModel().select(tabNew);
        tabNew.setDisable(false);
        tabSearch.setDisable(true);
        CleanTextFields();
    }

    @FXML private void SavePedido(){

    }
    @FXML private void EditPedido() throws SQLException {
        if (tableViewPedidos.getSelectionModel().getSelectedItem() != null){
            Pedido pedido= (Pedido) tableViewPedidos.getSelectionModel().getSelectedItem();
            ResultSet rsPedido = conexion.consultar("SELECT * FROM `pedido` WHERE `id_pedido`='"+pedido.getId_pedido()+"'");
            if (rsPedido.next()){
            /*   usuario = new Usuario(rsUsuario.getInt("id_user"), rsUsuario.getString("nombre_completo")
                        ,rsUsuario.getString("sexo"), rsUsuario.getString("username"), rsUsuario.getString("password"), rsUsuario.getString("nombre_rol"));
                tabPaneVentana.getSelectionModel().select(tabNew);
                tabSearch.setDisable(true);
                tabNew.setDisable(false);
                txtID.setText(String.valueOf(usuario.getId_user()));
                txtNombre.setText(String.valueOf(usuario.getNombre_completo()));
                txtUsername.setText(String.valueOf(usuario.getUsername()));
                cbRoles.getSelectionModel().select(usuario.getNombre_rol());
                txtPass.setText(usuario.getPassword());
                txtConfirmarPass.setText(usuario.getPassword());
                switch (usuario.getSexo()) {
                    case "Masculino" -> toggleGroupSexo.selectToggle(rbMasculino);
                    case "Femenino" -> toggleGroupSexo.selectToggle(rbFemenino);
                }
                txtID.setDisable(true);
                ActivateBtn(true,false,true,false,false,true);*/
            }

        }else {
            Error("Selecciona un registro pa");
        }
    }
    @FXML private void DeletePedido() throws SQLException {
        if (tableViewPedidos.getSelectionModel().getSelectedItem() != null){
            Articulo articulo = (Articulo) tableViewPedidos.getSelectionModel().getSelectedItem();
            if (ConfirmarBorrar("Deseas borrar "+articulo.getMaterial()+" "+articulo.getTipo())){
                conexion.insmodelim("DELETE FROM `material` WHERE `cb_material`='"+articulo.getCodigo_barras()+"'");
                Exito("Registro borrado exitosamente");
                ActualizarTabla(conexion.consultar("SELECT * FROM `pedido`"));
            }

        }else {
            Error("Selecciona un registro pa");
        }
    }
    @FXML private void CanecelPedido() throws SQLException {
        txtID.setText("");
        txtID.setDisable(false);
        CleanTextFields();
        ActivateBtn(false,true,false,true,false,false);
        tabPaneVentana.getSelectionModel().select(tabSearch);
        tabSearch.setDisable(false);
        tabNew.setDisable(true);
    }
    @FXML private void ExitPedido(){

    }

    private void ActualizarTabla(ResultSet rsPedido) throws SQLException {
        int cont=0;
        tableViewPedidos.getItems().clear();
        while (rsPedido.next()){
            Pedido pedido = new Pedido(rsPedido.getInt("id_pedido "),rsPedido.getString("num_control"),
                    rsPedido.getString("estado"),rsPedido.getDate("fecha"),rsPedido.getString("profesor"),
                    rsPedido.getString("materia"));
            tableViewPedidos.getItems().add(pedido);
            cont++;
        }
        lblContador.setText("Se cargaron "+cont+" pedidos");

    }
    public void AgregarMaterial(Registro a){
        if (productos != null){
            boolean cantidadPlus=false;
                for (int x=0; x<productos.size();x++){
                    if (productos.get(x).getNombre().equals(a.getNombre()) && Objects.equals(productos.get(x).getValor(), a.getValor()) && productos.get(x).getTipo().equals(a.getTipo()) && Objects.equals(productos.get(x).getUnidad_medida(), a.getUnidad_medida())){
                        Registro r = productos.get(x);
                        r.setCantidad((r.getCantidad()+1));
                        if (r.getCantidad() <=0){
                            productos.remove(x);
                        }else {
                            productos.set(x,r);
                        }
                        cantidadPlus=true;
                    }
                }
                if (!cantidadPlus){
                    productos.add(a);
                }
        }
    }

    @FXML public void EscaneoBusqueda() throws SQLException {
        if (txtBusquedaID.getText().matches("\\d{10}")){
            ResultSet rsArticulo = conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material WHERE cb_material='"+txtBusquedaID.getText()+"'");
            if (rsArticulo.next()){
                Registro registro = new Registro(rsArticulo.getString("tipo_material"),rsArticulo.getString("tipo"),rsArticulo.getDouble("valor"), rsArticulo.getString("unidad_de_medida"),1);
                AgregarMaterial(registro);
                txtBusquedaID.setText("");
            }else {
                ResultSet rsHerramienta = conexion.consultar("SELECT * FROM `herramienta` INNER JOIN tipo_material ON herramienta.id_herramienta = tipo_material.id_material WHERE cb_herramienta='"+txtBusquedaID.getText()+"'");
                if (rsHerramienta.next()){
                    Registro registro = new Registro(rsHerramienta.getString("tipo_material"),rsHerramienta.getString("tipo"), 1);
                    AgregarMaterial(registro);
                    txtBusquedaID.setText("");
                }
            }
        }
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
    @FXML private void CheckBoxChange(){
        if (checkBoxNA1.isSelected()){
            txtNumControl.setText("N/A");
            txtNumControl.setDisable(true);
        }else {
            txtNumControl.setText("");
            txtNumControl.setDisable(false);
        }
        if (checkBoxNA2.isSelected()) {
            txtProfesor.setText("N/A");
            txtProfesor.setDisable(true);
        }else {
            txtProfesor.setText("");
            txtProfesor.setDisable(false);
        }
        if (checkBoxNA3.isSelected()) {
            txtMateria.setText("N/A");
            txtMateria.setDisable(true);
        }else {
            txtMateria.setText("");
            txtMateria.setDisable(false);
        }
    }

    @FXML private void BuscarProducto() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConsultarMaterial.fxml"));
        Parent root = fxmlLoader.load();
        ventanaSecundaria.setScene(new Scene(root));
        ventanaSecundaria.showAndWait();
    }

    private void ActivateBtn(boolean New, boolean save, boolean edit, boolean cancel, boolean exit, boolean delete) throws SQLException {
        if (LoginController.resultado.getInt("crud_pedido")==0){
            btnNew.setDisable(true);
            btnEdit.setDisable(true);
            btnDelete.setDisable(true);
        }else {
            btnNew.setDisable(New);
            btnEdit.setDisable(edit);
            btnDelete.setDisable(delete);
        }


        btnSave.setDisable(save);
        btnCancel.setDisable(cancel);
        btnExit.setDisable(exit);
    }

    private void CleanTextFields(){
        txtNumControl.setText("");
        txtFecha.setText("");
        txtMateria.setText("");
        txtProfesor.setText("");
        txtBusquedaID.setText("");
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



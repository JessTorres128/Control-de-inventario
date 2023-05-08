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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class PedidosController {
    ZoneId zonaHoraria = ZoneId.of("America/Mazatlan");
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    ToggleGroup toggleGroupBusqueda = new ToggleGroup();
    @FXML TabPane tabPaneVentana;
    @FXML Tab tabSearch, tabNew;
    static Conexion conexion;
    @FXML Label lblContador;
    @FXML RadioButton rbID, rbNumControl, rbProfesor, rbMaterial;
    @FXML TextField txtBusqueda;
    @FXML TableView<Pedido> tableViewPedidos = new TableView<>();
    @FXML Button btnNew, btnSave, btnEdit, btnDelete, btnCancel, btnExit;
    @FXML TextField txtID, txtNumControl, txtFecha, txtProfesor, txtMateria, txtBusquedaID, txtNombre;
    @FXML CheckBox checkBoxNA1,checkBoxNA2,checkBoxNA3;
    public static ObservableList<Registro> productos;
    @FXML
    TableView<Registro> tableViewPedidoMaterial = new TableView<>();
    TableColumn tableColumnIDPedido = new TableColumn<>("ID Pedido");
    TableColumn tableColumnCB = new TableColumn<>("Código de barras");
    TableColumn tableColumnNombrePersona = new TableColumn<>("Nombre");
    TableColumn tableColumnNumControl = new TableColumn<>("Número de control");
    TableColumn tableColumnEstado = new TableColumn<>("Estado");
    TableColumn tableColumnFecha = new TableColumn<>("Fecha");
    TableColumn tableColumnProfesor = new TableColumn<>("Profesor");
    TableColumn tableColumnMateria = new TableColumn<>("Materia");
    TableColumn tableColumnVer = new TableColumn<>("Mostrar pedido");
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
                                try {
                                    if (VerificarCantidad(r.getCb(),r.getCantidad()+1)){
                                        r.setCantidad(r.getCantidad()+1);
                                        if (r.getCantidad() <=0){
                                            productos.remove(getIndex());
                                        }else {
                                            productos.set(getIndex(),r);
                                        }
                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
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

    Callback<TableColumn<Registro,String>, TableCell<Registro,String>> celdaVer=
            objectStringTableColumn -> {
                TableCell<Registro,String> cell = new TableCell<>() {
                    Button btnVer = new Button("Ver");

                    @Override
                    protected void updateItem(String s, boolean b) {
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnVer.setOnAction(event -> {
                                Pedido pedido = tableViewPedidos.getItems().get(getIndex());
                                System.out.println(pedido);
                            });
                            setGraphic(btnVer);
                            setText(null);
                        }


                    }
                };
                return cell;
            };





    @FXML protected void initialize() throws SQLException {

        rbID.setToggleGroup(toggleGroupBusqueda);
        rbMaterial.setToggleGroup(toggleGroupBusqueda);
        rbNumControl.setToggleGroup(toggleGroupBusqueda);
        rbProfesor.setToggleGroup(toggleGroupBusqueda);
        conexion= new Conexion();
        productos = FXCollections.observableArrayList();
        tableColumnNumero.setCellFactory(celdaNo);
        tableColumnNumero.setMaxWidth(40);
        tableColumnCB.setCellValueFactory(new PropertyValueFactory<Registro,Long>("cb"));
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

        tableViewPedidoMaterial.getColumns().addAll( tableColumnNumero,tableColumnCB,tableColumnNombre,tableColumnModelo,tableColumnValor,tableColumnMedida,tableColumnBtnMinus, tableColumnItemCount,tableColumnBtnPlus,tableColumnBtnDelete);
        tableViewPedidoMaterial.setItems(productos);

        tableColumnIDPedido.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("id_pedido"));
        tableColumnNombrePersona.setCellValueFactory(new PropertyValueFactory<Pedido, String>("nombre_persona"));
        tableColumnNumControl.setCellValueFactory(new PropertyValueFactory<Pedido, String>("num_control"));
        tableColumnEstado.setCellValueFactory(new PropertyValueFactory<Pedido, String>("estado"));
        tableColumnFecha.setCellValueFactory(new PropertyValueFactory<Pedido, Date>("fecha"));
        tableColumnProfesor.setCellValueFactory(new PropertyValueFactory<Pedido, String>("profesor"));
        tableColumnMateria.setCellValueFactory(new PropertyValueFactory<Pedido, String>("materia"));
        tableColumnVer.setCellFactory(celdaVer);
        tableViewPedidos.getColumns().addAll(tableColumnIDPedido,tableColumnNombrePersona,tableColumnNumControl,tableColumnEstado,tableColumnFecha,tableColumnProfesor,tableColumnMateria,tableColumnVer);
        ActualizarTabla(conexion.consultar("SELECT * FROM `pedido`"));
    }

    @FXML private void NewPedido() throws SQLException {
        productos.clear();
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zonaHoraria);

        txtID.setDisable(false);
        ActivateBtn(false,false,true,false,false,true);
        tabPaneVentana.getSelectionModel().select(tabNew);
        tabNew.setDisable(false);
        tabSearch.setDisable(true);
        CleanTextFields();
        txtFecha.setText(zonedDateTime.format(formato));
    }

    @FXML private void SavePedido() throws SQLException {
        if (productos.isEmpty()){
            Error("No has seleccionado ningun articulo :V");
        }else {
            if (!VerifyTxt(txtNumControl, txtProfesor, txtMateria , txtNombre)){
                Error("Faltan campos por rellenar");
            }else {
                ResultSet rsEdit = conexion.consultar("SELECT * FROM `pedido` WHERE `id_pedido`='"+txtID.getText()+"'");
                if (rsEdit.next()){//PA EDITAR
                    conexion.insmodelim("DELETE FROM `pedido_material` WHERE `id_pedido`='"+txtID.getText()+"'");
                    conexion.insmodelim("UPDATE `pedido` SET `nombre_persona`='"+txtNombre.getText()+"',`num_control`='"+txtNumControl.getText()+"',`profesor`='"+txtProfesor.getText()+"',`materia`='"+txtMateria.getText()+"' WHERE `id_pedido`='"+txtID.getText()+"'");
                    for (Registro registro : productos){
                            conexion.insmodelim("INSERT INTO `pedido_material`(`id_pedido`, `cb_material`, `cantidad`) VALUES ('"+txtID.getText()+"','"+registro.getCb()+"','"+registro.getCantidad()+"')");
                       }
                    Exito("Pedido actualizado correctamente");
                }else {
                    conexion.insmodelim("INSERT INTO `pedido`(`nombre_persona`, `num_control`, `estado`, `fecha`, `profesor`, `materia`) VALUES " +
                            "('"+txtNombre.getText()+"','"+txtNumControl.getText()+"','Pendiente','"+txtFecha.getText()+"','"+txtProfesor.getText()+"','"+txtMateria.getText()+"')");
                    ResultSet rsID= conexion.consultar("SELECT `id_pedido` FROM pedido ORDER BY `id_pedido` DESC LIMIT 1;");
                    if (rsID.next()){
                        int id = rsID.getInt("id_pedido");
                        for (Registro registro : productos){
                            conexion.insmodelim("INSERT INTO `pedido_material`(`id_pedido`,`cb_material`, `cantidad`) VALUES ('"+id+"','"+registro.getCb()+"','"+registro.getCantidad()+"')");
                        }
                    }

                    Exito("Pedido agregado correctamente");
                }

            }
            tabPaneVentana.getSelectionModel().select(tabSearch);
            tabSearch.setDisable(false);
            tabNew.setDisable(true);
            ActivateBtn(false,true,false,true,false,false);
            txtID.setDisable(false);
            ActualizarTabla(conexion.consultar("SELECT * FROM `pedido`"));
        }

    }
    @FXML private void EditPedido() throws SQLException {
        productos.clear();
        if (tableViewPedidos.getSelectionModel().getSelectedItem() != null){
            Pedido pedido= (Pedido) tableViewPedidos.getSelectionModel().getSelectedItem();
            ResultSet rsPedido = conexion.consultar("SELECT * FROM `pedido` WHERE `id_pedido`='"+pedido.getId_pedido()+"'");
            if (rsPedido.next()){
                tabPaneVentana.getSelectionModel().select(tabNew);
                tabSearch.setDisable(true);
                tabNew.setDisable(false);
                txtID.setText(String.valueOf(rsPedido.getInt("id_pedido")));
                txtFecha.setText(String.valueOf(rsPedido.getDate("fecha")));
                txtMateria.setText(rsPedido.getString("materia"));
                txtNumControl.setText(rsPedido.getString("num_control"));
                txtProfesor.setText(rsPedido.getString("profesor"));
                txtNombre.setText(rsPedido.getString("nombre_persona"));
                if (txtNumControl.getText().equals("N/A")){
                    checkBoxNA1.setSelected(true);
                }
                if (txtProfesor.getText().equals("N/A")){
                    checkBoxNA2.setSelected(true);
                }
                if (txtMateria.getText().equals("N/A")){
                    checkBoxNA3.setSelected(true);
                }
                ResultSet rsArticulos = conexion.consultar("SELECT `cb_material`,`cantidad` FROM `pedido_material` WHERE `id_pedido`='"+txtID.getText()+"'");
                while (rsArticulos.next()){
                    ResultSet rsArticulo = conexion.consultar("SELECT `tipo`,`valor`,`unidad_de_medida`,tipo_material.material FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material WHERE cb_material='"+rsArticulos.getLong("cb_material")+"'");
                    if (rsArticulo.next()){
                        Registro registro = new Registro(rsArticulos.getLong("cb_material"),rsArticulo.getString("material"),rsArticulo.getString("tipo"),rsArticulo.getDouble("valor"),rsArticulo.getString("unidad_de_medida"),rsArticulos.getInt("cantidad"));
                        AgregarMaterial(registro);
                    }else {
                        ResultSet rsHerramienta = conexion.consultar("SELECT tipo_material.material,`tipo` FROM `herramienta` INNER JOIN tipo_material ON herramienta.id_herramienta = tipo_material.id_material WHERE cb_herramienta='"+rsArticulos.getLong("cb_material")+"'");
                        if (rsHerramienta.next()){
                            Registro registro = new Registro(rsArticulos.getLong("cb_material"),rsHerramienta.getString("material"), rsHerramienta.getString("tipo"),rsArticulos.getInt("cantidad"));
                            AgregarMaterial(registro);
                        }
                    }
                }
            }
            ActivateBtn(true,false,true,false,false,true);

        }else {
            Error("Selecciona un registro pa");
        }
    }
    @FXML private void DeletePedido() throws SQLException {
        if (tableViewPedidos.getSelectionModel().getSelectedItem() != null){
            Pedido pedido = (Pedido) tableViewPedidos.getSelectionModel().getSelectedItem();
            if (ConfirmarBorrar("Deseas borrar este pedido?")){
                conexion.insmodelim("DELETE FROM `pedido_material` WHERE `id_pedido`='"+pedido.getId_pedido()+"'");
                conexion.insmodelim("DELETE FROM `pedido` WHERE `id_pedido`='"+pedido.getId_pedido()+"'");
                Exito("Pedido borrado exitosamente");
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
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML private void Busqueda() throws SQLException {
        String busqueda = txtBusqueda.getText();
        String criterio = "";
        if (rbID.isSelected() || rbNumControl.isSelected() || rbProfesor.isSelected()){
            if (rbID.isSelected() && !busqueda.equals("")){
                criterio="id_pedido";
            } else if (rbNumControl.isSelected() && !busqueda.equals("")) {
                criterio="num_control";
            } else if (rbProfesor.isSelected() && !busqueda.equals("")) {
                criterio="profesor";
            }
            if (!busqueda.equals("") && !criterio.equals("")){
                ActualizarTabla(conexion.consultar("SELECT * FROM `pedido` WHERE `"+criterio+"` LIKE '%"+busqueda+"%'"));
            }else {
                ActualizarTabla(conexion.consultar("SELECT * FROM `pedido`"));
            }
        }else {// PAl rato
            tableViewPedidos.getItems().clear();
            if (!busqueda.equals("")){
                ResultSet rsHerramienta = conexion.consultar("SELECT * FROM `herramienta` INNER JOIN tipo_material ON herramienta.id_herramienta = tipo_material.id_material WHERE material LIKE'%"+busqueda+"%'");
                while (rsHerramienta.next()){
                    ResultSet rsIDPedido= conexion.consultar("SELECT `id_pedido` FROM `pedido_material` WHERE `cb_material`='"+rsHerramienta.getLong("cb_herramienta")+"'");
                    while (rsIDPedido.next()){
                        ResultSet rsPedido = conexion.consultar("SELECT * FROM `pedido` WHERE `id_pedido`='"+rsIDPedido.getInt("id_pedido")+"'");
                        if (rsPedido.next()){
                            Pedido pedido = new Pedido(rsPedido.getInt("id_pedido"), rsPedido.getString("nombre_persona"),rsPedido.getString("num_control"),
                                    rsPedido.getString("estado"),rsPedido.getDate("fecha"),rsPedido.getString("profesor"),
                                    rsPedido.getString("materia"));
                            tableViewPedidos.getItems().add(pedido);
                        }
                    }
                }

                ResultSet rsArticulo = conexion.consultar("SELECT * FROM `material` INNER JOIN tipo_material ON material.id_material = tipo_material.id_material WHERE material LIKE '%"+busqueda+"%'");
                while (rsArticulo.next()){
                    ResultSet rsIDPedido= conexion.consultar("SELECT `id_pedido` FROM `pedido_material` WHERE `cb_material`='"+rsArticulo.getLong("cb_material")+"'");
                    while (rsIDPedido.next()){
                        ResultSet rsPedido = conexion.consultar("SELECT * FROM `pedido` WHERE `id_pedido`='"+rsIDPedido.getInt("id_pedido")+"'");
                        if (rsPedido.next()){
                            Pedido pedido = new Pedido(rsPedido.getInt("id_pedido"), rsPedido.getString("nombre_persona"),rsPedido.getString("num_control"),
                                    rsPedido.getString("estado"),rsPedido.getDate("fecha"),rsPedido.getString("profesor"),
                                    rsPedido.getString("materia"));
                            tableViewPedidos.getItems().add(pedido);
                        }
                    }
                }
            }else {
                ActualizarTabla(conexion.consultar("SELECT * FROM `pedido`"));
            }

        }

    }

    private void ActualizarTabla(ResultSet rsPedido) throws SQLException {
        int cont=0;
        tableViewPedidos.getItems().clear();
        while (rsPedido.next()){
            Pedido pedido = new Pedido(rsPedido.getInt("id_pedido"), rsPedido.getString("nombre_persona"),rsPedido.getString("num_control"),
                    rsPedido.getString("estado"),rsPedido.getDate("fecha"),rsPedido.getString("profesor"),
                    rsPedido.getString("materia"));
            tableViewPedidos.getItems().add(pedido);
            cont++;
        }
        lblContador.setText("Se cargaron "+cont+" pedidos");

    }

    private boolean VerifyTxt(TextField... campos){
        for (TextField campo : campos){
            if (campo.getText().equals("")){
                return false;
            }
        }
        return true;
    }
    public void AgregarMaterial(Registro a) throws SQLException {
        if (productos != null){
            boolean cantidadPlus=false;
                for (int x=0; x<productos.size();x++){
                    if (productos.get(x).getCb().equals(a.getCb())){
                        cantidadPlus=true;
                        Registro r = productos.get(x);
                        if (VerificarCantidad(r.getCb(),r.getCantidad()+1)){
                            r.setCantidad((r.getCantidad()+1));
                            if (r.getCantidad() <=0){
                                productos.remove(x);
                            }else {
                                productos.set(x,r);

                            }
                        }
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
                Registro registro = new Registro(rsArticulo.getLong("cb_material"),rsArticulo.getString("material"),rsArticulo.getString("tipo"),rsArticulo.getDouble("valor"), rsArticulo.getString("unidad_de_medida"),1);
                AgregarMaterial(registro);


                txtBusquedaID.setText("");
            }else {
                ResultSet rsHerramienta = conexion.consultar("SELECT * FROM `herramienta` INNER JOIN tipo_material ON herramienta.id_herramienta = tipo_material.id_material WHERE cb_herramienta='"+txtBusquedaID.getText()+"'");
                if (rsHerramienta.next()){
                    Registro registro = new Registro(rsHerramienta.getLong("cb_herramienta"),rsHerramienta.getString("material"),rsHerramienta.getString("tipo"), 1);
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

    public boolean VerificarCantidad(Long cb, int cantidadActual) throws SQLException {
        ResultSet rsCantidadMinA = conexion.consultar("SELECT `cantidad` FROM `material` WHERE cb_material='"+cb+"' LIMIT 1");
        ResultSet rsCantidadMinH = conexion.consultar("SELECT `cantidad` FROM `herramienta` WHERE cb_herramienta='"+cb+"' LIMIT 1");
        if (rsCantidadMinA.next()){
            if (cantidadActual > rsCantidadMinA.getInt("cantidad")){
                Error("Ya se ha alcanzado el limite de registros");
                return false;

            }
        }
        if (rsCantidadMinH.next()){
            if (cantidadActual > rsCantidadMinH.getInt("cantidad")){
                Error("Ya se ha alcanzado el limite de registros");
                return false;

            }
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



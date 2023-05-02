package com.example.controldeinventario;

import com.example.controldeinventario.Datos.Articulo;
import com.example.controldeinventario.Datos.Herramienta;
import com.example.controldeinventario.Datos.Registro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class PedidosController {
    Conexion conexion;
    public PedidosController(){


    }
    public ObservableList<Registro> productos;
    @FXML
    TableView<Registro> tableViewPedidoMaterial = new TableView<>();
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
        conexion= new Conexion();
        productos = FXCollections.observableArrayList();
        tableColumnNumero.setCellFactory(celdaNo);
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<Registro,String>("nombre"));
        tableColumnModelo.setCellValueFactory(new PropertyValueFactory<Registro,String>("tipo"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<Registro,Double>("valor"));
        tableColumnMedida.setCellValueFactory(new PropertyValueFactory<Registro,String>("unidad_medida"));
        tableColumnBtnMinus.setCellFactory(celdaMinus);
        tableColumnItemCount.setCellValueFactory(new PropertyValueFactory<Registro,Integer>("cantidad"));
        tableColumnBtnPlus.setCellFactory(celdaPlus);
        tableColumnBtnDelete.setCellFactory(celdaDelete);

        tableViewPedidoMaterial.getColumns().addAll( tableColumnNumero,tableColumnNombre,tableColumnModelo,tableColumnValor,tableColumnMedida,tableColumnBtnMinus, tableColumnItemCount,tableColumnBtnPlus,tableColumnBtnDelete);
        tableViewPedidoMaterial.setItems(productos);
    }

    @FXML private void NewPedido(){

    }
    public void AgregarMaterial(Registro a){

        if (productos != null){
        /*    if(a instanceof Articulo){
                Registro registro= new Registro(((Articulo) a).getMaterial(),((Articulo) a).getTipo(),((Articulo) a).getValor()
                ,((Articulo) a).getUnidad_medida(),((Articulo) a).getCantidad());
                productos.add(registro);
            }else if (a instanceof Herramienta){
                Registro registro = new Registro(((Herramienta) a).getHerramienta(),((Herramienta) a).getTipo()
                ,((Herramienta) a).getCantidad());
                productos.add(registro);
            }*/
            productos.add(a);
        }




    }
    @FXML private void BuscarProducto() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConsultarMaterial.fxml"));
        Parent root = fxmlLoader.load();
        ventanaSecundaria.setScene(new Scene(root));
        ventanaSecundaria.showAndWait();


    }

    @FXML public void AgregarGenerico(){
        Articulo articulo = new Articulo(Long.valueOf("21344"),"d","3d","4",
                "Diodo","Zenner","4324AACV",330.0,"Ohms",
                "De locos","Bajo",100,30);
        Registro registro = new Registro(articulo.getMaterial(),articulo.getTipo(),articulo.getValor(),
                articulo.getUnidad_medida(),1);
        Herramienta herramienta = new Herramienta(Long.valueOf("432432"),"Pala","cuadrada",
                "nanana","Medio",10,4);
        Registro registro1 = new Registro(herramienta.getHerramienta(),herramienta.getTipo(),1);
        AgregarMaterial(registro);
        AgregarMaterial(registro1);
    }

    public void CerrarVentana(){
        ventanaSecundaria.close();
    }
}



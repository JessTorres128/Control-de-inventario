package com.example.controldeinventario;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class BorrarController {
    @FXML CheckBox checkBoxHorarios, checkBoxUsuarios, checkBoxAlumnos,checkBoxRoles, checkBoxPedidos,checkBoxHerramientas, checkBoxMateriales,checkBoxTipos_Material;
    Conexion conexion;

    @FXML protected void initialize(){
        conexion = new Conexion();
    }

    @FXML private void Borrar() throws SQLException {
        if(CheckConfirmar()){
            if (ConfirmarBorrar("Deseas hacer una copia de seguridad de estos registros?")){
                GenerarController generarController = new GenerarController();
                generarController.GenerarExcel(checkBoxMateriales.isSelected(), checkBoxHerramientas.isSelected(),checkBoxPedidos.isSelected(),checkBoxAlumnos.isSelected(), checkBoxHorarios.isSelected(), checkBoxTipos_Material.isSelected(),checkBoxRoles.isSelected(),checkBoxUsuarios.isSelected());
            }else if (ConfirmarBorrar("Deseas hacer una copia de seguridad de estos registros?")){
                GenerarController generarController = new GenerarController();
                generarController.GenerarExcel(checkBoxMateriales.isSelected(), checkBoxHerramientas.isSelected(),checkBoxPedidos.isSelected(),checkBoxAlumnos.isSelected(), checkBoxHorarios.isSelected(), checkBoxTipos_Material.isSelected(),checkBoxRoles.isSelected(),checkBoxUsuarios.isSelected());
            }
            if (checkBoxUsuarios.isSelected()){
                conexion.insmodelim("DELETE FROM `usuario` WHERE 1");
                conexion.insmodelim("INSERT INTO `usuario`(`id_user`, `nombre_completo`, `sexo`, `username`, `password`, `nombre_rol`) VALUES ('1','Admin','M','admin','admin','Administrador')");
                conexion.insmodelim("INSERT INTO `usuario`(`id_user`, `nombre_completo`, `sexo`, `username`, `password`, `nombre_rol`) VALUES ('2','Invitado','M','invitado','invitado','Invitado')");
                if (checkBoxRoles.isSelected()){
                    conexion.insmodelim("DELETE FROM `tipo_usuario` WHERE 1");
                    conexion.insmodelim("INSERT INTO `tipo_usuario`(`id_rol`, `nombre_rol`, `create_material`, `update_material`, `delete_material`, `create_herramienta`, `update_herramienta`, `delete_herramienta`, `crud_pedido`, `create_t_articulo`, `update_t_articulo`, `delete_t_articulo`, `crud_roles`, `crud_empleados`, `generar_bd`, `eliminar_bd`) VALUES ('1','Administrador','1','1','1','1','1','1','1','1','1','1','1','1','1','1')");
                    conexion.insmodelim("INSERT INTO `tipo_usuario`(`id_rol`, `nombre_rol`, `create_material`, `update_material`, `delete_material`, `create_herramienta`, `update_herramienta`, `delete_herramienta`, `crud_pedido`, `create_t_articulo`, `update_t_articulo`, `delete_t_articulo`, `crud_roles`, `crud_empleados`, `generar_bd`, `eliminar_bd`) VALUES ('2','Invitado','0','0','0','0','0','0','0','0','0','0','0','0','0','0')");
                }
            }
            if (checkBoxHorarios.isSelected()){
                conexion.insmodelim("DELETE FROM `materia` WHERE 1");
                if (checkBoxAlumnos.isSelected()){
                    conexion.insmodelim("DELETE FROM `alumnos` WHERE 1");
                }
            }
            if (checkBoxMateriales.isSelected()){
                conexion.insmodelim("DELETE FROM `material` WHERE 1");
                if (checkBoxTipos_Material.isSelected()){
                    conexion.insmodelim("DELETE FROM `tipo_material` WHERE `tipo_material`='Material Consumible' or `tipo_material`='Material Fijo'");
                }
            }

            if (checkBoxHerramientas.isSelected()){
                conexion.insmodelim("DELETE FROM `herramienta` WHERE 1");
                if (checkBoxTipos_Material.isSelected()){
                    conexion.insmodelim("DELETE FROM `tipo_material` WHERE `tipo_material`='Herramienta'");
                }
            }
            if (checkBoxPedidos.isSelected()){
                ResultSet rsPendientes = conexion.consultar("SELECT * FROM `pedido_material` WHERE `estado`='Pendiente'");
                while (rsPendientes.next()){
                   // System.out.println("cantidad pendiente: "+rsPendientes.getInt("cantidad"));
                    ResultSet rsMat = conexion.consultar("SELECT * FROM `material` WHERE `cb_material`= ?",rsPendientes.getString("cb_material"));
                    ResultSet rsHerramienta = conexion.consultar("SELECT * FROM `herramienta` WHERE `cb_herramienta`= ?", rsPendientes.getString("cb_material"));
                    if (rsMat.next()){
                       // System.out.println("cantidad material: "+rsMat.getInt("cantidad"));
                        conexion.insmodelim("UPDATE `material` SET `cantidad`= ? WHERE `cb_material`= ?", String.valueOf((rsPendientes.getInt("cantidad")+rsMat.getInt("cantidad"))), rsMat.getString("cb_material"));
                    } else if (rsHerramienta.next()) {
                       // System.out.println("cantidad herramienta: "+rsHerramienta.getInt("cantidad"));
                        conexion.insmodelim("UPDATE `herramienta` SET `cantidad`= ? WHERE `cb_herramienta`= ? ", String.valueOf((rsHerramienta.getInt("cantidad")+rsPendientes.getInt("cantidad"))), rsHerramienta.getString("cb_herramienta"));
                    }
                }
                conexion.insmodelim("DELETE FROM `pedido` WHERE 1");
                conexion.insmodelim("DELETE FROM `pedido_material` WHERE 1");
            }
        }else {
            Error("No se ha seleccionado ningun campo");
        }

    }

    public boolean ConfirmarBorrar(String mensaje) {

        AtomicBoolean confirmar = new AtomicBoolean(false);
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Confirmar acción");

        Label lblmsg = new Label(mensaje);
        Button btnConfirmar = new Button("Si");
        Button btnCancelar = new Button("No");

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

        Scene dialogScene = new Scene(vbox, 350, 120);
        dialog.setScene(dialogScene);
        dialog.showAndWait();

        return confirmar.get();
    }
    @FXML private void CheckBoxChange(){
        if(checkBoxRoles.isSelected()){
            checkBoxUsuarios.setSelected(true);
        }
        if (checkBoxTipos_Material.isSelected()){
            checkBoxMateriales.setSelected(true);
            checkBoxHerramientas.setSelected(true);
        }
        if (checkBoxAlumnos.isSelected()){
            checkBoxHorarios.setSelected(true);
        }
    }
    private boolean CheckConfirmar(){
        return checkBoxAlumnos.isSelected() || checkBoxHerramientas.isSelected() || checkBoxHorarios.isSelected() || checkBoxMateriales.isSelected() || checkBoxPedidos.isSelected() || checkBoxRoles.isSelected() || checkBoxTipos_Material.isSelected() || checkBoxUsuarios.isSelected();
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
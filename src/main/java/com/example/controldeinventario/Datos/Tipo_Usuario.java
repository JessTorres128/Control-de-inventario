package com.example.controldeinventario.Datos;

public class Tipo_Usuario {
    public int id_rol ;
    public String nombre_rol;
    public boolean create_material;
    public boolean update_material;
    public boolean delete_material;
    public boolean create_herramienta;
    public boolean update_herramienta;
    public boolean delete_herramienta;
    public boolean crud_pedido;
    public boolean create_t_material;
    public boolean update_t_material;
    public boolean delete_t_material;
    public boolean create_t_herramienta;
    public boolean update_t_herramienta;
    public boolean delete_t_herramienta;
    public boolean crud_roles;
    public boolean crud_empleados;
    public boolean restaurar_bd;
    public boolean respaldar_bd;

    public Tipo_Usuario(int id_rol, String nombre_rol, boolean create_material, boolean update_material, boolean delete_material, boolean create_herramienta, boolean update_herramienta, boolean delete_herramienta, boolean crud_pedido, boolean create_t_material, boolean update_t_material, boolean delete_t_material, boolean create_t_herramienta, boolean update_t_herramienta, boolean delete_t_herramienta, boolean crud_roles, boolean crud_empleados, boolean restaurar_bd, boolean respaldar_bd) {
        this.id_rol = id_rol;
        this.nombre_rol = nombre_rol;
        this.create_material = create_material;
        this.update_material = update_material;
        this.delete_material = delete_material;
        this.create_herramienta = create_herramienta;
        this.update_herramienta = update_herramienta;
        this.delete_herramienta = delete_herramienta;
        this.crud_pedido = crud_pedido;
        this.create_t_material = create_t_material;
        this.update_t_material = update_t_material;
        this.delete_t_material = delete_t_material;
        this.create_t_herramienta = create_t_herramienta;
        this.update_t_herramienta = update_t_herramienta;
        this.delete_t_herramienta = delete_t_herramienta;
        this.crud_roles = crud_roles;
        this.crud_empleados = crud_empleados;
        this.restaurar_bd = restaurar_bd;
        this.respaldar_bd = respaldar_bd;
    }

    public int getId_rol() {return id_rol;}
    public void setId_rol(int id_rol) {this.id_rol = id_rol;}
    public String getNombre_rol() {return nombre_rol;}
    public void setNombre_rol(String nombre_rol) {this.nombre_rol = nombre_rol;}
    public boolean isCreate_material() {return create_material;}
    public void setCreate_material(boolean create_material) {this.create_material = create_material;}
    public boolean isUpdate_material() {return update_material;}
    public void setUpdate_material(boolean update_material) {this.update_material = update_material;}
    public boolean isDelete_material() {return delete_material;}
    public void setDelete_material(boolean delete_material) {this.delete_material = delete_material;}
    public boolean isCreate_herramienta() {return create_herramienta;}
    public void setCreate_herramienta(boolean create_herramienta) {this.create_herramienta = create_herramienta;}
    public boolean isUpdate_herramienta() {return update_herramienta;}
    public void setUpdate_herramienta(boolean update_herramienta) {this.update_herramienta = update_herramienta;}
    public boolean isDelete_herramienta() {return delete_herramienta;}
    public void setDelete_herramienta(boolean delete_herramienta) {this.delete_herramienta = delete_herramienta;}
    public boolean isCrud_pedido() {return crud_pedido;}
    public void setCrud_pedido(boolean crud_pedido) {this.crud_pedido = crud_pedido;}
    public boolean isCreate_t_material() {return create_t_material;}
    public void setCreate_t_material(boolean create_t_material) {this.create_t_material = create_t_material;}
    public boolean isUpdate_t_material() {return update_t_material;}
    public void setUpdate_t_material(boolean update_t_material) {this.update_t_material = update_t_material;}
    public boolean isDelete_t_material() {return delete_t_material;}
    public void setDelete_t_material(boolean delete_t_material) {this.delete_t_material = delete_t_material;}
    public boolean isCreate_t_herramienta() {return create_t_herramienta;}
    public void setCreate_t_herramienta(boolean create_t_herramienta) {this.create_t_herramienta = create_t_herramienta;}
    public boolean isUpdate_t_herramienta() {return update_t_herramienta;}
    public void setUpdate_t_herramienta(boolean update_t_herramienta) {this.update_t_herramienta = update_t_herramienta;}
    public boolean isDelete_t_herramienta() {return delete_t_herramienta;}
    public void setDelete_t_herramienta(boolean delete_t_herramienta) {this.delete_t_herramienta = delete_t_herramienta;}
    public boolean isCrud_roles() {return crud_roles;}
    public void setCrud_roles(boolean crud_roles) {this.crud_roles = crud_roles;}
    public boolean isCrud_empleados() {return crud_empleados;}
    public void setCrud_empleados(boolean crud_empleados) {this.crud_empleados = crud_empleados;}
    public boolean isRestaurar_bd() {return restaurar_bd;}
    public void setRestaurar_bd(boolean restaurar_bd) {this.restaurar_bd = restaurar_bd;}
    public boolean isRespaldar_bd() {return respaldar_bd;}
    public void setRespaldar_bd(boolean respaldar_bd) {this.respaldar_bd = respaldar_bd;}
}
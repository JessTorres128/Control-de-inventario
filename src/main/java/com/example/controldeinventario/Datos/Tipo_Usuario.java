package com.example.controldeinventario.Datos;

public class Tipo_Usuario {
    public int id_rol ;
    public String nombre_rol;
    public String create_material;
    public String update_material;
    public String delete_material;
    public String create_herramienta;
    public String update_herramienta;
    public String delete_herramienta;
    public String crud_pedido;
    public String create_t_material;
    public String update_t_material;
    public String delete_t_material;
    public String create_t_herramienta;
    public String update_t_herramienta;
    public String delete_t_herramienta;
    public String crud_roles;
    public String crud_empleados;
    public String restaurar_bd;
    public String respaldar_bd;

    public Tipo_Usuario(int id_rol, String nombre_rol, String create_material, String update_material, String delete_material, String create_herramienta, String update_herramienta, String delete_herramienta, String crud_pedido, String create_t_material, String update_t_material, String delete_t_material, String create_t_herramienta, String update_t_herramienta, String delete_t_herramienta, String crud_roles, String crud_empleados, String restaurar_bd, String respaldar_bd) {
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

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public String getCreate_material() {
        return create_material;
    }

    public void setCreate_material(String create_material) {
        this.create_material = create_material;
    }

    public String getUpdate_material() {
        return update_material;
    }

    public void setUpdate_material(String update_material) {
        this.update_material = update_material;
    }

    public String getDelete_material() {
        return delete_material;
    }

    public void setDelete_material(String delete_material) {
        this.delete_material = delete_material;
    }

    public String getCreate_herramienta() {
        return create_herramienta;
    }

    public void setCreate_herramienta(String create_herramienta) {
        this.create_herramienta = create_herramienta;
    }

    public String getUpdate_herramienta() {
        return update_herramienta;
    }

    public void setUpdate_herramienta(String update_herramienta) {
        this.update_herramienta = update_herramienta;
    }

    public String getDelete_herramienta() {
        return delete_herramienta;
    }

    public void setDelete_herramienta(String delete_herramienta) {
        this.delete_herramienta = delete_herramienta;
    }

    public String getCrud_pedido() {
        return crud_pedido;
    }

    public void setCrud_pedido(String crud_pedido) {
        this.crud_pedido = crud_pedido;
    }

    public String getCreate_t_material() {
        return create_t_material;
    }

    public void setCreate_t_material(String create_t_material) {
        this.create_t_material = create_t_material;
    }

    public String getUpdate_t_material() {
        return update_t_material;
    }

    public void setUpdate_t_material(String update_t_material) {
        this.update_t_material = update_t_material;
    }

    public String getDelete_t_material() {
        return delete_t_material;
    }

    public void setDelete_t_material(String delete_t_material) {
        this.delete_t_material = delete_t_material;
    }

    public String getCreate_t_herramienta() {
        return create_t_herramienta;
    }

    public void setCreate_t_herramienta(String create_t_herramienta) {
        this.create_t_herramienta = create_t_herramienta;
    }

    public String getUpdate_t_herramienta() {
        return update_t_herramienta;
    }

    public void setUpdate_t_herramienta(String update_t_herramienta) {
        this.update_t_herramienta = update_t_herramienta;
    }

    public String getDelete_t_herramienta() {
        return delete_t_herramienta;
    }

    public void setDelete_t_herramienta(String delete_t_herramienta) {
        this.delete_t_herramienta = delete_t_herramienta;
    }

    public String getCrud_roles() {
        return crud_roles;
    }

    public void setCrud_roles(String crud_roles) {
        this.crud_roles = crud_roles;
    }

    public String getCrud_empleados() {
        return crud_empleados;
    }

    public void setCrud_empleados(String crud_empleados) {
        this.crud_empleados = crud_empleados;
    }

    public String getRestaurar_bd() {
        return restaurar_bd;
    }

    public void setRestaurar_bd(String restaurar_bd) {
        this.restaurar_bd = restaurar_bd;
    }

    public String getRespaldar_bd() {
        return respaldar_bd;
    }

    public void setRespaldar_bd(String respaldar_bd) {
        this.respaldar_bd = respaldar_bd;
    }
}
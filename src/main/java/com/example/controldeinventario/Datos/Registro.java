package com.example.controldeinventario.Datos;

public class Registro {
    private String nombre;
    private String tipo;
    private Double valor;
    private String unidad_medida;
    private int cantidad;

    public Registro(String nombre, String tipo, Double valor, String unidad_medida, int cantidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        this.unidad_medida = unidad_medida;
        this.cantidad = cantidad;
    }

    public Registro(String nombre, String tipo, int cantidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

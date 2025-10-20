package com.example.model;

public class Article {
    private String nombre;
    private int cantidad;
    private double precio;
    private double descuento;

    public Article(){

    }
    
    public Article(String nombre,int cantidad, double precio, double descuento){
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public int getCantidad(){
        return cantidad;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    public double getPrecioTotal(){
        return precio*cantidad;
    }

    public double getPrecioPorUnidad(){
        return precio;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }
    public double setGrossAmount(){
        return cantidad;
    }
    public double getDescuento(){
        return descuento;
    }
    public void setDescuento(double descuento){
        this.descuento = descuento;
    }
    public double getPrecioConDescuento(){
        return (getPrecioPorUnidad() - (getPrecioPorUnidad() * descuento))*cantidad;
    }

    @Override
    public String toString(){
        return nombre + "("+ precio +"€, descuentos:" + (descuento * 100)+"%)";
    }
}

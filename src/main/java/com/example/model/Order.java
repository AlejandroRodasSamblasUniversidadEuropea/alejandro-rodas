package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String idPedido;
    private List<Article> articulos;

    public Order(){
        
    }


public Order(String idPedido){
    this.idPedido = idPedido;
    this.articulos = new ArrayList<>();
}

public String getidPedido(){
    return idPedido;
}
public void setidPedido(String idPedido){
    this.idPedido = idPedido;
}
public List<Article> getArticulos(){
    return articulos;
}
public void setArticulos(List<Article> articulos){
    this.articulos = articulos;
}


public void addArticulo(Article articulo){
    articulos.add(articulo);
}


public double getGrossTotal(){
    double total = 0;
    for (Article articulo : articulos){
        total += articulo.getPrecioPorUnidad()*articulo.getCantidad();
    }
    return total;
}

public double getDiscountedTotal(){
    double total = 0;
    for (Article articulo: articulos) {
        total = total + articulo.getPrecioConDescuento();
    }
    return total;
}

@Override
    public String toString() {
        return "Order{" +
                "idPedido='" + idPedido + '\'' +
                ", articulos=" + articulos +
                ", grossTotal=" + getGrossTotal() +
                ", discountedTotal=" + getDiscountedTotal() +
                '}';
    }
}

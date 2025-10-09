package com.example.model;


public class Main {
    public static void main(String[] args) {

        System.out.println("Pantalla de Pedidos");
        System.out.println("===================");


        Order order1 = new Order("PED-001");
        order1.addArticulo(new Article("Laptop",2, 1200.0, 0.15));
        order1.addArticulo(new Article("Teléfono",2, 600.0, 0.10));
        order1.addArticulo(new Article("Auriculares",1, 50.0, 0.0));

        System.out.println("El id del order1 es: ");
        System.out.println(order1.getidPedido());
        System.out.println("El precio sin descuento es de: ");
        System.out.println(order1.getGrossTotal());
        System.out.println("Y el precio final es de: ");
        System.out.println(order1.getDiscountedTotal());

        Order order2 = new Order("PED-002");
        order2.addArticulo(new Article("Laptop",5, 1200.0, 0.15));
        order2.addArticulo(new Article("Teléfono",1, 600.0, 0.10));
        order2.addArticulo(new Article("Auriculares",0, 50.0, 0.0));

        System.out.println("El id del order2 es: ");
        System.out.println(order2.getidPedido());
        System.out.println("El precio sin descuento es de: ");
        System.out.println(order2.getGrossTotal());
        System.out.println("Y el precio final es de: ");
        System.out.println(order2.getDiscountedTotal());

    }
}
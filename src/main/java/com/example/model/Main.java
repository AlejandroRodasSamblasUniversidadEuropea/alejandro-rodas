package com.example.model;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("Iniciando la carga de los pedidos...");

        try {
            FileReader reader = new FileReader("src/main/resources/orders.json");

            Gson gson = new Gson();

            Type listType = new TypeToken<List<Order>>() {}.getType();

            List<Order> pedidos = gson.fromJson(reader, listType);

            for (Order pedido : pedidos) {
                System.out.println("=========================================");
                System.out.println("ID del pedido: " + pedido.getidPedido());
                System.out.println("Artículos del pedido:");
                System.out.println("-----------------------------------------");

                for (Article articulo : pedido.getArticulos()) {
                    System.out.println(articulo.getNombre());
                    System.out.println("   - Cantidad: " + articulo.getCantidad());
                    System.out.println("   - Precio unitario: " + articulo.getPrecioPorUnidad() + " euros");
                    System.out.println("   - Descuento: " + articulo.getDescuento() + "%");
                    System.out.println("   - Precio total con descuento: " + articulo.getPrecioConDescuento() + " euros");
                    System.out.println();
                }

                System.out.println("Total sin descuento: " + pedido.getGrossTotal() + " euros");
                System.out.println("Total con descuento: " + pedido.getDiscountedTotal() + " euros");
                System.out.println("=========================================\n");

                // También lo registramos en los logs
                log.debug("Pedido cargado correctamente: {}", pedido.getidPedido());
            }

            reader.close(); // cerramos el archivo al terminar
            log.info("Archivo JSON leído correctamente.");

        } catch (Exception e) {
            log.error("Error al leer el archivo JSON");
        }
    }
}

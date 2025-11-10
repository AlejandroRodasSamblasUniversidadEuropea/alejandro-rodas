package com.example.model;

import com.example.controller.OrderController;
import com.example.view.OrderView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("Iniciando la carga de los pedidos...");

        try {

            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("orders.json");
            if (inputStream == null) {
                log.error("No se encontró el archivo orders.json en resources");
                return;
            }

            Reader reader = new InputStreamReader(inputStream, java.nio.charset.StandardCharsets.UTF_8);

            Gson gson = new Gson();
            Type listType = new TypeToken<List<Order>>() {}.getType();
            List<Order> pedidos = gson.fromJson(reader, listType);

            for (Order pedido : pedidos) {
                System.out.println("=========================================");
                System.out.println("ID del pedido: " + pedido.getidPedido());
                System.out.println("Artículos del pedido: ");
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

                log.debug("Pedido cargado correctamente: {}", pedido.getidPedido());
            }

            reader.close();
            log.info("Archivo JSON leído correctamente.");

            // Initialize MVC
            OrderView view = new OrderView();
            new OrderController(view, pedidos);

        } catch (Exception e) {
            log.error("Error al leer el archivo JSON", e);
        }
    }
}

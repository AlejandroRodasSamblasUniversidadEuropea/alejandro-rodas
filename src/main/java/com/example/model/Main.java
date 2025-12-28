package com.example.model;

import com.example.controller.OrderController;
import com.example.view.OrderView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final String JSON_FILE = "data/orders.json";

    public static void main(String[] args) {
        log.info("Iniciando la aplicación de gestión de pedidos...");

        try {
            // Crear carpeta y archivo si no existe
            File file = new File(JSON_FILE);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                log.info("Archivo orders.json creado en {}", file.getAbsolutePath());
            }

            // Inicializar repositorio y cargar pedidos
            OrderRepository repository = new OrderRepository(JSON_FILE);
            List<Order> pedidos = repository.loadOrders();
            log.info("Pedidos cargados: {}", pedidos.size());

            // Inicializar interfaz MVC
            OrderView view = new OrderView();
            new OrderController(view, pedidos, repository);

        } catch (Exception e) {
            log.error("Error al iniciar la aplicación", e);
        }
    }
}

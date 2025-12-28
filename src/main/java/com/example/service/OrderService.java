package com.example.service;

import com.example.model.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private List<Order> orders = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    // Ruta correcta y persistente
    private final Path jsonPath = Path.of("data/orders.json");

    public OrderService() {
        loadOrders();
    }

    private void loadOrders() {
        try {
            if (Files.exists(jsonPath)) {
                orders = mapper.readValue(
                        Files.newBufferedReader(jsonPath),
                        new TypeReference<List<Order>>() {}
                );
            } else {
                orders = new ArrayList<>();
            }
        } catch (Exception e) {
            orders = new ArrayList<>();
            System.out.println("Error cargando pedidos: " + e.getMessage());
        }
    }

    private void saveOrders() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(jsonPath.toFile(), orders);
        } catch (Exception e) {
            System.out.println("Error guardando pedidos: " + e.getMessage());
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<String> getOrderIds() {
        return orders.stream()
                .map(Order::getidPedido)
                .toList();
    }

    public Optional<Order> findById(String id) {
        return orders.stream()
                .filter(o -> o.getidPedido().equalsIgnoreCase(id))
                .findFirst();
    }

    public boolean addOrder(Order order) {
        if (findById(order.getidPedido()).isPresent()) {
            return false;
        }
        orders.add(order);
        saveOrders();
        return true;
    }

    public boolean deleteOrder(String id) {
        boolean removed = orders.removeIf(
                o -> o.getidPedido().equalsIgnoreCase(id)
        );
        if (removed) {
            saveOrders();
        }
        return removed;
    }
}

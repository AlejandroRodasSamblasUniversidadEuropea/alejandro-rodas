package com.example.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private String jsonPath;
    private List<Order> orders;

    public OrderRepository(String jsonPath) {
        this.jsonPath = jsonPath;
        this.orders = new ArrayList<>();
    }

    public List<Order> loadOrders() {
        File file = new File(jsonPath);
        if (!file.exists()) saveOrders(new ArrayList<>());

        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Order>>() {}.getType();
            orders = gson.fromJson(reader, listType);
            if (orders == null) orders = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            orders = new ArrayList<>();
        }
        return orders;
    }

    public void saveOrders(List<Order> ordersToSave) {
        this.orders = ordersToSave;
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(jsonPath), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            gson.toJson(ordersToSave, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteOrderById(String idPedido) {
        boolean removed = orders.removeIf(o -> o.getidPedido().equalsIgnoreCase(idPedido));
        if (removed) saveOrders(orders);
        return removed;
    }

    public List<Order> getOrders() {
        return orders;
    }
}

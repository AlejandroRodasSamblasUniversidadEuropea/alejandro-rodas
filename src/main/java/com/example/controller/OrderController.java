package com.example.controller;

import com.example.model.Order;
import com.example.model.OrderRepository;
import com.example.service.ExchangeService;
import com.example.view.OrderView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import java.util.Optional;

public class OrderController {

    private OrderView view;
    private List<Order> orders;
    private OrderRepository repository;

    public OrderController(OrderView view, List<Order> orders, OrderRepository repository) {
        this.view = view;
        this.orders = orders;
        this.repository = repository;

        this.view.getSearchButton().addActionListener(e -> searchOrder());
        this.view.getAddButton().addActionListener(e -> addOrder());
        this.view.getDeleteButton().addActionListener(e -> deleteOrder());
        this.view.getEditButton().addActionListener(e -> editOrder());

        updateOrderList();

        // Selección en JList
        this.view.getOrderList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String selectedId = view.getOrderList().getSelectedValue();
                if (selectedId != null) {
                    Optional<Order> o = orders.stream()
                            .filter(order -> order.getidPedido().equalsIgnoreCase(selectedId))
                            .findFirst();
                    o.ifPresent(order -> view.displayOrder(order, ExchangeService.getEurUsdRate()));
                }
            }
        });
    }

    private void updateOrderList() {
        String[] ids = orders.stream().map(Order::getidPedido).toArray(String[]::new);
        view.setOrderListData(ids);
    }

    private void searchOrder() {
        String id = view.getSearchId();
        if (id == null || id.isEmpty()) {
            view.displayOrder(null, 0);
            return;
        }
        Optional<Order> found = orders.stream()
                .filter(o -> o.getidPedido().equalsIgnoreCase(id))
                .findFirst();
        found.ifPresentOrElse(
                order -> view.displayOrder(order, ExchangeService.getEurUsdRate()),
                () -> view.displayOrder(null, 0)
        );
    }

    private void addOrder() {
        Order newOrder = view.showAddOrderDialog();
        if (newOrder != null) {
            boolean exists = orders.stream()
                    .anyMatch(o -> o.getidPedido().equalsIgnoreCase(newOrder.getidPedido()));
            if (exists) {
                view.showMessage("El ID ya existe. Use otro ID.");
                return;
            }
            orders.add(newOrder);
            repository.saveOrders(orders);
            updateOrderList();
            view.showMessage("Pedido creado correctamente.");
        }
    }

    private void deleteOrder() {
        String id = view.getSearchId();
        if (id == null || id.isEmpty()) {
            view.showMessage("Ingrese el ID del pedido a borrar.");
            return;
        }
        boolean removed = repository.deleteOrderById(id);
        if (removed) {
            orders.removeIf(o -> o.getidPedido().equalsIgnoreCase(id));
            updateOrderList();
            view.showMessage("Pedido eliminado correctamente.");
        } else {
            view.showMessage("No se encontró el pedido con ese ID.");
        }
    }

    private void editOrder() {
        String selectedId = view.getOrderList().getSelectedValue();
        if (selectedId == null) {
            view.showMessage("Seleccione un pedido de la lista para editar.");
            return;
        }

        Optional<Order> o = orders.stream()
                .filter(order -> order.getidPedido().equalsIgnoreCase(selectedId))
                .findFirst();

        if (o.isPresent()) {
            Order orderToEdit = o.get();
            view.showEditOrderDialog(orderToEdit);
            repository.saveOrders(orders);
            view.displayOrder(orderToEdit, ExchangeService.getEurUsdRate());
        } else {
            view.showMessage("Pedido no encontrado.");
        }
    }
}

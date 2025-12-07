package com.example.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.Order;
import com.example.model.Searcher;
import com.example.service.ExchangeService;
import com.example.view.OrderView;

public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderView view;
    private List<Order> orders;
    private Searcher searcher;

    public OrderController(OrderView view, List<Order> orders) {
        this.view = view;
        this.orders = orders;
        this.searcher = new Searcher();

        this.view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchOrder();
            }
        });
    }

    private void searchOrder() {
        String id = view.getSearchId();

        if (id == null || id.isEmpty()) {
            view.displayOrder(null, 0);
            return;
        }

        log.info("Buscando pedido con ID: {}", id);

        Optional<Order> found = orders.stream()
                .filter(o -> o.getidPedido().equalsIgnoreCase(id))
                .findFirst();

        if (found.isPresent()) {
            log.info("Pedido encontrado: {}", found.get().getidPedido());

            double rate = ExchangeService.getEurUsdRate();

            view.displayOrder(found.get(), rate);

        } else {
            log.warn("Pedido no encontrado: {}", id);
            view.displayOrder(null, 0);
        }
    }
}

package com.example.view;

import javax.swing.*;
import com.example.model.Order;
import java.awt.*;

public class OrderView extends JFrame {
    private JTextField searchField = new JTextField(10);
    private JButton searchButton = new JButton("Search");
    private JTextArea resultArea = new JTextArea(10, 40);

    public OrderView() {
        setTitle("Order Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // ICONO DE LA APLICACIÓN
        Image icon = new ImageIcon(
                getClass().getResource("/images/app.png")
        ).getImage();
        setIconImage(icon);

        JLabel idPedidoLabel = new JLabel("ID del pedido:");
        add(idPedidoLabel);

        add(searchField);
        add(searchButton);

        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane);

        pack();
        setVisible(true);
    }

    public String getSearchId() {
        return searchField.getText().trim();
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void displayOrder(Order order, double rate) {
        resultArea.setText("");

        if (order == null) {
            resultArea.setText("Order not found.");
            return;
        }

        resultArea.append("Order ID: " + order.getidPedido() + "\n");
        resultArea.append("Artículos del pedido:\n");
        resultArea.append("-----------------------------------------\n");

        order.getArticulos().forEach(a -> {
            resultArea.append("Nombre: " + a.getNombre() + "\n");
            resultArea.append("Cantidad: " + a.getCantidad() + "\n");
            resultArea.append("Precio unitario: " + a.getPrecioPorUnidad() + "€\n");
            resultArea.append("Descuento: " + a.getDescuento() + "%\n");
            resultArea.append("Precio total con descuento: " + a.getPrecioConDescuento() + "€\n\n");
        });

        resultArea.append("=========================================\n");
        resultArea.append("Total sin descuento : " + order.getGrossTotal() + "€\n");
        resultArea.append("Total con descuento : " + order.getDiscountedTotal() + "€\n");

        if (rate > 0) {
            double usd = order.getDiscountedTotal() * rate;
            resultArea.append("Total en dólares   : " + usd + " USD\n");
            resultArea.append("(Tipo de cambio EUR → USD: " + rate + ")\n");
        }
    }
}

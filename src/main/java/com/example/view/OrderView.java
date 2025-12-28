package com.example.view;

import com.example.model.Article;
import com.example.model.Order;

import javax.swing.*;
import java.awt.*;

public class OrderView extends JFrame {

    private JTextField searchField = new JTextField(10);
    private JButton searchButton = new JButton("Search");
    private JButton addButton = new JButton("Add Order");
    private JButton deleteButton = new JButton("Delete Order");
    private JButton editButton = new JButton("Edit Order");
    private JTextArea resultArea = new JTextArea(15, 50);
    private JList<String> orderList = new JList<>();

    public OrderView() {
        setTitle("Order Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // ICONO
        Image icon = new ImageIcon(getClass().getResource("/images/app.png")).getImage();
        setIconImage(icon);

        add(new JLabel("ID del pedido:"));
        add(searchField);
        add(searchButton);
        add(addButton);
        add(deleteButton);
        add(editButton);

        orderList.setVisibleRowCount(5);
        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(orderList));

        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        pack();
        setVisible(true);
    }

    public String getSearchId() { return searchField.getText().trim(); }
    public JButton getSearchButton() { return searchButton; }
    public JButton getAddButton() { return addButton; }
    public JButton getDeleteButton() { return deleteButton; }
    public JButton getEditButton() { return editButton; }
    public JList<String> getOrderList() { return orderList; }

    public void setOrderListData(String[] ids) {
        orderList.setListData(ids);
    }

    public void displayOrder(Order order, double rate) {
        resultArea.setText("");
        if (order == null) {
            resultArea.setText("Order not found.");
            return;
        }
        resultArea.append("Order ID: " + order.getidPedido() + "\n");
        resultArea.append("Artículos del pedido:\n-----------------------------------------\n");
        for (Article a : order.getArticulos()) {
            resultArea.append("Nombre: " + a.getNombre() + "\n");
            resultArea.append("Cantidad: " + a.getCantidad() + "\n");
            resultArea.append("Precio unitario: " + a.getPrecioPorUnidad() + "€\n");
            resultArea.append("Descuento: " + a.getDescuento() * 100 + "%\n");
            resultArea.append("Precio total con descuento: " + a.getPrecioConDescuento() + "€\n\n");
        }
        resultArea.append("=========================================\n");
        resultArea.append("Total sin descuento : " + order.getGrossTotal() + "€\n");
        resultArea.append("Total con descuento : " + order.getDiscountedTotal() + "€\n");
        if (rate > 0) {
            double usd = order.getDiscountedTotal() * rate;
            resultArea.append("Total en dólares : " + usd + " USD\n");
            resultArea.append("(Tipo de cambio EUR → USD: " + rate + ")\n");
        }
    }

    public Order showAddOrderDialog() {
        String id = JOptionPane.showInputDialog(this, "Ingrese ID del pedido:");
        if (id == null || id.trim().isEmpty()) return null;

        Order newOrder = new Order(id.trim());
        boolean addingArticles = true;

        while (addingArticles) {
            String name = JOptionPane.showInputDialog(this, "Nombre del artículo:");
            if (name == null || name.trim().isEmpty()) break;

            int qty = Integer.parseInt(JOptionPane.showInputDialog(this, "Cantidad:"));
            double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Precio unitario:"));
            double discount = Double.parseDouble(JOptionPane.showInputDialog(this, "Descuento (0-1):"));

            newOrder.addArticulo(new Article(name, qty, price, discount));

            int option = JOptionPane.showConfirmDialog(this, "Agregar otro artículo?", "Agregar", JOptionPane.YES_NO_OPTION);
            if (option != JOptionPane.YES_OPTION) addingArticles = false;
        }

        return newOrder;
    }

    public void showEditOrderDialog(Order order) {
        if (order == null) return;

        for (Article a : order.getArticulos()) {
            int qty = Integer.parseInt(JOptionPane.showInputDialog(this, "Cantidad de " + a.getNombre() + ":", a.getCantidad()));
            double discount = Double.parseDouble(JOptionPane.showInputDialog(this, "Descuento de " + a.getNombre() + " (0-1):", a.getDescuento()));
            a.setCantidad(qty);
            a.setDescuento(discount);
        }
        showMessage("Pedido actualizado correctamente.");
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.OrderItem;
import util.DBUtil;

public class OrderDAO {
    
    // ユーザーの購入履歴を取得（注文と注文商品の詳細）
    public List<Order> getUserOrders(int userId) {
        List<Order> orders = new ArrayList<>();
        String orderSql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";
        String itemSql = "SELECT oi.product_id, p.name, oi.quantity, oi.price " +
                         "FROM order_items oi " +
                         "JOIN products p ON oi.product_id = p.id " +
                         "WHERE oi.order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement orderStmt = conn.prepareStatement(orderSql)) {
            orderStmt.setInt(1, userId);
            ResultSet orderRs = orderStmt.executeQuery();

            while (orderRs.next()) {
                int orderId = orderRs.getInt("id");
                Order order = new Order(orderId, userId, orderRs.getTimestamp("order_date"), orderRs.getDouble("total_price"));
                
                // 注文ごとの商品情報を取得
                List<OrderItem> orderItems = new ArrayList<>();
                try (PreparedStatement itemStmt = conn.prepareStatement(itemSql)) {
                    itemStmt.setInt(1, orderId);
                    ResultSet itemRs = itemStmt.executeQuery();
                    
                    while (itemRs.next()) {
                        orderItems.add(new OrderItem(
                            itemRs.getInt("product_id"),
                            itemRs.getString("name"),
                            itemRs.getInt("quantity"),
                            itemRs.getDouble("price")
                        ));
                    }
                }
                order.setOrderItems(orderItems);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public int saveOrder(int userId, double totalPrice, List<OrderItem> orderItems) {
        String orderSql = "INSERT INTO orders (user_id, total_price) VALUES (?, ?)";
        String itemSql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        int orderId = -1;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement orderStmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {

            // `orders` テーブルに注文データを挿入
            orderStmt.setInt(1, userId);
            orderStmt.setDouble(2, totalPrice);
            orderStmt.executeUpdate();

            // 自動生成された注文IDを取得
            ResultSet rs = orderStmt.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            // `order_items` テーブルに各商品の注文情報を挿入
            try (PreparedStatement itemStmt = conn.prepareStatement(itemSql)) {
                for (OrderItem item : orderItems) {
                    itemStmt.setInt(1, orderId);
                    itemStmt.setInt(2, item.getProductId());
                    itemStmt.setInt(3, item.getQuantity());
                    itemStmt.setDouble(4, item.getPrice());
                    itemStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }
}


package model;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    private int id;
    private int userId;
    private Timestamp orderDate;
    private double totalPrice;
    private List<OrderItem> orderItems;

    public Order(int id, int userId, Timestamp orderDate, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public Timestamp getOrderDate() { return orderDate; }
    public double getTotalPrice() { return totalPrice; }
    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}

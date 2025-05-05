<%@ page import="java.util.List, model.Order, model.OrderItem, java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>購入履歴</title>
</head>
<jsp:include page="commonHeader.jsp" />
<body>
    <h1>購入履歴</h1>
    <a href="products">商品一覧へ戻る</a>

    <%
        List<Order> orders = (List<Order>) request.getAttribute("orders");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  // 日時フォーマットを設定
        if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) {
    %>
    <div>
        <p>注文ID: <%= order.getId() %></p>
        <p>注文日: <%= sdf.format(order.getOrderDate()) %></p>  <!-- フォーマット適用 -->
     
        <table border="1">
            <tr>
                <th>商品名</th>
                <th>価格 (円)</th>
                <th>数量</th>
                <th>小計 (円)</th>
            </tr>
            <%
                List<OrderItem> orderItems = order.getOrderItems();
                double totalAmount = 0;
                for (OrderItem item : orderItems) {
                    double itemTotal = item.getPrice() * item.getQuantity();
                    totalAmount += itemTotal;
            %>
            <tr>
                <td><%= item.getProductName() %></td>
                <td><%= String.format("%.2f", item.getPrice()) %></td>
                <td><%= item.getQuantity() %></td>
                <td><%= String.format("%.2f", itemTotal) %></td>
            </tr>
            <% } %>
            <tr>
                <td colspan="3" align="right"><strong>注文合計:</strong></td>
                <td><strong><%= String.format("%.2f", totalAmount) %> 円</strong></td>
            </tr>
        </table>
    </div>
    <hr>
    <% 
            }
        } else {
    %>
    <p>購入履歴はありません。</p>
    <% } %>
</body>
</html>


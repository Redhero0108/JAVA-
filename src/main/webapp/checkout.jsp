<%@ page import="java.util.List, model.CartItem" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <title>注文確定しました</title>
 
</head>
<jsp:include page="commonHeader.jsp" />
<body>
    <h1>注文確定しました！</h1>
    <p >
        <a href="products">商品一覧へ戻る</a>
    </p>

    <div class="order-summary">
        <p>お客様の注文情報</p>
        <table border="1">
            <tr>
                <th>商品名</th>
                <th>価格 (円)</th>
                <th>数量</th>
                <th>小計 (円)</th>
            </tr>
            <%
                List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
                double totalAmount = 0;
                if (cartItems != null && !cartItems.isEmpty()) {
                    for (CartItem item : cartItems) {
                        double itemTotal = item.getPrice() * item.getQuantity();
                        totalAmount += itemTotal;
            %>
            <tr>
                <td><%= item.getProductName() %></td>
                <td><%= String.format("%.2f", item.getPrice()) %></td>
                <td><%= item.getQuantity() %></td>
                <td><%= String.format("%.2f", itemTotal) %></td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="4">注文情報がありません。</td>
            </tr>
            <% } %>
            <tr>
                <td colspan="3" class="total">合計金額：</td>
                <td class="total"><%= String.format("%.2f", totalAmount) %> 円</td>
            </tr>
        </table>
    </div>

   
</body>
</html>

 
 
 
 
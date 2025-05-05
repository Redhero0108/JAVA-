<%@ page import="java.util.List, model.CartItem" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>カート</title>
</head>
<jsp:include page="commonHeader.jsp" />
<body>
    <h1>カート一覧</h1>
    <% if (request.getParameter("error") != null) { %>
        <p style="color:red;"><%= request.getParameter("error") %></p>
    <% } %>
    <a href="products">商品一覧へ戻る</a> 
   

    <table border="1">
        <tr>
            <th>商品名</th>
            <th>価格</th>
            <th>数量</th>
            <th>合計</th>
            <th>操作</th>
        </tr>
        <%
            List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
            if (cartItems != null && !cartItems.isEmpty()) {
                double total = 0;
                for (CartItem item : cartItems) {
                    double itemTotal = item.getPrice() * item.getQuantity();
                    total += itemTotal;
        %>
        <tr>
            <td><%= item.getProductName() %></td>
               
            <td><%= String.format("%.2f", item.getPrice()) %> 円</td>  
            
            <td><%= item.getQuantity() %></td>
            <td><%= String.format("%.2f", itemTotal) %> 円</td>
            <td>
                <!-- 商品削除ボタン -->
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="productId" value="<%= item.getProductId() %>">
                    <button type="submit">削除</button>
                </form>
            </td>
        </tr>
        <% 
                }
        %>
        <tr>
            <td colspan="3"><strong>合計：</strong></td>
            <td><strong><%= String.format("%.2f", total) %> 円</strong></td>
            <td></td>
        </tr>
        <% 
            } else {
        %>
        <tr>
            <td colspan="5">現在カートに商品はありません</td>
        </tr>
        <% } %>
    </table>

    <!-- カートを空にするボタン -->
    <form action="cart" method="post">
        <input type="hidden" name="action" value="clear">
        <button type="submit">カートを空にする</button>
    </form>

    <!-- 購入手続きボタン -->
    <form action="checkout" method="post">
        <button type="submit">商品注文を確定へ</button>
    </form>
</body>
</html>

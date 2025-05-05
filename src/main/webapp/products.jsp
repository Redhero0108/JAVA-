
<%@ page import="java.util.List, model.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品一覧</title>
</head>
<jsp:include page="commonHeader.jsp" />
<body>
    <h1>商品一覧</h1>
      
      <%
        String username = (String) session.getAttribute("username");
        if (username != null) {
    %>
        
    
        <a href="cart">カートを見る</a> |
         <a href="orderCheck">購入履歴を見る</a> |
        
    <% } else { %>
         <p><a href="index.jsp">ホームへ</a></p>
    <% } %>
    <table border="1">
        <tr>
            <th>商品名</th>
            <th>価格</th>
            <th>操作</th>
        </tr>
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products != null) {
                for (Product p : products) {
        %>
        <tr>
            <td><%= p.getName() %></td>
            <td><%= String.format("%.2f",p.getPrice()) %> 円</td>
            <td>
                <form action="cart" method="post">
                    <input type="hidden" name="productId" value="<%= p.getId() %>">
                    <input type="number" name="quantity" value="0" min="1">
                    <button type="submit">カートに追加</button>
                </form>
            </td>
        </tr>
        <% 
                }
            }
        %>
    </table>
</body>
</html>

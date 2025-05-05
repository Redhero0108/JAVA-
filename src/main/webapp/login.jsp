<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>ログイン</title>
</head>
<jsp:include page="commonHeader.jsp" />
<body>
    <h1>ログイン</h1>
   <p><a href="index.jsp">ホームへ</a></p>
    <% if (request.getParameter("message") != null) { %>
        <p style="color:green;"><%= request.getParameter("message") %></p>
    <% } %>
    
    
    <% if (request.getParameter("error") != null) { %>
        <p style="color:red;"><%= request.getParameter("error") %></p>
    <% } %>
    <form action="login" method="post">
        <label>ユーザー名：</label>
        <input type="text" name="username" required><br>
        <label>パスワード：</label>
        <input type="password" name="password" required><br>
        <button type="submit">ログイン</button>
    </form>
    <p><a href="register.jsp">アカウントを持っていない方はこちらへ</a></p>
   
</body>
</html>


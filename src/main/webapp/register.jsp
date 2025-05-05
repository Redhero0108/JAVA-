<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>新規登録</title>
</head>
<jsp:include page="commonHeader.jsp" />
<body>
    <h1>新規登録</h1>
      <p><a href="index.jsp">ホームへ</a></p>
     <% if (request.getParameter("error") != null) { %>
        <p style="color:red;"><%= request.getParameter("error") %></p>
    <% } %>
    <form action="register" method="post">
        <label>ユーザー名：</label>
        <input type="text" name="username" required><br>
        <label>パスワード：</label>
        <input type="password" name="password" required><br>
        <button type="submit">登録</button>
    </form> 
</body>
</html>

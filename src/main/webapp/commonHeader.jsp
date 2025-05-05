<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String username = (String) session.getAttribute("username");
    if (username != null) {
%>
    <div style="text-align: center; font-size: 14px; color: red; padding: 10px; font-weight: bold;">
        ユーザーID：<strong><%= username %></strong> ログイン中
        | <a href="logout" style="color: blue; text-decoration: none;">ログアウト</a>
    </div>
<%
    }
%>

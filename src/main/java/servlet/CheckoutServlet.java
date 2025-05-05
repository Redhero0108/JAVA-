/*
package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import dao.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartItem;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp?error=ログインしてください");
            return;
        }

        String username = (String) session.getAttribute("username");
        CartDAO cartDAO = new CartDAO();
        List<CartItem> cartItems = cartDAO.getCartItems(username);

        if (cartItems == null || cartItems.isEmpty()) {
        	String errorMessage = URLEncoder.encode("カートが空です,商品を購入してください", StandardCharsets.UTF_8);
        	response.sendRedirect("cart.jsp?error=" + errorMessage);
            return;
        }

        // 注文情報をセッションに保存（checkout.jsp で表示するため）
        session.setAttribute("cartItems", cartItems);

        // カートをクリアする
        cartDAO.clearCart(username);

        // 注文完了ページへリダイレクト
        response.sendRedirect("checkout.jsp");
    }
}
*/
package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import dao.CartDAO;
import dao.OrderDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartItem;
import model.OrderItem;
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
        	String errorMessage = URLEncoder.encode("ログインしてください", StandardCharsets.UTF_8);
        	response.sendRedirect("login.jsp?error=" + errorMessage);
         
            return;
        }

        String username = (String) session.getAttribute("username");
        UserDAO userDAO = new UserDAO();
        int userId = userDAO.getUserIdByUsername(username);

        CartDAO cartDAO = new CartDAO();
        List<CartItem> cartItems = cartDAO.getCartItems(username);

        if (cartItems == null || cartItems.isEmpty()) {
        	String errorMessage = URLEncoder.encode("カートが空です", StandardCharsets.UTF_8);
        	response.sendRedirect("cart.jsp?error=" + errorMessage);
         
            return;
        }

        double totalPrice = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : cartItems) {
            totalPrice += item.getPrice() * item.getQuantity();
            orderItems.add(new OrderItem(item.getProductId(), item.getProductName(), item.getQuantity(), item.getPrice()));
        }

        OrderDAO orderDAO = new OrderDAO();
        int orderId = orderDAO.saveOrder(userId, totalPrice, orderItems);

        cartDAO.clearCart(username);

        session.setAttribute("cartItems", cartItems);
        response.sendRedirect("checkout.jsp");
    }
}


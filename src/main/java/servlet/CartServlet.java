
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

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
        	String errorMessage = URLEncoder.encode("ログインしてください", StandardCharsets.UTF_8);
        	response.sendRedirect("login.jsp?error=" + errorMessage);
         
            return;
        }

        String username = (String) session.getAttribute("username");
        CartDAO cartDAO = new CartDAO();
        List<CartItem> cartItems = cartDAO.getCartItems(username);

        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
        	String errorMessage = URLEncoder.encode("ログインしてください", StandardCharsets.UTF_8);
        	response.sendRedirect("login.jsp?error=" + errorMessage);
         
            return;
        }

        String action = request.getParameter("action");
        String username = (String) session.getAttribute("username");
        CartDAO cartDAO = new CartDAO();

        if ("clear".equals(action)) {
            // カートを空にする
            cartDAO.clearCart(username);
        } else if ("delete".equals(action)) {
            // 指定した商品をカートから削除
            int productId = Integer.parseInt(request.getParameter("productId"));
            cartDAO.removeFromCart(username, productId);
        } else {
            // 商品をカートに追加
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            cartDAO.addToCart(username, productId, quantity);
        }

        response.sendRedirect("cart");
    }
}

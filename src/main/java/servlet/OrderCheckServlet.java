package servlet;

import java.io.IOException;
import java.util.List;

import dao.OrderDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;

@WebServlet("/orderCheck")
public class OrderCheckServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp?error=ログインしてください");
            return;
        }

        String username = (String) session.getAttribute("username");
        UserDAO userDAO = new UserDAO();
        int userId = userDAO.getUserIdByUsername(username);

        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.getUserOrders(userId);

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
    }
}


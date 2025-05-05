package servlet;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        boolean isValidUser = userDAO.validateUser(username, password);

        if (isValidUser) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("products");
        } else {
        	String errorMessage = URLEncoder.encode("ユーザー名あるいはパスワードが誤りです", StandardCharsets.UTF_8);
        	response.sendRedirect("login.jsp?error=" + errorMessage);
        }
    }
}

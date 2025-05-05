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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.registerUser(username, password);

        if (success) {
        	
        	String Message = URLEncoder.encode("登録成功,ログインしてください", StandardCharsets.UTF_8);
        	response.sendRedirect("login.jsp?message=" + Message);
        
        } else {
            String errorMessage = URLEncoder.encode("ユーザー名が既に存在します修正してから再登録してください", StandardCharsets.UTF_8);
        	response.sendRedirect("register.jsp?error=" + errorMessage);
        }
    }
}

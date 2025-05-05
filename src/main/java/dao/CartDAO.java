package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CartItem;
import util.DBUtil;

public class CartDAO {
    public List<CartItem> getCartItems(String username) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.price, c.quantity FROM cart c JOIN products p ON c.product_id = p.id JOIN users u ON c.user_id = u.id WHERE u.username = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                cartItems.add(new CartItem(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    public void addToCart(String username, int productId, int quantity) {
        String sql = "INSERT INTO cart (user_id, product_id, quantity) VALUES ((SELECT id FROM users WHERE username = ?), ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void clearCart(String username) {
        String sql = "DELETE FROM cart WHERE user_id = (SELECT id FROM users WHERE username = ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  

        // カート内の特定の商品を削除
        public void removeFromCart(String username, int productId) {
            String sql = "DELETE FROM cart WHERE user_id = (SELECT id FROM users WHERE username = ?) AND product_id = ?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setInt(2, productId);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

     
    
    
    
}


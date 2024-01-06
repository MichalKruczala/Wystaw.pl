package pl.it.portfolio.DB;

import org.springframework.beans.factory.annotation.Autowired;
import pl.it.portfolio.DB.interfaces.IOrderPositionDAO;
import pl.it.portfolio.DB.interfaces.IProductDAO;
import pl.it.portfolio.model.OrderPosition;
import pl.it.portfolio.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IOrderPosition implements IOrderPositionDAO {
    @Autowired
    Connection connection;
    @Autowired
    IProductDAO productDAO;
    @Override
    public void persistOrderPosition(OrderPosition orderPosition, int orderId) {
        try {
            String sql = "INSERT INTO torderposition (book_id, order_id, quantity) " +
                    "VALUES (?, ?, ?);";
            PreparedStatement ps = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orderPosition.getProduct().getId());
            ps.setInt(2, orderId);
            ps.setInt(3, orderPosition.getQuantity());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            orderPosition.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderPosition> getOrderPositionByOrderId(int id) {
        List<OrderPosition> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torderposition WHERE order_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Optional<Product> productBox = this.productDAO
                        .getProductById(rs.getInt("product_id"));
                if(productBox.isPresent()) {
                    result.add(new OrderPosition(
                            rs.getInt("id"),
                            productBox.get(),
                            rs.getInt("quantity")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Optional<OrderPosition> getOrderPositionById(int id) {
        return Optional.empty();
    }
}


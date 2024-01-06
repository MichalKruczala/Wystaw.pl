package pl.it.portfolio.DB;

import jdk.jshell.spi.ExecutionControl;
import pl.it.portfolio.DB.interfaces.IOrderDAO;
import pl.it.portfolio.model.Order;

import java.util.List;
import java.util.Optional;

public class OrderDAO implements IOrderDAO {
    @Override
    public void persistOrder(Order order) {

    }

    @Override
    public void updateOrder(Order order) throws ExecutionControl.NotImplementedException {

    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return null;
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return Optional.empty();
    }
}

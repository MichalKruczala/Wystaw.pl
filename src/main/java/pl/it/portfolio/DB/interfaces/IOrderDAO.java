package pl.it.portfolio.DB.interfaces;


import jdk.jshell.spi.ExecutionControl;
import pl.it.portfolio.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderDAO {
    void persistOrder(Order order);
    void updateOrder(Order order) throws ExecutionControl.NotImplementedException;
    List<Order> getOrdersByUserId(int userId);

    Optional<Order> getOrderById(int id);
}

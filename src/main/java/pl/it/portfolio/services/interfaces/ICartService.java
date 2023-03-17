package pl.it.portfolio.services.interfaces;

import pl.it.portfolio.exceptions.NotEnoughProductException;
import pl.it.portfolio.model.OrderPosition;

import java.util.List;

public interface ICartService {
    void addProductToCart(int bookId,int quantity) throws NotEnoughProductException;
    void clearCart();
    void removeFromCart(int bookId);
    List<OrderPosition> getCart();
    double calculateCartSum();
}

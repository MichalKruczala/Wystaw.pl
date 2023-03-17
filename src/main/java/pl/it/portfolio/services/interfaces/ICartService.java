package pl.it.portfolio.services.interfaces;

import pl.it.portfolio.model.OrderPosition;

import java.util.List;

public interface ICartService {
    void addProductToCart(int bookId,int quantity);
    void clearCart();
    void removeFromCart(int bookId);
    List<OrderPosition> getCart();
    double calculateCartSum();
}

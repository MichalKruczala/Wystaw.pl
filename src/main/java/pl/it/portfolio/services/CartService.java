package pl.it.portfolio.services;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import pl.it.portfolio.DB.interfaces.IProductDAO;
import pl.it.portfolio.exceptions.NotEnoughProductException;
import pl.it.portfolio.model.OrderPosition;
import pl.it.portfolio.model.Product;
import pl.it.portfolio.services.interfaces.ICartService;
import pl.it.portfolio.session.SessionObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CartService implements ICartService {

    @Autowired
    IProductDAO productDAO;
    @Resource
    SessionObject sessionObject;


    @Override
    public void addProductToCart(int productId, int quantity) throws NotEnoughProductException {
        Map<Integer, OrderPosition> cart = this.sessionObject.getCart();
        Optional<Product> productOptional = this.productDAO.getProductById(productId);
        if (productOptional.isEmpty()) {
            return;
        }
        if (cart.get(productId) == null && productOptional.get().getQuantity() > 0) {
            cart.put(productId, new OrderPosition(productOptional.get(), quantity));
        } else if (productOptional.get().getQuantity() > cart.get(productId).getQuantity()) {
            cart.get(productId).addQuantity(quantity);
        } else {
            throw new NotEnoughProductException();
        }
    }

    @Override
    public void clearCart() {
        this.sessionObject.getCart().clear();
    }

    @Override
    public void removeFromCart(int productId) {
        this.sessionObject.getCart().remove(productId);
    }

    @Override
    public List<OrderPosition> getCart() {
        return new ArrayList<>(this.sessionObject.getCart().values());
    }

    @Override
    public double calculateCartSum() {
        return this.sessionObject.getCart().values().stream()
            .mapToDouble(op -> op.getQuantity() * op.getProduct().getPrize())
            .sum();
    }
}

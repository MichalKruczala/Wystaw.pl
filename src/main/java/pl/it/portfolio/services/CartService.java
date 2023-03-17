package pl.it.portfolio.services;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import pl.it.portfolio.DB.interfaces.IProductDAO;
import pl.it.portfolio.model.OrderPosition;
import pl.it.portfolio.model.Product;
import pl.it.portfolio.services.interfaces.ICartService;
import pl.it.portfolio.session.SessionObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//public class CartService implements ICartService {
//
//    @Autowired
//    IProductDAO productDAO;
//    @Resource
//    SessionObject sessionObject;
//
////    @Override
////    public void addProductToCart(int productId,int quantity) {
////        Map<Integer, OrderPosition> cart = this.sessionObject.getCart();
////        Optional<Product> bookBox = this.productDAO.getProductById(productId);
////        if(bookBox.isEmpty()) {
////            return;
////        }
////        if(cart.get(productId) == null && bookBox.get().getQuantity() > 0) {
////            cart.put(productId, new OrderPosition(bookBox.get(), quantity));
////        } else if(bookBox.get().getQuantity() > cart.get(productId).getQuantity()) {
////            cart.get(bookId).incrementQuantity();
////        } else {
////            throw new NotEnoughBookException();
////        }
////    }
//
//  //  @Override
//    public void addBookToCart(int bookId) {
//
//    }
//
//    @Override
//    public void clearCart() {
//        this.sessionObject.getCart().clear();
//    }
//
//    @Override
//    public void removeFromCart(int bookId) {
//        this.sessionObject.getCart().remove(bookId);
//    }
//
//    @Override
//    public List<OrderPosition> getCart() {
//        return new ArrayList<>(this.sessionObject.getCart().values());
//    }
//
//    @Override
//    public double calculateCartSum() {
//        /*double sum = 0.0;
//        for(OrderPosition position : this.sessionObject.getCart().values()) {
//            sum += position.getQuantity() * position.getBook().getPrice();
//        }
//        return sum;*/
//        return this.sessionObject.getCart().values().stream()
//             //   .mapToDouble(op -> op.getQuantity() * op.getBook().getPrice())
//             //   .sum();
//        //.reduce(0.0, Double::sum);
//   // }  //TODO dokonczyc
//}
//}

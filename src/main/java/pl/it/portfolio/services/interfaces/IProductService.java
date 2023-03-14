package pl.it.portfolio.services.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.it.portfolio.model.Product;

import java.util.List;
import java.util.Optional;


public interface IProductService {

     void addProduct(HttpServletRequest request);

    void persistProduct(Product product);

    Optional<Product> getProductById(int id);

    void updateProduct(Product product);

    void deleteProduct(Product id);
}

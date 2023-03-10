package pl.it.portfolio.services.interfaces;

import org.springframework.web.bind.annotation.RequestParam;
import pl.it.portfolio.model.Product;

import java.util.List;
import java.util.Optional;

public interface IListingService {
    List<Product> getProductsForMainWebSite();

    List<Product> getProductsByParams(String name, String category, String delivery, String state, String localization, Double prize);

    void persistProduct(Product product);

    Optional<Product> getProductById(int id);

    void updateProduct(Product product);

    void deleteProduct(Product id);

}

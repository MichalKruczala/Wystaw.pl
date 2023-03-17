package pl.it.portfolio.DB.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import pl.it.portfolio.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDAO {
    List<Product> listProductsForMainWebSite();

    List<Product> listProductsByParams(String name, String category, String delivery, String state, String localization, Double prize);

    void addProduct(HttpServletRequest request);

    void persistProduct(Product product);


    Optional<Product> getProductById(int id);

    void updateProduct(Product product);

    void deleteProduct(Product id);
}


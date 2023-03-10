package pl.it.portfolio.DB.interfaces;

import pl.it.portfolio.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDAO {
    List<Product> getProductsForMainWebSite();

    List<Product> getProductsByParams(String name, String category, String delivery, String state, String localization, Double prize);

    void persistProduct(Product product);

    List<Product> getProductById(int id);

    void updateProduct(Product product);

    void deleteProduct(Product id);
}


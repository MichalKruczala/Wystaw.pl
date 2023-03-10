package pl.it.portfolio.DB.interfaces;

import pl.it.portfolio.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDAO {
    List<Product> getProductsForMainWebSite();

    List<Product> getProdyctsBy(String pattern);

    void persistBook(Product book);

    Optional<Product> getBookById(int id);

    void updateBook(Product book);
}


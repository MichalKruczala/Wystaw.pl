package pl.it.portfolio.DB.interfaces;

import pl.it.portfolio.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDAO {
    List<Product> getProductsForMainWebSite();

//    List<Product> getProductsByName(String name);
//    List<Product> getProductsByNameAndCategory(String name, String category);
//    List<Product> getProductsByName(String name);
//    List<Product> getProductsByName(String name);
//    List<Product> getProductsByName(String name);
//    List<Product> getProductsByName(String name);

    void persistBook(Product book);

    Optional<Product> getBookById(int id);

    void updateBook(Product book);
}


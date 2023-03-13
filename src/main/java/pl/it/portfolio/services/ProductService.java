package pl.it.portfolio.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.it.portfolio.DB.interfaces.IProductDAO;
import pl.it.portfolio.model.Product;
import pl.it.portfolio.services.interfaces.IProductService;

import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductDAO productDAO;

    @Override
    public void addProduct(HttpServletRequest request) {
        this.productDAO.addProduct(request);
    }

    @Override
    public void persistProduct(Product product) {
        this.productDAO.persistProduct(product);
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return this.productDAO.getProductById(id);
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    @Override
    public void deleteProduct(Product id) {
        productDAO.deleteProduct(id);
    }
}

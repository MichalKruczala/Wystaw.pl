package pl.it.portfolio.services;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.it.portfolio.DB.interfaces.IProductDAO;
import pl.it.portfolio.model.Product;
import pl.it.portfolio.services.interfaces.IListingService;

import java.util.*;

@Service
public class ListingService implements IListingService {

    @Autowired
    IProductDAO productDAO;

    @Override
    public List<Product> getProductsForMainWebSite() {
        return productDAO.getProductsForMainWebSite();
    }

    @Override
    public List<Product> getProductsByParams(String name, String category, String delivery,
                                             String state, String localization, Double prize) {
        return productDAO.getProductsByParams(name, category, delivery, state, localization, prize);
    }

    @Override
    public void persistProduct(Product product) {
        this.productDAO.persistProduct(product);
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return this.productDAO.getProductById(id);
        //TODO coś nie gra dokończyć
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


//    Session session = this.sessionFactory.openSession();
//    Query<Product> query = session.createQuery(
//            "FROM pl.it.portfolio.model.Product WHERE id = :id",
//            Product.class);
//        query.setParameter("id", id);
//                Optional<Product> result = Optional.empty();
//        try {
//        result = Optional.of(query.getSingleResult());
//        } catch (NoResultException e) {}
//        session.close();
//        return result;

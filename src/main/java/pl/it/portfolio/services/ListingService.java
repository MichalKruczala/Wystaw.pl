package pl.it.portfolio.services;

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
    public List<Product> listProductsForMainWebSite() {
        return productDAO.listProductsForMainWebSite();
    }

    @Override
    public List<Product> listProductsByParams(String name, String category, String delivery, String state, String localization, Double prize) {

         return this.productDAO.listProductsByParams(name, category, delivery, state, localization, prize);
    }
}
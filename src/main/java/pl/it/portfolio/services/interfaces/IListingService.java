package pl.it.portfolio.services.interfaces;

import org.springframework.web.bind.annotation.RequestParam;
import pl.it.portfolio.model.Product;

import java.util.List;

public interface IListingService {
    public String createQuery(String name, String category, String delivery, String state, String localization, Double prize);

}

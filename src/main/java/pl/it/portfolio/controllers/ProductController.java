package pl.it.portfolio.controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.it.portfolio.model.Product;
import pl.it.portfolio.services.interfaces.IListingService;
import pl.it.portfolio.services.interfaces.IProductService;
import pl.it.portfolio.session.SessionObject;

import java.io.IOException;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    IProductService productService;
    @Resource
    SessionObject sessionObject;

    @Autowired
    IListingService listingService;


    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addOffer(Model model) {
        model.addAttribute("sessionObject", this.sessionObject);
        return "offeradd";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addOffer(HttpServletRequest request) {
        this.productService.addProduct(request);

        return "redirect:/main";
    }

    @RequestMapping(path = "/view/offer", method = RequestMethod.GET)
    public String view(@RequestParam(value = "id") int id, Model model) {
        System.out.println(id);
        Optional<Product> productOptional = this.productService.getProductById(id);
        if (productOptional.isEmpty()) {
            return "redirect:/main";
        }
        model.addAttribute("product", productOptional.get());
        model.addAttribute("sessionObject", this.sessionObject);

        return "view-offer";
    }
}

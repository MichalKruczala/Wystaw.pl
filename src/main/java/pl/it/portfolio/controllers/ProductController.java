package pl.it.portfolio.controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.it.portfolio.services.interfaces.IProductService;
import pl.it.portfolio.session.SessionObject;

import java.io.IOException;

@Controller
public class ProductController {
    @Autowired
    IProductService productService;
    @Resource
    SessionObject sessionObject;


    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addOffer(Model model) {
        model.addAttribute("sessionObject",this.sessionObject);
        return "offeradd";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addOffer(HttpServletRequest request) throws ServletException, IOException {
        this.productService.addProduct(request);

        return "redirect:/main";
    }
}

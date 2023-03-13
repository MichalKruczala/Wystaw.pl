package pl.it.portfolio.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.it.portfolio.services.interfaces.IProductService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Controller
public class ProductController {
 @Autowired
    IProductService productService;


    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addOffer() {
        return "offeradd";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addOffer(HttpServletRequest request) throws ServletException, IOException {
        this.productService.addProduct(request);


        return "redirect:/main";
    }
}

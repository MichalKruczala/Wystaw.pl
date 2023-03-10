package pl.it.portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartController {
    @RequestMapping(path = "/cart", method = RequestMethod.GET)
    public String cart() {
        return "cart";
    }
}

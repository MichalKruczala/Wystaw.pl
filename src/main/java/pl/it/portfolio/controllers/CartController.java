package pl.it.portfolio.controllers;

import jakarta.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.it.portfolio.session.SessionObject;

@Controller
public class CartController {
    @Resource
    SessionObject sessionObject;
    @RequestMapping(path = "/cart", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("sessionObject",this.sessionObject);
        return "cart";
    }
}

package pl.it.portfolio.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.it.portfolio.model.Product;
import pl.it.portfolio.services.interfaces.IListingService;
import pl.it.portfolio.session.SessionObject;

@Controller
public class CommonController {
    @Resource
    SessionObject sessionObject;
    @Autowired
    IListingService listingService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String main2(Model model) {
        model.addAttribute("sessionObject", this.sessionObject);
        model.addAttribute("productsForMain", this.listingService.listProductsForMainWebSite());

        return "main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("sessionObject", this.sessionObject);
        return "redirect:/";
    }


}



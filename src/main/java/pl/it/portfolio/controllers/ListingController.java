package pl.it.portfolio.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.it.portfolio.model.Product;
import pl.it.portfolio.services.interfaces.IListingService;
import pl.it.portfolio.session.SessionObject;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Controller
public class ListingController {
    @Resource
    SessionObject sessionObject;
    @Autowired
    IListingService listingService;


    @GetMapping(value = "/listing")
    public String listing(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "category", required = false) String category,
                          @RequestParam(value = "delivery", required = false) String delivery,
                          @RequestParam(value = "state", required = false) String state,
                          @RequestParam(value = "localization", required = false) String localization,
                          @RequestParam(value = "prize", required = false) Double prize, Model model) {
        model.addAttribute("sessionObject", this.sessionObject);
        model.addAttribute("listByParam", this.listingService.listProductsByParams(name, category, delivery, state, localization, prize));
        return "listing";
    }
}

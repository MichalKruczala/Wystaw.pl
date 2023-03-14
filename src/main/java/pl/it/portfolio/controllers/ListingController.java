package pl.it.portfolio.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.it.portfolio.services.interfaces.IListingService;
import pl.it.portfolio.session.SessionObject;


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
        model.addAttribute("sessionObject",this.sessionObject);
        System.out.println("listing get");
        this.listingService.listProductsByParams(name, category, delivery, state, localization, prize);


        //TODO do modelu wrzucic ogloszenia ( szukane po name lub po name i ccategory
        return "main";
    }


}

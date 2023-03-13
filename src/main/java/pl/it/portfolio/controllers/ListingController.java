package pl.it.portfolio.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.it.portfolio.services.interfaces.IListingService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


@Controller
public class ListingController {
    @Autowired
    IListingService listingService;


    @GetMapping(value = "/listing")
    public String listing(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "category", required = false) String category,
                          @RequestParam(value = "delivery", required = false) String delivery,
                          @RequestParam(value = "state", required = false) String state,
                          @RequestParam(value = "localization", required = false) String localization,
                          @RequestParam(value = "prize", required = false) Double prize) {
        System.out.println("listing get");
          this.listingService.listProductsByParams(name, category, delivery, state, localization, prize);


        //TODO do modelu wrzucic ogloszenia ( szukane po name lub po name i ccategory
        return "main";
    }



}

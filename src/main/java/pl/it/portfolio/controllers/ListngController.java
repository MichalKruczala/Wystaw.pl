package pl.it.portfolio.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.it.portfolio.DB.interfaces.IProductDAO;
import pl.it.portfolio.services.interfaces.IListingService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


@Controller
public class ListngController {
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

        System.out.println(name);
         System.out.println(category);
        System.out.println(delivery);
        System.out.println(state);
        System.out.println(localization);
        System.out.println(prize);
        //TODO do modelu wrzucic ogloszenia ( szukane po name lub po name i ccategory
        return "main";
    }


    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addOffer() {
        return "offeradd";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addOffer(HttpServletRequest request) throws ServletException, IOException {
        System.out.println("przyszło");

        String name = Arrays.toString((request.getParameterValues("name"))).replaceAll("[^a-z\sA-Z0-9]", "");
        String category = Arrays.toString(request.getParameterValues("category")).replaceAll("[^a-zA-Z0-9]", "");
        String delivery = Arrays.toString(request.getParameterValues("delivery")).replaceAll("[^a-zA-Z0-9]", "");
        String state = Arrays.toString(request.getParameterValues("state")).replaceAll("[^a-zA-Z0-9]", "");
        String city = Arrays.toString(request.getParameterValues("city")).replaceAll("[^a-zA-Z0-9]", "");
        Double prize = Double.valueOf(Arrays.toString(request.getParameterValues("prize")).
                replaceAll("[^\\d,]+", "").
                replace(",", "."));

        //TODO ogarnąć to łądnie
        String uploadPath = "";
        try {

            Part file = request.getPart("image");
            String imageFileName = file.getSubmittedFileName();
            uploadPath = "C:/Users/Michał/IdeaProjects/Wystaw.pl/src/main/resources/static/uploadedPhotos/" + imageFileName;
            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream is = file.getInputStream();
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name);
        System.out.println(category);
        System.out.println(delivery);
        System.out.println(city);
        System.out.println(state);
        System.out.println(prize);
        System.out.println(uploadPath);
        return "redirect:/main";
    }
}

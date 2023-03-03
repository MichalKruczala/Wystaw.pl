package pl.it.portfolio.controllers;

import jakarta.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
       // model.addAttribute("books", this.bookService.getBooks());
      //  model.addAttribute("sessionObject", this.sessionObject);
        return "main";
    }
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String main2(@RequestBody String img) {

        // model.addAttribute("books", this.bookService.getBooks());
        //  model.addAttribute("sessionObject", this.sessionObject);
        return "main";
    }
}

package pl.it.portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}

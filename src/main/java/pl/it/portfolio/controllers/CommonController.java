package pl.it.portfolio.controllers;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.it.portfolio.session.SessionObject;

@Controller
public class CommonController {

    @Resource
    SessionObject sessionObject;
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String main2() {
        return "main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        //TODO wrzucić w model po 2 ogloszenia z kazdej kategorii
          model.addAttribute("sessionObject", this.sessionObject);
        return "redirect:/";
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String main2(@RequestBody String img) {


        //  model.addAttribute("sessionObject", this.sessionObject);
        return "main";
    }


}



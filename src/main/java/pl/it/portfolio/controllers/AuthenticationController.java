package pl.it.portfolio.controllers;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.it.portfolio.session.SessionObject;

@Controller
public class AuthenticationController {
    @Resource
    SessionObject sessionObject;
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)

    public String login2() {
        //TODO dac userowi sesje
        return "redirect:/main";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout() {
         //TODO nullować usera w sesji
        return "redirect:/main";
    }

}

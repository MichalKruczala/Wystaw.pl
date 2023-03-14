package pl.it.portfolio.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.it.portfolio.exceptions.UserLoginExistException;
import pl.it.portfolio.exceptions.UserValidationException;
import pl.it.portfolio.model.User;
import pl.it.portfolio.services.UserValidationService;
import pl.it.portfolio.services.interfaces.IAuthencitationService;
import pl.it.portfolio.session.SessionObject;

@Controller
public class AuthenticationController {
    @Resource
    SessionObject sessionObject;
    @Autowired
    UserValidationService userValidationService;
    @Autowired
    IAuthencitationService authenticationServices;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String form(Model model) {
        model.addAttribute("sessionObject", this.sessionObject);
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, @RequestParam String password2) {
        try {
            this.userValidationService.validateRegisterUser(user, password2);
            if (authenticationServices.findUserBylogin(user.getLogin()).isPresent()) {
            //    this.userDAOService.setThatLoginExists("***   Spróbuj ponownie z innym loginem.  ***");
                return "redirect:/register";
            }
            authenticationServices.registerUser(user);
            sessionObject.setUser(user);
        } catch (UserLoginExistException a) {
            a.getMessage();
            return "redirect:/register";
        } catch (UserValidationException e) {
            e.getMessage();
            return "redirect:/register";
        }
        return "redirect:/main";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("sessionObject", this.sessionObject);
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password) {
        try {
            this.userValidationService.validateUserTryingToLogIn(login, password);
        } catch (UserValidationException e) {
           e.getMessage();
            return "redirect:/login";
        }
        this.authenticationServices.authenticate(login, password);
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        return "redirect:/main";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.authenticationServices.logout();
        return "redirect:/main";
    }
}

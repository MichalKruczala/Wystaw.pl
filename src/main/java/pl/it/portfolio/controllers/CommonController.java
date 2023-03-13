package pl.it.portfolio.controllers;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.it.portfolio.session.SessionObject;

@Controller
public class CommonController {

    @Resource
    SessionObject sessionObject;

//@RequestMapping(value = "/submit-form",method = RequestMethod.GET)
//    public String dzialaj(){
//        return "jebac";
//
//    }

    @RequestMapping("/submit-form")
    public String submitForm(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam("age") int age,
            @RequestParam("gender") String gender){

        System.out.println(name);
        return "main";


    }

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



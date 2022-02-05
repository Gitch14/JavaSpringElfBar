package com.elfbar.BenikShop.controllers;

import com.elfbar.BenikShop.models.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "HomePage");
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/logout")
    public String logOut(Model model){
        return "LogOut";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        return "shop";
    }

    @GetMapping("/admin-panel/city-tav")
    public String city(Model model){
        return "city-tav";
    }

    @GetMapping("/admin-panel/od-tav")
    public String odessa(Model model){
        return "od-tav";
    }

    @GetMapping("/admin-panel/kiev-tav")
    public String kiev(Model model){
        return "city-tav";
    }

}
package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String showIndex(Model model){
        model.addAttribute("active","home");
        return "index";
    }

    @GetMapping("/home")
    public String showIndex2(Model model){
        model.addAttribute("active","home");
        return "index";
    }

    @GetMapping("/login")
    public String showLogin(Model model){
        model.addAttribute("active","login");
        return "login";
    }
}

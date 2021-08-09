package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.service.UserService;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showIndex(Model model){
        model.addAttribute("active","home");
        return "index";
    }

    @GetMapping("/login")
    public String showLogin(Model model){
        model.addAttribute("active","login");
        return "login";
    }

    @GetMapping("/error")
    public String error(Model model){
//        model.addAttribute("active","home");
        return "index";
    }

    @GetMapping("/signup")
    public String SignUp(Model model, @RequestParam(required = false, value="errorMessage") String errorMessage){
        System.out.println("errorMessage = " + errorMessage);
        if (errorMessage != null){
            model.addAttribute("errorMessage", errorMessage);
        }
        return "signup";
    }

    @PostMapping("/signup")
    public String SignUpPost(RedirectAttributes attributes,
                             @RequestParam String login,
                             @RequestParam String password,
                             @RequestParam String firstName,
                             @RequestParam String lastName){
        if (login.length() == 0 || password.length() == 0 || firstName.length() == 0 || lastName.length() == 0){
            attributes.addAttribute("errorMessage", "fields must be filled");
            return "redirect:/signup";
        }

        User user = new User(firstName,lastName);
        user.setLogin(login);
        user.setPassword(password);
        List<User> users = null;
        try {
            users = userService.findAll();
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "An error has occurred. Please try again");
            return "redirect:/signup";
        }
        boolean match = users.stream().anyMatch(user1 -> login.equals(user1.getLogin()));
        if (!match){
            try {
                userService.save(user);
            } catch (SQLException e) {
                attributes.addAttribute("errorMessage", "An error has occurred. Please try again");
                return "redirect:/signup";
            }
        }
        else {
            attributes.addAttribute("errorMessage", "User with this login already exists");
            return "redirect:/signup";
        }
        return "redirect:/";
    }

}

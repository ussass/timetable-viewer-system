package ru.trofimov.timetableviewersystem.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.service.UserService;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("active", "home");

        return "index";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("active", "login");
        return "login";
    }

    @GetMapping("/signup")
    public String SignUp(@RequestParam(required = false, value = "errorMessage") String errorMessage,
                         Model model, User user) {
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "signup";
    }

    @PostMapping("/signup")
    public String SignUpPost(RedirectAttributes attributes,
                             @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        boolean loginIsExists = false;
        try {
            if (userService.findByLogin(user.getLogin()) == null) {
                loginIsExists = true;
            }
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "An error has occurred. Please try again");
            return "redirect:/signup";
        }

        if (loginIsExists) {
            try {
                userService.save(user);
            } catch (SQLException e) {
                attributes.addAttribute("errorMessage", "An error has occurred. Please try again");
                return "redirect:/signup";
            }
        } else {
            attributes.addAttribute("errorMessage", "User with this login already exists");
            return "redirect:/signup";
        }
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        try {
            model.addAttribute("user", userService.findByLogin(username));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "profile";
    }
}

package ru.trofimov.timetableviewersystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.trofimov.timetableviewersystem.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAll(Model model, HttpServletRequest request) {
        model.addAttribute("active", "users");
        if (!request.isUserInRole("ROLE_ADMIN")) return "redirect:/";
        try {
            model.addAttribute("users", userService.findAll());
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }
        return "users/index";
    }



}

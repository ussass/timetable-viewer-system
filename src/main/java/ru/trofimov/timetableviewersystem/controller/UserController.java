package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trofimov.timetableviewersystem.model.Role;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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

    @GetMapping("/edit/{id}")
    public String editUser(Model model, HttpServletRequest request, User user, @PathVariable long id) {
        model.addAttribute("active", "users");
        if (!request.isUserInRole("ROLE_ADMIN")) return "redirect:/";
        try {
            model.addAttribute("user", userService.findById(id));
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }

        return "users/edit";
    }

    @PostMapping("/edit")
    public String postEditUser(RedirectAttributes attributes,
                               @Valid User user, BindingResult bindingResult,
                               @RequestParam(defaultValue = "false") boolean switchCheckAdmin,
                               @RequestParam(defaultValue = "false") boolean switchCheckStudent,
                               @RequestParam(defaultValue = "false") boolean switchCheckTeacher,
                               @RequestParam(defaultValue = "false") boolean switchCheckStuff) {

        System.out.println("user.toString() = " + user.toString());
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }

        Set<Role> roles = new HashSet<>();
        if (switchCheckAdmin) roles.add(Role.ADMIN);
        if (switchCheckStudent) roles.add(Role.STUDENT);
        if (switchCheckTeacher) roles.add(Role.TEACHER);
        if (switchCheckStuff) roles.add(Role.STUFF);

        try {
            User userById = userService.findById(user.getId());
            user.setLogin(userById.getLogin());
            user.setPassword(userById.getPassword());
            user.setRoles(roles);
            userService.update(user);
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "failed to update entry");
        }
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(RedirectAttributes attributes, @PathVariable long id) {

        try {
            userService.delete(id);
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "failed to delete user");
        }
        return "redirect:/users";
    }
}

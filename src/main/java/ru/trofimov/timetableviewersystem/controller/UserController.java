package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trofimov.timetableviewersystem.model.Role;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.service.UserGroupService;
import ru.trofimov.timetableviewersystem.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserGroupService userGroupService;

    public UserController(UserService userService, UserGroupService userGroupService) {
        this.userService = userService;
        this.userGroupService = userGroupService;
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
    public String editUser(Model model, HttpServletRequest request, @PathVariable long id) {
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
                                  @RequestParam String firstName,
                                  @RequestParam String lastName,
                                  @RequestParam(defaultValue = "false") boolean switchCheckAdmin,
                                  @RequestParam(defaultValue = "false") boolean switchCheckStudent,
                                  @RequestParam(defaultValue = "false") boolean switchCheckTeacher,
                                  @RequestParam(defaultValue = "false") boolean switchCheckStuff,
                                  @RequestParam Long id) {

        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("switchCheckAdmin = " + switchCheckAdmin);
        System.out.println("switchCheckStudent = " + switchCheckStudent);
        System.out.println("switchCheckTeacher = " + switchCheckTeacher);
        System.out.println("switchCheckStuff = " + switchCheckStuff);
        System.out.println("id = " + id);


        if (firstName.length() > 0 && lastName.length() > 0) {
            Set<Role> roles = new HashSet<>();
            if (switchCheckAdmin) roles.add(Role.ADMIN);
            if (switchCheckStudent) roles.add(Role.STUDENT);
            else try {
                userGroupService.deleteByUserId(id);
            } catch (SQLException e) {
                attributes.addAttribute("errorMessage", "failed to delete entry");
            }
            if (switchCheckTeacher) roles.add(Role.TEACHER);
            if (switchCheckStuff) roles.add(Role.STUFF);
            try {
                User user = userService.findById(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setRoles(roles);
                userService.update(user);
            } catch (SQLException e) {
                attributes.addAttribute("errorMessage", "failed to update entry");
            }
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

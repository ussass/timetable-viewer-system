package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.service.GroupService;
import ru.trofimov.timetableviewersystem.service.UserService;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final UserService userService;

    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping()
    public String showAll(Model model, @RequestParam(defaultValue = "") String errorMessage) {
        model.addAttribute("active", "groups");
        List<String> errorMessages = new ArrayList<>();
        try {
            model.addAttribute("groups", groupService.findAll());
        } catch (SQLException e) {
            errorMessages.add("Failed to load data");
        }
        if (errorMessage.length() > 0) errorMessages.add(errorMessage);
        if (errorMessages.size() > 0) model.addAttribute("errorMessage", String.join(", ", errorMessages));

        return "groups/index";
    }

    @GetMapping("/new")
    public String newGroup(Model model, Group group) {
        model.addAttribute("active", "groups");
        return "groups/new";
    }

    @PostMapping("/new")
    public String postNewGroup(RedirectAttributes attributes,
                               @Valid Group group, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "groups/new";
        }
        try {
            groupService.save(group);
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "failed to add entry");
        }
        return "redirect:/groups";
    }

    @GetMapping("/edit/{id}")
    public String editGroup(Model model, @PathVariable long id, Group group) {
        model.addAttribute("active", "groups");
        try {
            model.addAttribute("group", groupService.findById(id));
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }

        return "groups/edit";
    }

    @PostMapping("/edit")
    public String postEditGroup(RedirectAttributes attributes,
                                @Valid Group group, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "groups/edit";
        }
        try {
            groupService.update(group);
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "failed to update entry");
        }
        return "redirect:/groups";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(RedirectAttributes attributes, @PathVariable long id) {
        AtomicBoolean isUpdated = new AtomicBoolean(true);
        try {
            List<User> users = userService.findAllByGroup(id);
            users.forEach(user -> user.setGroupId(null));
            users.forEach(user -> {
                try {
                    userService.update(user);
                } catch (SQLException e) {
                    attributes.addAttribute("errorMessage", "failed to delete group");
                    isUpdated.set(false);
                }
            });
            if (isUpdated.get()) groupService.delete(id);
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "failed to delete group");
        }

        return "redirect:/groups";
    }
}

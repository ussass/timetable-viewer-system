package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.service.GroupService;

import java.sql.SQLException;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("active", "groups");
        try {
            model.addAttribute("groups", groupService.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "groups/index";
    }

    @GetMapping("/new")
    public String newGroup(Model model) {
        model.addAttribute("active", "groups");
        return "groups/new";
    }

    @PostMapping("/new")
    public String postNewGroup(Model model, @RequestParam String groupName) {
        if (groupName.length() > 0) {
            try {
                groupService.save(new Group(groupName));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/groups";
    }

    @GetMapping("/edit/{id}")
    public String editGroup(Model model, @PathVariable long id) {
        model.addAttribute("active", "groups");
        System.out.println("id = " + id);
        try {
            model.addAttribute("group", groupService.findById(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "groups/edit";
    }

    @PostMapping("/edit")
    public String postEditGroup(Model model,
                                @RequestParam String groupName,
                                @RequestParam Long id) {
        if (groupName.length() > 0) {
            Group group = new Group(groupName);
            group.setId(id);
            try {
                groupService.update(group);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/groups";
    }
}

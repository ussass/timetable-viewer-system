package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        try {
            model.addAttribute("groups", groupService.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "groups/index";
    }
}

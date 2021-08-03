package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.service.GroupService;
import ru.trofimov.timetableviewersystem.service.StudentService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final StudentService studentService;

    public GroupController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
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
    public String newGroup(Model model) {
        model.addAttribute("active", "groups");
        return "groups/new";
    }

    @PostMapping("/new")
    public String postNewGroup(RedirectAttributes attributes, @RequestParam String groupName) {
        if (groupName.length() > 0) {
            try {
                groupService.save(new Group(groupName));
            } catch (SQLException e) {
                attributes.addAttribute("errorMessage", "failed to add entry");
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
            model.addAttribute("errorMessage", "Failed to load data");
        }

        return "groups/edit";
    }

    @PostMapping("/edit")
    public String postEditGroup(RedirectAttributes attributes,
                                @RequestParam String groupName,
                                @RequestParam Long id) {
        if (groupName.length() > 0) {
            Group group = new Group(groupName);
            group.setId(id);
            try {
                groupService.update(group);
            } catch (SQLException e) {
                attributes.addAttribute("errorMessage", "failed to update entry");
            }
        }
        return "redirect:/groups";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(RedirectAttributes attributes, @PathVariable long id) {
        List<Student> studentList = null;
        try {
            studentList = studentService.findAll();
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "failed to delete group");
        }

        studentList.stream()
                .filter(student -> student.getGroupId() == id)
                .peek(student -> student.setGroupId(null))
                .forEach(student -> {
                    try {
                        studentService.update(student);
                    } catch (SQLException e) {
                        attributes.addAttribute("errorMessage", "failed to delete group");
                    }
                });

        try {
            groupService.delete(id);
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "failed to delete group");
        }

        return "redirect:/groups";
    }

}

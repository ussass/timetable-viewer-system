package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.model.Teacher;
import ru.trofimov.timetableviewersystem.service.TeacherService;

import java.sql.SQLException;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("active","teachers");
        try {
            model.addAttribute("teachers", teacherService.findAll());
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }
        return "teachers/index";
    }

    @GetMapping("/new")
    public String newGroup(Model model) {
        model.addAttribute("active", "teachers");
        return "teachers/new";
    }

    @PostMapping("/new")
    public String postNewGroup(RedirectAttributes attributes,
                               @RequestParam String firstName,
                               @RequestParam String lastName) {
        if (firstName.length() > 0 && lastName.length() > 0) {
            try {
                teacherService.save(new Teacher(firstName, lastName));
            } catch (SQLException e) {
                attributes.addAttribute("errorMessage", "failed to add entry");
            }
        }
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String editGroup(Model model, @PathVariable long id) {
        model.addAttribute("active", "teachers");
        try {
            model.addAttribute("teacher", teacherService.findById(id));
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }

        return "teachers/edit";
    }

    @PostMapping("/edit")
    public String postEditGroup(RedirectAttributes attributes,
                                @RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam Long id) {
            if (firstName.length() > 0 && lastName.length() > 0) {
            Teacher teacher = new Teacher(firstName, lastName);
            teacher.setId(id);
            try {
                teacherService.update(teacher);
            } catch (SQLException e) {
                attributes.addAttribute("errorMessage", "failed to update entry");
            }
        }
        return "redirect:/teachers";
    }
}

package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        try {
            model.addAttribute("teachers", teacherService.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "teachers/index";
    }

}

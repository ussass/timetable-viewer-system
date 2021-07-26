package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.trofimov.timetableviewersystem.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public String showAll(Model model) {
        return "students/index";
    }

}

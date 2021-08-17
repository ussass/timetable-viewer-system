package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    @GetMapping("/new")
    public String editLesson(Model model) {
//        model.addAttribute("active", "teachers");

        return "lessons/new";
    }

    @PostMapping("/new")
    public String postEditLesson(
            String[] group1
    ) {
        System.out.println(group1);
        return "/";
    }
}

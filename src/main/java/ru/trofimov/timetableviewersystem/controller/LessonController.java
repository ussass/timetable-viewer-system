package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trofimov.timetableviewersystem.model.Lesson;
import ru.trofimov.timetableviewersystem.service.ClassroomService;
import ru.trofimov.timetableviewersystem.service.GroupService;
import ru.trofimov.timetableviewersystem.service.LessonSlotService;
import ru.trofimov.timetableviewersystem.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    private final ClassroomService classroomService;
    private final GroupService groupService;
    private final UserService userService;
    private final LessonSlotService lessonSlotService;

    public LessonController(ClassroomService classroomService, GroupService groupService, UserService userService, LessonSlotService lessonSlotService) {
        this.classroomService = classroomService;
        this.groupService = groupService;
        this.userService = userService;
        this.lessonSlotService = lessonSlotService;
    }

    @GetMapping()
    public String showAll(Model model) throws SQLException {
//        model.addAttribute("active", "groups");
//        List<Lesson> lessons = le

        return "groups/index";
    }

    @GetMapping("/new")
    public String editLesson(Model model) throws SQLException {
//        model.addAttribute("active", "teachers");
        model.addAttribute("classrooms", classroomService.findAll());
        model.addAttribute("slots", lessonSlotService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("teachers", userService.findAllTeacher());

        return "lessons/new";
    }

    @PostMapping("/new")
    public String postEditLesson(
            @RequestParam() String[] group,
            @RequestParam() long[] teacher
    ) {

        List<Lesson> lessons = new ArrayList<>();
        for (int i = 0; i < group.length; i++) {
            String[] split = group[i].split("-");
            lessons.add(new Lesson(i + 1, teacher[i], Long.parseLong(split[0]), Long.parseLong(split[2]), Long.parseLong(split[1]), 1));
        }
        lessons.forEach(System.out::println);


        return "redirect:/lessons/new";
    }
}

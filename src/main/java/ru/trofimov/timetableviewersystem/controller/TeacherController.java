package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trofimov.timetableviewersystem.model.Teacher;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.service.TeacherService;
import ru.trofimov.timetableviewersystem.service.UserService;

import java.sql.SQLException;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final UserService userService;

    public TeacherController(TeacherService teacherService, UserService userService) {
        this.teacherService = teacherService;
        this.userService = userService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("active", "teachers");
        try {
            model.addAttribute("teachers", userService.findAllTeacher());
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }
        return "teachers/index";
    }

//    @GetMapping("/new")
//    public String newTeacher(Model model) {
//        model.addAttribute("active", "teachers");
//        return "teachers/new";
//    }
//
//    @PostMapping("/new")
//    public String postNewTeacher(RedirectAttributes attributes,
//                                 @RequestParam String firstName,
//                                 @RequestParam String lastName) {
//        if (firstName.length() > 0 && lastName.length() > 0) {
//            try {
//                teacherService.save(new Teacher(firstName, lastName));
//            } catch (SQLException e) {
//                attributes.addAttribute("errorMessage", "failed to add entry");
//            }
//        }
//        return "redirect:/teachers";
//    }

    @GetMapping("/edit/{id}")
    public String editTeacher(Model model, @PathVariable long id) {
        model.addAttribute("active", "teachers");
        try {
            model.addAttribute("teacher", userService.findById(id));
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }

        return "teachers/edit";
    }

    @PostMapping("/edit")
    public String postEditTeacher(RedirectAttributes attributes,
                                  @RequestParam String firstName,
                                  @RequestParam String lastName,
                                  @RequestParam Long id) {
        if (firstName.length() > 0 && lastName.length() > 0) {
            User user = null;
            try {
                user = userService.findById(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            user.setFirstName(firstName);
            user.setLastName(lastName);
//            Teacher teacher = new Teacher(firstName, lastName);
//            teacher.setId(id);
            try {
                userService.update(user);
//                teacherService.update(teacher);
            } catch (SQLException e) {
                attributes.addAttribute("errorMessage", "failed to update entry");
            }
        }
        return "redirect:/teachers";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(RedirectAttributes attributes, @PathVariable long id) {

        try {
//            teacherService.delete(id);
            userService.delete(id);
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "failed to delete teacher");
        }
        return "redirect:/teachers";
    }
}

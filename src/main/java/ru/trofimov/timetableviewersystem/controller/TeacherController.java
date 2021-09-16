package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trofimov.timetableviewersystem.model.*;
import ru.trofimov.timetableviewersystem.service.CourseService;
import ru.trofimov.timetableviewersystem.service.UserService;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final UserService userService;
    private final CourseService courseService;

    public TeacherController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("active", "teachers");
        try {
            List<Teacher> teachers = userService.findAllTeacher();
            List<Course> courses = courseService.findAll();
            teachers.forEach(
                    teacher -> teacher.setCourseName(courses.stream()
                            .filter(course -> course.getId() == teacher.getCourseId())
                            .findFirst().orElse(new Course(""))
                            .getCourseName())
            );

            model.addAttribute("teachers", teachers);
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }
        return "teachers/index";
    }

    @GetMapping("/edit/{id}")
    public String editTeacher(Model model, @PathVariable long id) {
        model.addAttribute("active", "teachers");
        try {
            model.addAttribute("teacher", userService.findById(id));
            model.addAttribute("courses", courseService.findAll());
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }

        return "teachers/edit";
    }

    @PostMapping("/edit")
    public String postEditTeacher(RedirectAttributes attributes,
                                  @RequestParam String firstName,
                                  @RequestParam String lastName,
                                  @RequestParam Long course,
                                  @RequestParam Long id) {
        if (firstName.length() > 0 && lastName.length() > 0) {
            try {
                User user = userService.findById(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setCourseId(course == 0 ? null : course);
                userService.update(user);
            } catch (SQLException e) {
                attributes.addAttribute("errorMessage", "failed to update entry");
            }
        }
        return "redirect:/teachers";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(RedirectAttributes attributes, @PathVariable long id) {

        try {
            userService.delete(id);
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "failed to delete teacher");
        }
        return "redirect:/teachers";
    }
}

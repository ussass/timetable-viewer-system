package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trofimov.timetableviewersystem.model.*;
import ru.trofimov.timetableviewersystem.service.CourseService;
import ru.trofimov.timetableviewersystem.service.UserCourseService;
import ru.trofimov.timetableviewersystem.service.UserService;

import java.sql.SQLException;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final UserService userService;
    private final CourseService courseService;
    private final UserCourseService userCourseService;

    public TeacherController(UserService userService, CourseService courseService, UserCourseService userCourseService) {
        this.userService = userService;
        this.courseService = courseService;
        this.userCourseService = userCourseService;
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

    @GetMapping("/edit/{id}")
    public String editTeacher(Model model, @PathVariable long id) {
        model.addAttribute("active", "teachers");
        try {
            model.addAttribute("teacher", userService.findTeacherById(id));
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
                Teacher teacher = userService.findTeacherById(id);
                if (!teacher.getCourseId().equals(course)) {
                    if (course != 0) {
                        if (teacher.getCourseId() == 0) {
                            userCourseService.save(new UserCourse(id, course));
                        } else {
                            userCourseService.update(new UserCourse(id, course));
                        }
                    } else {
                        userCourseService.deleteByUserId(id);
                    }
                }
                if (!teacher.getFullName().equals(firstName + " " + lastName)) {
                    userService.update(new User(
                            id,
                            firstName,
                            lastName,
                            teacher.getLogin(),
                            teacher.getPassword(),
                            teacher.getRoles()
                    ));
                }
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

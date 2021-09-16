package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.model.UserGroup;
import ru.trofimov.timetableviewersystem.service.GroupService;
import ru.trofimov.timetableviewersystem.service.UserGroupService;
import ru.trofimov.timetableviewersystem.service.UserService;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final GroupService groupService;
    private final UserService userService;
    private final UserGroupService userGroupService;

    public StudentController(GroupService groupService, UserService userService, UserGroupService userGroupService) {
        this.groupService = groupService;
        this.userService = userService;
        this.userGroupService = userGroupService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("active", "students");
        try {
            List<Student> students = userService.findAllStudent();
            List<Group> groups = groupService.findAll();
            students.forEach(
                    student -> student.setGroupName(groups.stream()
                            .filter(group -> group.getId() == student.getGroupId())
                            .findFirst().orElse(new Group(""))
                            .getGroupName())
            );

            model.addAttribute("students", students);
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }
        return "students/index";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(Model model, @PathVariable long id) {
        model.addAttribute("active", "students");
        try {
            model.addAttribute("student", userService.findById(id));
            model.addAttribute("groups", groupService.findAll());
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }

        return "students/edit";
    }

    @PostMapping("/edit")
    public String postEditTeacher(RedirectAttributes attributes,
                                  @RequestParam String firstName,
                                  @RequestParam String lastName,
                                  @RequestParam Long group,
                                  @RequestParam Long id) {
        if (firstName.length() > 0 && lastName.length() > 0) {
            try {
                User user = userService.findById(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setGroupId(group == 0 ? null : group);
                userService.update(user);
            } catch (SQLException e) {
                attributes.addAttribute("errorMessage", "failed to update entry");
            }
        }
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(RedirectAttributes attributes, @PathVariable long id) {

        try {
            userService.delete(id);
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "failed to delete students");
        }
        return "redirect:/students";
    }
}

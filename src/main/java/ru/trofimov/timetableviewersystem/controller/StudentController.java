package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.model.Teacher;
import ru.trofimov.timetableviewersystem.service.GroupService;
import ru.trofimov.timetableviewersystem.service.StudentService;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final GroupService groupService;

    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping()
    public String showAll(Model model) {
        List<Group> groupList = null;
        List<Student> studentList = null;
        model.addAttribute("active", "students");
        try {
            studentList = studentService.findAll();
            groupList = groupService.findAll();
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }
        System.out.println("studentList = " + studentList);
        if (studentList != null && groupList != null) {
            List<Group> finalGroupList = groupList;
            studentList.forEach(student -> student.setGroupName(
                    finalGroupList.stream()
                            .filter(group -> group.getId() == student.getGroupId())
                            .findFirst().orElse(new Group("without group")).getGroupName()));
//            );

            model.addAttribute("students", studentList);
        }
        return "students/index";
    }

    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("active", "students");
        List<Group> groupList = null;
        try {
            groupList = groupService.findAll();
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }
        if (groupList != null){
            model.addAttribute("groups", groupList);
        }

        return "students/new";
    }

    @PostMapping("/new")
    public String postNewStudent(RedirectAttributes attributes,
                                 @RequestParam Long group,
                                 @RequestParam String firstName,
                                 @RequestParam String lastName) {
        if (firstName.length() > 0 && lastName.length() > 0) {
            if (group == 0) group = null;
            try {
                Student student = new Student(firstName, lastName);
                student.setGroupId(group);
                studentService.save(student);
            } catch (SQLException e) {
                attributes.addAttribute("errorMessage", "failed to add entry");
            }
        }

        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editTeacher(Model model, @PathVariable long id) {
        model.addAttribute("active", "students");
        try {
            model.addAttribute("student", studentService.findById(id));
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
            if (group == 0) group = null;
            Student student = new Student(firstName, lastName);
            student.setGroupId(group);
            student.setId(id);
            try {
                studentService.update(student);
            } catch (SQLException e) {
                attributes.addAttribute("errorMessage", "failed to update entry");
            }
        }
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(RedirectAttributes attributes, @PathVariable long id) {

        try {
            studentService.delete(id);
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "failed to delete students");
        }
        return "redirect:/students";
    }

}

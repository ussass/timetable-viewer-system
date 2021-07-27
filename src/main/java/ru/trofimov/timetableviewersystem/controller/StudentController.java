package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.model.Student;
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
        try {
            studentList = studentService.findAll();
            groupList = groupService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Group> finalGroupList = groupList;
        studentList.forEach(student -> student.setGroupName(
                finalGroupList.stream()
                        .filter(group -> group.getId() == student.getGroupId())
                        .findFirst().get().getGroupName())
        );

        model.addAttribute("active","students");
        model.addAttribute("students", studentList);

        return "students/index";
    }

}

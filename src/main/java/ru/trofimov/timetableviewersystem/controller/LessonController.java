package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trofimov.timetableviewersystem.model.Course;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.model.Lesson;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.service.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;
    private final ClassroomService classroomService;
    private final GroupService groupService;
    private final UserService userService;
    private final LessonSlotService lessonSlotService;
    private final CourseService courseService;

    public LessonController(LessonService lessonService, ClassroomService classroomService,
                            GroupService groupService, UserService userService, LessonSlotService lessonSlotService, CourseService courseService) {
        this.lessonService = lessonService;
        this.classroomService = classroomService;
        this.groupService = groupService;
        this.userService = userService;
        this.lessonSlotService = lessonSlotService;
        this.courseService = courseService;
    }

    @GetMapping()
    public String showAll(Model model) throws SQLException {
        model.addAttribute("active", "timetable");
        model.addAttribute("slots", lessonSlotService.findAll());
        model.addAttribute("classrooms", classroomService.findAll());

        List<Lesson> lessons = lessonService.findAll();
        List<Group> groups = groupService.findAll();
        List<User> users = userService.findAll();
        List<Course> courses = courseService.findAll();

        for (Lesson lesson : lessons) {
            lesson.setGroupName(groups.stream()
                    .filter(gr -> gr.getId() == lesson.getGroupId())
                    .findFirst().orElse(new Group("no group"))
                    .getGroupName());
            lesson.setTeacherName(users.stream()
                    .filter(user -> user.getId() == lesson.getTeacherId())
                    .findFirst().orElse(new User("no", " teacher"))
                    .getFullName());
            lesson.setCourseName(courses.stream()
                    .filter(course -> course.getId() == lesson.getCourseId())
                    .findFirst().orElse(new Course("no course"))
                    .getCourseName());
        }

        model.addAttribute("lessons", lessons);

        return "lessons/index";
    }

    @GetMapping("/new")
    public String editLesson(Model model) throws SQLException {
        model.addAttribute("active", "timetable");
        model.addAttribute("classrooms", classroomService.findAll());
        model.addAttribute("slots", lessonSlotService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("teachers", userService.findAllTeacher());

        return "lessons/new";
    }

    @PostMapping("/new")
    public String postEditLesson(
            @RequestParam() String[] group,
            @RequestParam() String[] courseTeacher
    ) {

        List<Lesson> lessons = new ArrayList<>();
        for (int i = 0; i < group.length; i++) {
            String[] splitGroup = group[i].split("-");
            String[] splitTeacher = courseTeacher[i].split("-");
            lessons.add(new Lesson(Long.parseLong(splitTeacher[0]), Long.parseLong(splitTeacher[1]),
                    Long.parseLong(splitGroup[0]), Long.parseLong(splitGroup[2]),
                    Long.parseLong(splitGroup[1]), 1));
        }
        lessonService.saveAll(lessons);

        return "redirect:/lessons/new";
    }
}

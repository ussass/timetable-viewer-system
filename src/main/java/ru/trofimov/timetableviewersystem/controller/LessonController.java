package ru.trofimov.timetableviewersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String showAll(Model model, @RequestParam(defaultValue = "1") Integer day) throws SQLException {
        model.addAttribute("active", "timetable");
        model.addAttribute("activeDay", day);
        model.addAttribute("slots", lessonSlotService.findAll());
        model.addAttribute("classrooms", classroomService.findAll());

        List<Lesson> lessons = lessonService.getLessonsForDay(day);
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
        System.out.println("day = " + day);

        model.addAttribute("lessons", lessons);

        return "lessons/index";
    }

    @GetMapping("/new")
    public String editLesson(Model model, @RequestParam(defaultValue = "1") Integer day) throws SQLException {
        model.addAttribute("active", "timetable");
        model.addAttribute("activeDay", day);
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
        System.out.println("courseTeacher = " + courseTeacher[0]);
        List<Lesson> lessons = new ArrayList<>();
        for (int i = 0; i < group.length; i++) {
            String[] splitGroup = group[i].split("-");
            String[] splitTeacher = courseTeacher[i].split("-");
            long courseId = Long.parseLong(splitTeacher[0]);
            long teacherId = Long.parseLong(splitTeacher[1]);
            long groupId = Long.parseLong(splitGroup[0]);
            lessons.add(new Lesson(
                    courseId == 0 ? null : courseId,
                    teacherId == 0 ? null : teacherId,
                    groupId == 0 ? null : groupId,
                    Long.parseLong(splitGroup[2]),      //classroomId
                    Long.parseLong(splitGroup[1]),      //lessonSlotId
                    Integer.parseInt(splitTeacher[2]))  //dayOfWeek
            );
        }
        lessonService.saveAll(lessons);

        return "redirect:/lessons";
    }

    @GetMapping("/edit/{id}")
    public String editLesson(Model model, @PathVariable long id) {
        model.addAttribute("active", "timetable");
        try {
            Lesson lesson = lessonService.findById(id);
            model.addAttribute("lesson", lesson);
            model.addAttribute("groups", groupService.findAll());
            model.addAttribute("teachers", userService.findAllTeacher());
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Failed to load data");
        }

        return "lessons/edit";
    }

    @PostMapping("/edit")
    public String postEditLesson(RedirectAttributes attributes,
                                 @RequestParam Long group,
                                 @RequestParam String courseTeacher,
                                 @RequestParam Long id) {
        System.out.println("courseTeacher = " + courseTeacher);
        String[] split = courseTeacher.split("-");
        try {
            Lesson lesson = lessonService.findById(id);
            lesson.setGroupId(group);
            lesson.setCourseId(Long.parseLong(split[0]));
            lesson.setTeacherId(Long.parseLong(split[1]));
            lessonService.update(lesson);
        } catch (SQLException e) {
            attributes.addAttribute("errorMessage", "failed to update entry");
        }

        return "redirect:/lessons/";
    }
}

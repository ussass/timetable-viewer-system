package ru.trofimov.timetableviewersystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.trofimov.timetableviewersystem.model.*;
import ru.trofimov.timetableviewersystem.service.*;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class TimetableViewerSystemApplication {

    private static StudentService studentService;
    private static TeacherService teacherService;
    private static GroupService groupService;
    private static CourseService courseService;
    private static ClassroomService classroomService;
    private static LessonSlotService lessonSlotService;
    private static ClassesService classesService;
    private static final Logger logger = LoggerFactory.getLogger(TimetableViewerSystemApplication.class);

    public TimetableViewerSystemApplication(StudentService studentService, GroupService groupService,
                                            TeacherService teacherService, ClassroomService classroomService,
                                            CourseService courseService, LessonSlotService lessonSlotService,
                                            ClassesService classesService) {
        TimetableViewerSystemApplication.studentService = studentService;
        TimetableViewerSystemApplication.groupService = groupService;
        TimetableViewerSystemApplication.courseService = courseService;
        TimetableViewerSystemApplication.classroomService = classroomService;
        TimetableViewerSystemApplication.lessonSlotService = lessonSlotService;
        TimetableViewerSystemApplication.teacherService = teacherService;
        TimetableViewerSystemApplication.classesService = classesService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TimetableViewerSystemApplication.class, args);
        try{
            System.out.println("\nGroup's timetable:");
            showTimetable(groupService.getGroupTimetable(1, 1, Long.MAX_VALUE));

            System.out.println("\nTeacher's timetable:");
            showTimetable(teacherService.getTeacherTimetable(1, 1, Long.MAX_VALUE));

            System.out.println("\nClassroom's timetable:");
            showTimetable(classroomService.getClassroomTimetable(1, 1, Long.MAX_VALUE));
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void showTimetable(List<Classes> classesList) {
        try {
            for (Classes classes : classesList) {

                System.out.println("Course: " + courseService.findById(classes.getCourseId()).getCourseName());
                System.out.println("Teacher: " + teacherService.findById(classes.getTeacherId()).getFullName());
                System.out.println("Group: " + groupService.findById(classes.getGroupId()).getGroupName());
                System.out.println("Classroom: " + classroomService.findById(classes.getClassroomId()).getNumber());
                System.out.println("LessonSlot: " + lessonSlotService.findById(classes.getLessonSlotId()).getNumber());
                System.out.println("Date and time: " + classes.getDateAndTime());
                System.out.println("-------------");
            }
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
            e.printStackTrace();
        }
    }
}

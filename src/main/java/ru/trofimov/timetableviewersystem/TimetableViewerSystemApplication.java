package ru.trofimov.timetableviewersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.trofimov.timetableviewersystem.dao.*;
import ru.trofimov.timetableviewersystem.model.*;
import ru.trofimov.timetableviewersystem.service.GroupService;
import ru.trofimov.timetableviewersystem.service.StudentService;
import ru.trofimov.timetableviewersystem.service.TeacherService;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class TimetableViewerSystemApplication {

	private static StudentService studentService;
	private static TeacherService teacherService;
	private static GroupService groupService;
	private static CourseDao courseDao;
	private static ClassroomDao classroomDao;
	private static LessonSlotDao lessonSlotDao;

	public TimetableViewerSystemApplication(StudentService studentService, GroupService groupService, TeacherService teacherService,
											ClassroomDao classroomDao, CourseDao courseDao, LessonSlotDao lessonSlotDao) {
		TimetableViewerSystemApplication.studentService = studentService;
		TimetableViewerSystemApplication.groupService = groupService;
		TimetableViewerSystemApplication.courseDao = courseDao;
		TimetableViewerSystemApplication.classroomDao = classroomDao;
		TimetableViewerSystemApplication.lessonSlotDao = lessonSlotDao;
		TimetableViewerSystemApplication.teacherService = teacherService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TimetableViewerSystemApplication.class, args);

		List<Classes> classesList = groupService.getGroupTimetable(1, 1, Long.MAX_VALUE);
		try{
			for (Classes classes : classesList) {

				System.out.println("Course: " + courseDao.findById(classes.getCourseId()).getCourseName());
				System.out.println("Teacher: " + teacherService.findById(classes.getTeacherId()).getFullName());
				System.out.println("Group: " + groupService.findById(classes.getGroupId()).getGroupName());
				System.out.println("Classroom: " + classroomDao.findById(classes.getClassroomId()).getNumber());
				System.out.println("LessonSlot: " + lessonSlotDao.findById(classes.getLessonSlotId()).getNumber());
				System.out.println("Date and time: " + classes.getDateAndTime());
				System.out.println("-------------");
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
}

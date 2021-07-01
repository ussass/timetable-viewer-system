package ru.trofimov.timetableviewersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.trofimov.timetableviewersystem.dao.Dao;
import ru.trofimov.timetableviewersystem.model.Course;
import ru.trofimov.timetableviewersystem.model.Student;

import java.util.List;

@SpringBootApplication
public class TimetableViewerSystemApplication {

	private static Dao<Course> courseDao;
	private static Dao<Student> studentDao;

	public TimetableViewerSystemApplication(Dao<Course> courseDao, Dao<Student> studentDao) {
		TimetableViewerSystemApplication.courseDao = courseDao;
		TimetableViewerSystemApplication.studentDao = studentDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(TimetableViewerSystemApplication.class, args);

		List<Student> students = studentDao.findAll();
		students.forEach(System.out::println);

		System.out.println();
		Student student = studentDao.findById(1);
		System.out.println(student);

	}

}

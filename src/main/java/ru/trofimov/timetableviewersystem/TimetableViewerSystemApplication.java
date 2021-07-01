package ru.trofimov.timetableviewersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.trofimov.timetableviewersystem.dao.Dao;
import ru.trofimov.timetableviewersystem.model.Course;
import ru.trofimov.timetableviewersystem.model.Student;

import java.util.List;

@SpringBootApplication
public class TimetableViewerSystemApplication {

	private static Dao<Student> studentDao;

	public TimetableViewerSystemApplication(Dao<Student> studentDao) {
		TimetableViewerSystemApplication.studentDao = studentDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(TimetableViewerSystemApplication.class, args);

		List<Student> students = studentDao.findAll();
		students.forEach(System.out::println);

		System.out.println();
		Student student = studentDao.findById(1);
		System.out.println(student);

		studentDao.delete(228);

		System.out.println();
		Student student1 = new Student("Ivan", "Ivanov");
		System.out.println("student1.getStudentId() = " + student1.getStudentId());
		studentDao.add(student1);
		students = studentDao.findAll();
		students.forEach(System.out::println);

		System.out.println();
		student1.setGroupId(13);
		studentDao.update(student1, 0);
		students = studentDao.findAll();
		students.forEach(System.out::println);



	}

}

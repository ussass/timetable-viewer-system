package ru.trofimov.timetableviewersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.trofimov.timetableviewersystem.dao.StudentDao;
import ru.trofimov.timetableviewersystem.model.Student;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class TimetableViewerSystemApplication {

	private static StudentDao studentDao;

	public TimetableViewerSystemApplication(StudentDao studentDao) {
		TimetableViewerSystemApplication.studentDao = studentDao;
	}


	public static void main(String[] args) {
		SpringApplication.run(TimetableViewerSystemApplication.class, args);

		System.out.println("\n All students ----------------\n");
		List<Student> students = studentDao.findAll();
		students.forEach(System.out::println);

		System.out.println("\n Add new students ----------------\n");
		Student student = new Student("Peter", "Parker");
		student.setGroupId(5);
		try {
			student = studentDao.save(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(student);

		System.out.println("\n Update students ----------------\n");

		student.setFirstName("Miles");
		student.setLastName("Morales");
		student.setGroupId(6);

		try {
			student = studentDao.update(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(student);

		System.out.println("\n Delete student and show all ----------------\n");

		try {
			studentDao.delete(1L);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		students = studentDao.findAll();
		students.forEach(System.out::println);
	}
}

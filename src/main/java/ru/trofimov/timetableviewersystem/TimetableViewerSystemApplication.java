package ru.trofimov.timetableviewersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.trofimov.timetableviewersystem.dao.DaoOld;
import ru.trofimov.timetableviewersystem.dao.StudentDao;
import ru.trofimov.timetableviewersystem.model.Student;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class TimetableViewerSystemApplication {

	private static DaoOld<Student> studentDaoOld;
	private static StudentDao studentDao;

	public TimetableViewerSystemApplication(DaoOld<Student> studentDaoOld, StudentDao studentDao) {
		TimetableViewerSystemApplication.studentDaoOld = studentDaoOld;
		TimetableViewerSystemApplication.studentDao = studentDao;
	}


	public static void main(String[] args) {
		SpringApplication.run(TimetableViewerSystemApplication.class, args);

		List<Student> students = studentDao.findAll();
		students.forEach(System.out::println);

		System.out.println();

		Student student = studentDao.findById(1L);
		System.out.println(student);

		Student student1 = new Student("Ab", "Cd");
		Student student2 = null;
		try {
			student2 = studentDao.save(student1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(student2);

		studentDaoOld.delete(97);

		students = studentDao.findAll();
		students.forEach(System.out::println);

	}

}

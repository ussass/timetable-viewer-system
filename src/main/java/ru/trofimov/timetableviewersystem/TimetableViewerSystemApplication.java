package ru.trofimov.timetableviewersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.trofimov.timetableviewersystem.dao.DaoOld;
import ru.trofimov.timetableviewersystem.dao.StudentDao;
import ru.trofimov.timetableviewersystem.model.Student;

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
		studentDao.save(student1);
		System.out.println(student1);

		studentDaoOld.delete(97);

		students = studentDao.findAll();
		students.forEach(System.out::println);


//		List<Student> students = studentDaoOld.findAll();
//		students.forEach(System.out::println);
//
//		System.out.println();
//		Student student = studentDaoOld.findById(1);
//		System.out.println(student);
//
//		studentDaoOld.delete(228);
//
//		System.out.println();
//		Student student1 = new Student("Ivan", "Ivanov");
//		System.out.println("student1.getStudentId() = " + student1.getId());
//		studentDaoOld.add(student1);
//		students = studentDaoOld.findAll();
//		students.forEach(System.out::println);
//
//		System.out.println();
//		student1.setGroupId(13);
//		studentDaoOld.update(student1, 0);
//		students = studentDaoOld.findAll();
//		students.forEach(System.out::println);



	}

}

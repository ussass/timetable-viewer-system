package ru.trofimov.timetableviewersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.trofimov.timetableviewersystem.dao.DaoOld;
import ru.trofimov.timetableviewersystem.model.Student;

import java.util.List;

@SpringBootApplication
public class TimetableViewerSystemApplication {

	private static DaoOld<Student> studentDaoOld;

	public TimetableViewerSystemApplication(DaoOld<Student> studentDaoOld) {
		TimetableViewerSystemApplication.studentDaoOld = studentDaoOld;
	}

	public static void main(String[] args) {
		SpringApplication.run(TimetableViewerSystemApplication.class, args);

		List<Student> students = studentDaoOld.findAll();
		students.forEach(System.out::println);

		System.out.println();
		Student student = studentDaoOld.findById(1);
		System.out.println(student);

		studentDaoOld.delete(228);

		System.out.println();
		Student student1 = new Student("Ivan", "Ivanov");
		System.out.println("student1.getStudentId() = " + student1.getId());
		studentDaoOld.add(student1);
		students = studentDaoOld.findAll();
		students.forEach(System.out::println);

		System.out.println();
		student1.setGroupId(13);
		studentDaoOld.update(student1, 0);
		students = studentDaoOld.findAll();
		students.forEach(System.out::println);



	}

}

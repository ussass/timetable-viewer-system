package ru.trofimov.timetableviewersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.trofimov.timetableviewersystem.dao.GroupDao;
import ru.trofimov.timetableviewersystem.dao.StudentDao;
import ru.trofimov.timetableviewersystem.dao.TeacherDao;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.model.Teacher;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class TimetableViewerSystemApplication {

	private static StudentDao studentDao;
	private static TeacherDao teacherDao;
	private static GroupDao groupDao;

	public TimetableViewerSystemApplication(StudentDao studentDao, TeacherDao teacherDao, GroupDao groupDao) {
		TimetableViewerSystemApplication.studentDao = studentDao;
		TimetableViewerSystemApplication.teacherDao = teacherDao;
		TimetableViewerSystemApplication.groupDao = groupDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(TimetableViewerSystemApplication.class, args);

		studentDaoDemonstration();
		teacherDaoDemonstration();
		groupDaoDemonstration();
	}

	public static void studentDaoDemonstration(){
		System.out.println("\n All students ----------------\n");
		List<Student> students = studentDao.findAll();
		students.forEach(System.out::println);

		System.out.println("\n Add new student ----------------\n");
		Student student = new Student("Peter", "Parker");
		student.setGroupId(5);
		try {
			student = studentDao.save(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(student);

		System.out.println("\n Update student ----------------\n");

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

	public static void teacherDaoDemonstration(){
		System.out.println("\n All teachers ----------------\n");
		List<Teacher> teachers = teacherDao.findAll();
		teachers.forEach(System.out::println);

		System.out.println("\n Add new teacher ----------------\n");
		Teacher teacher = new Teacher("Ethan", "Williams");
		try {
			teacher = teacherDao.save(teacher);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(teacher);

		System.out.println("\n Update teacher ----------------\n");

		teacher.setFirstName("Noah");
		teacher.setLastName("Jones");

		try {
			teacher = teacherDao.update(teacher);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(teacher);

		System.out.println("\n Delete teacher and show all ----------------\n");

		try {
			teacherDao.delete(1L);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		teachers = teacherDao.findAll();
		teachers.forEach(System.out::println);
	}

	public static void groupDaoDemonstration(){
		System.out.println("\n All groups ----------------\n");
		List<Group> groups = groupDao.findAll();
		groups.forEach(System.out::println);

		System.out.println("\n Add new group ----------------\n");
		Group group = new Group("ad-08");
		try {
			group = groupDao.save(group);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(group);

		System.out.println("\n Update group ----------------\n");

		group.setGroupName("ad-09");

		try {
			group = groupDao.update(group);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(group);

		System.out.println("\n Delete group and show all ----------------\n");

		try {
			groupDao.delete(1L);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		groups = groupDao.findAll();
		groups.forEach(System.out::println);
	}


}

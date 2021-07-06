package ru.trofimov.timetableviewersystem.dao;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.trofimov.timetableviewersystem.model.Student;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class JdbcTeacherDaoTest {

    @Autowired
    TeacherDao teacherDao;

    @Test
    @Order(1)
    void shouldFindAll() {
        List<Student> students = studentDao.findAll();
        students.forEach(System.out::println);
        assertEquals(2, students.size());
    }

    @Test
    @Order(2)
    void shouldFindById() {
        Student student = studentDao.findById(1L);
        assertEquals("Student{studentId=1, groupId=1, Tony Stark}", student.toString());
    }

    @Test
    @Order(3)
    void shouldAdd() throws SQLException {
        Student student = new Student("fName", "lName");
        student = studentDao.save(student);
        System.out.println("student.getId() = " + student.getId());
        assertNotEquals(0, (long) student.getId());
    }

    @Test
    @Order(4)
    void shouldUpdate() throws SQLException {
        Student student = new Student("newFirst", "newLast");
        List<Student> students = studentDao.findAll();
        long count = students.get(students.size() - 1).getId();
        student.setId(count);
        System.out.println("count = " + count);
        studentDao.update(student);
        Student studentExpected = studentDao.findById(count);
        assertEquals("Student{studentId=2, groupId=0, newFirst newLast}", studentExpected.toString());
    }

    @Test
    @Order(5)
    void shouldDelete() throws SQLException {
        List<Student> students = studentDao.findAll();
        long count = students.get(students.size() - 1).getId();
        studentDao.delete(count);
        List<Student> studentsExpected = studentDao.findAll();
        assertEquals(2, studentsExpected.size());
    }
}
package ru.trofimov.timetableviewersystem.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.trofimov.timetableviewersystem.model.Student;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class JdbcStudentDaoTest {

    @Autowired
    StudentDao studentDao;

    @Test
    @Sql({ "/student/recreate_schema.sql", "/student/insert_data.sql" })
    void shouldFindAll() {
        List<Student> students = studentDao.findAll();
        assertEquals(2, students.size());
    }

    @Test
    @Sql({ "/student/recreate_schema.sql", "/student/insert_data.sql" })
    void shouldFindById() throws SQLException {
        Student student = studentDao.findById(1L);
        assertEquals("Student{studentId=1, groupId=1, Tony_test Star_test}", student.toString());
    }

    @Test
    @Sql("/student/recreate_schema.sql")
    void shouldAdd() throws SQLException {
        Student student = new Student("fName", "lName");
        student = studentDao.save(student);

        assertNotEquals(0, (long) student.getId());
    }

    @Test
    @Sql({ "/student/recreate_schema.sql", "/student/insert_data.sql" })
    void shouldUpdate() throws SQLException {
        Student student = new Student("newFirst", "newLast");
        student.setId(1L);
        studentDao.update(student);
        Student studentExpected = studentDao.findById(1L);

        assertEquals("Student{studentId=1, groupId=0, newFirst newLast}", studentExpected.toString());
    }

    @Test
    @Sql({ "/student/recreate_schema.sql", "/student/insert_data.sql" })
    void shouldDelete() throws SQLException {
        studentDao.delete(1L);
        List<Student> studentsExpected = studentDao.findAll();

        assertEquals(1, studentsExpected.size());
    }
}
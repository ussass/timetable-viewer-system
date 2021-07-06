package ru.trofimov.timetableviewersystem.dao;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.trofimov.timetableviewersystem.model.Teacher;

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
        List<Teacher> teachers = teacherDao.findAll();
        assertEquals(2, teachers.size());
    }

    @Test
    @Order(2)
    void shouldFindById() {
        Teacher teacher = teacherDao.findById(1L);
        assertEquals("Teacher{id=1, Jacob Smith}", teacher.toString());
    }

    @Test
    @Order(3)
    void shouldAdd() throws SQLException {
        Teacher teacher = new Teacher("fName", "lName");
        teacher = teacherDao.save(teacher);

        assertNotEquals(0, (long) teacher.getId());
    }

    @Test
    @Order(4)
    void shouldUpdate() throws SQLException {
        Teacher teacher = new Teacher("newFirst", "newLast");
        List<Teacher> teachers = teacherDao.findAll();
        long count = teachers.get(teachers.size() - 1).getId();
        teacher.setId(count);
        teacherDao.update(teacher);
        Teacher teacherExpected = teacherDao.findById(count);

        assertEquals("Teacher{id=2, newFirst newLast}", teacherExpected.toString());
    }

    @Test
    @Order(5)
    void shouldDelete() throws SQLException {
        List<Teacher> teachers = teacherDao.findAll();
        long count = teachers.get(teachers.size() - 1).getId();
        teacherDao.delete(count);
        List<Teacher> teachersExpected = teacherDao.findAll();

        assertEquals(2, teachersExpected.size());
    }
}
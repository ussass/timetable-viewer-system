package ru.trofimov.timetableviewersystem.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
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
    @Sql({ "/teacher/recreate_schema.sql", "/teacher/insert_data.sql" })
    void shouldFindAll() throws SQLException {
        List<Teacher> teachers = teacherDao.findAll();
        assertEquals(2, teachers.size());
    }

    @Test
    @Sql({ "/teacher/recreate_schema.sql", "/teacher/insert_data.sql" })
    void shouldFindById() throws SQLException {
        Teacher teacher = teacherDao.findById(1L);
        assertEquals("Teacher{id=1, Jacob_test Smith_test}", teacher.toString());
    }

    @Test
    @Sql("/teacher/recreate_schema.sql")
    void shouldAdd() throws SQLException {
        Teacher teacher = new Teacher("fName", "lName");
        teacher = teacherDao.save(teacher);

        assertNotEquals(0, (long) teacher.getId());
    }

    @Test
    @Sql({ "/teacher/recreate_schema.sql", "/teacher/insert_data.sql" })
    void shouldUpdate() throws SQLException {
        Teacher teacher = new Teacher("newFirst", "newLast");
        teacher.setId(1L);
        teacherDao.update(teacher);
        Teacher teacherExpected = teacherDao.findById(1L);

        assertEquals("Teacher{id=1, newFirst newLast}", teacherExpected.toString());
    }

    @Test
    @Sql({ "/teacher/recreate_schema.sql", "/teacher/insert_data.sql" })
    void shouldDelete() throws SQLException {
        teacherDao.delete(1L);
        List<Teacher> teachersExpected = teacherDao.findAll();

        assertEquals(1, teachersExpected.size());
    }
}
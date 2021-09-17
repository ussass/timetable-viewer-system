package ru.trofimov.timetableviewersystem.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.model.User;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class JpaUserDaoTest extends BaseDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    @Sql({ "/user/recreate_schema.sql", "/user/insert_data.sql" })
    void shouldFindAll() throws SQLException {
        List<User> users = userDao.findAll();
        assertEquals(4, users.size());
    }

    @Test
    @Sql({ "/user/recreate_schema.sql", "/user/insert_data.sql" })
    void shouldFindById() throws SQLException {
        User user = userDao.findById(1L);
        assertEquals("User{id=1, firstName='The', lastName='Architect', login='admin', " +
                "password='$2a$10$csznBGiCXAQ.v4QyRSkbV.x8//TvJISzzRse21AgHTIgQoVF3jpfK', courseId='null', " +
                "groupId='null', roles=[], stringRoles=ADMIN}", user.toString());
    }

    @Test
    @Transactional
    @Sql("/user/recreate_schema.sql")
    void shouldAdd() throws SQLException {
        User user = new User();
        user.setFirstName("A");
        user.setLastName("B");
        user.setLogin("ab");
        user.setPassword("ab");
        user.setRolesToStringRoles();
        user = userDao.save(user);

        assertNotEquals(0, (long) user.getId());
    }

    @Test
    @Transactional
    @Sql({ "/user/recreate_schema.sql", "/user/insert_data.sql" })
    void shouldUpdate() throws SQLException {
        User user = new User();
        user.setFirstName("A");
        user.setLastName("B");
        user.setLogin("ab");
        user.setPassword("ab");
        user.setId(1L);
        userDao.update(user);
        User userExpected = userDao.findById(1L);

        assertEquals("A B", userExpected.getFullName());
    }

    @Test
    @Transactional
    @Sql({ "/user/recreate_schema.sql", "/user/insert_data.sql" })
    void shouldDelete() throws SQLException {
        userDao.delete(1L);
        List<User> usersExpected = userDao.findAll();

        assertEquals(3, usersExpected.size());
    }

    @Test
    @Sql({ "/user/recreate_schema.sql", "/user/insert_data.sql" })
    void shouldFindByLogin() throws SQLException {
        User user = userDao.findByLogin("admin");
        assertEquals("The Architect", user.getFullName());
    }

    @Test
    @Sql({ "/user/recreate_schema.sql", "/user/insert_data.sql" })
    void shouldFindAllStudent() throws SQLException {
        List<User> users = userDao.findAllStudent();
        assertEquals(1, users.size());
    }

    @Test
    @Sql({ "/user/recreate_schema.sql", "/user/insert_data.sql" })
    void shouldFindAllTeacher() throws SQLException {
        List<User> users = userDao.findAllTeacher();
        assertEquals(1, users.size());
    }
    @Test
    @Sql({ "/user/recreate_schema.sql", "/user/insert_data.sql" })
    void shouldFindAllByGroup() throws SQLException {
        List<User> users = userDao.findAllByGroup(1L);
        assertEquals(1, users.size());
    }
}
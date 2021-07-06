package ru.trofimov.timetableviewersystem.dao;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.trofimov.timetableviewersystem.model.Group;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcGroupDaoTest {

    @Autowired
    GroupDao groupDao;

    @Test
    @Order(1)
    void shouldFindAll() throws SQLException {
        List<Group> groups = groupDao.findAll();
        groups.forEach(System.out::println);
        assertEquals(2, groups.size());
    }

    @Test
    @Order(2)
    void shouldFindById() {
        Group group = groupDao.findById(1L);
        assertEquals("it-01", group.getGroupName());
    }

    @Test
    @Order(3)
    void shouldAdd() throws SQLException {
        Group group = new Group("test");
        group = groupDao.save(group);
        System.out.println("group.getId() = " + group.getId());
        assertNotEquals(0, (long) group.getId());
    }

    @Test
    @Order(4)
    void shouldUpdate() throws SQLException {
        Group group = new Group("update");
        List<Group> groups = groupDao.findAll();
        long count = groups.get(groups.size() - 1).getId();
        group.setId(count);
        System.out.println("count = " + count);
        groupDao.update(group);
        Group groupExpected = groupDao.findById(count);
        assertEquals("update", groupExpected.getGroupName());
    }

    @Test
    @Order(5)
    void shouldDelete() throws SQLException {
        List<Group> groups = groupDao.findAll();
        long count = groups.get(groups.size() - 1).getId();
        groupDao.delete(count);
        List<Group> groupsExpected = groupDao.findAll();
        assertEquals(2, groupsExpected.size());
    }
}
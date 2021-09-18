//package ru.trofimov.timetableviewersystem.dao;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.transaction.annotation.Transactional;
//import ru.trofimov.timetableviewersystem.model.Group;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class JpaGroupDaoTest extends BaseDaoTest {
//
//    @Autowired
//    GroupDao groupDao;
//
//    @Test
//    @Sql({ "/group/recreate_schema.sql", "/group/insert_data.sql" })
//    void shouldFindAll() throws SQLException {
//        List<Group> groups = groupDao.findAll();
//        assertEquals(2, groups.size());
//    }
//
//    @Test
//    @Sql({ "/group/recreate_schema.sql", "/group/insert_data.sql" })
//    void shouldFindById() throws SQLException {
//        Group group = groupDao.findById(1L).get();
//        assertEquals("it-01_test", group.getGroupName());
//    }
//
//    @Test
//    @Transactional
//    @Sql("/group/recreate_schema.sql")
//    void shouldAdd() throws SQLException {
//        Group group = new Group("test");
//        group = groupDao.save(group);
//
//        assertNotEquals(0, (long) group.getId());
//    }
//
//    @Test
//    @Transactional
//    @Sql({ "/group/recreate_schema.sql", "/group/insert_data.sql" })
//    void shouldUpdate() throws SQLException {
//        Group group = new Group("update");
//        group.setId(1L);
//        groupDao.save(group);
//        Group groupExpected = groupDao.findById(1L);
//
//        assertEquals("update", groupExpected.getGroupName());
//    }
//
//    @Test
//    @Transactional
//    @Sql({ "/group/recreate_schema.sql", "/group/insert_data.sql" })
//    void shouldDelete() throws SQLException {
//        groupDao.delete(new Group(1L));
//        List<Group> groupsExpected = groupDao.findAll();
//
//        assertEquals(1, groupsExpected.size());
//    }
//}
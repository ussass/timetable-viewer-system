package ru.trofimov.timetableviewersystem.dao;

import ru.trofimov.timetableviewersystem.model.UserCourse;

import java.sql.SQLException;

public interface UserCourseDao {

    UserCourse save(UserCourse entity) throws SQLException;

    UserCourse update(UserCourse entity) throws SQLException;

    void deleteByUserId(Long id) throws SQLException;

    void deleteByCourseId(Long id) throws SQLException;
}

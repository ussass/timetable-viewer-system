package ru.trofimov.timetableviewersystem.service;

import ru.trofimov.timetableviewersystem.model.UserCourse;

import java.sql.SQLException;

public interface UserCourseService {

    UserCourse save(UserCourse entity) throws SQLException;

    UserCourse update(UserCourse entity) throws SQLException;

    void deleteByUserId(Long id) throws SQLException;

    void deleteByCourseId(Long id) throws SQLException;

}

package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.trofimov.timetableviewersystem.dao.UserCourseDao;
import ru.trofimov.timetableviewersystem.model.UserCourse;
import ru.trofimov.timetableviewersystem.service.UserCourseService;

import java.sql.SQLException;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    private final UserCourseDao userCourseDao;
    private static final Logger logger = LoggerFactory.getLogger(UserCourseServiceImpl.class);

    public UserCourseServiceImpl(UserCourseDao userCourseDao) {
        this.userCourseDao = userCourseDao;
    }

    @Override
    public UserCourse save(UserCourse entity) throws SQLException {
        UserCourse userCourse = userCourseDao.save(entity);
        logger.info("saved new users_courses with userId={} and groupId ={}", userCourse.getUserId(), userCourse.getCourseId());
        return userCourse;
    }

    @Override
    public UserCourse update(UserCourse entity) throws SQLException {
        logger.info("updated users_courses with userId={} and groupId ={}", entity.getUserId(), entity.getCourseId());
        return userCourseDao.update(entity);
    }

    @Override
    public void deleteByUserId(Long id) throws SQLException {
        logger.info("deleted users_courses with userId = {}", id);
        userCourseDao.deleteByUserId(id);
    }

    @Override
    public void deleteByCourseId(Long id) throws SQLException {
        logger.info("deleted users_courses with groupId = {}", id);
        userCourseDao.deleteByCourseId(id);
    }
}

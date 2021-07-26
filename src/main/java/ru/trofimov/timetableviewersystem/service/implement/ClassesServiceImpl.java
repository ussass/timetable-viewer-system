package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.ClassesDao;
import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.service.ClassesService;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    private final ClassesDao classesDao;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public ClassesServiceImpl(ClassesDao classesDao) {
        this.classesDao = classesDao;
    }

    @Override
    @Transactional
    public Classes save(Classes entity) throws SQLException {
        logger.info("saved new classes");
        return classesDao.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Classes> findAll() throws SQLException {
        logger.info("Got all classes");
        return classesDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Classes findById(Long id) throws SQLException {
        logger.info("Got classes by id = {}", id);
        return classesDao.findById(id);
    }

    @Override
    @Transactional
    public Classes update(Classes entity) throws SQLException {
        logger.info("updated classes with id = {}", entity.getId());
        return classesDao.update(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        logger.info("deleted classes with id = {}", id);
        classesDao.delete(id);
    }
}

package ru.trofimov.timetableviewersystem.service.implement;

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

    public ClassesServiceImpl(ClassesDao classesDao) {
        this.classesDao = classesDao;
    }

    @Override
    @Transactional
    public Classes save(Classes entity) throws SQLException {
        return classesDao.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Classes> findAll() {
        return classesDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Classes findById(Long id) throws SQLException {
        return classesDao.findById(id);
    }

    @Override
    @Transactional
    public Classes update(Classes entity) throws SQLException {
        return classesDao.update(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        classesDao.delete(id);
    }
}

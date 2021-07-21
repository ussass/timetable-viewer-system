package ru.trofimov.timetableviewersystem.service.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.ClassroomDao;
import ru.trofimov.timetableviewersystem.model.Classroom;
import ru.trofimov.timetableviewersystem.service.ClassroomService;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomDao classroomDao;

    public ClassroomServiceImpl(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Classroom save(Classroom entity) throws SQLException {
        return classroomDao.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Classroom> findAll() {
        return classroomDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Classroom findById(Long id) throws SQLException {
        return classroomDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Classroom update(Classroom entity) throws SQLException {
        return classroomDao.update(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) throws SQLException {
        classroomDao.delete(id);
    }
}

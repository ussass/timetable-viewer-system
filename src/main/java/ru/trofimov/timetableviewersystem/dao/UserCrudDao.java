package ru.trofimov.timetableviewersystem.dao;

import org.springframework.data.repository.CrudRepository;
import ru.trofimov.timetableviewersystem.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserCrudDao extends CrudRepository<User, Long> {
}

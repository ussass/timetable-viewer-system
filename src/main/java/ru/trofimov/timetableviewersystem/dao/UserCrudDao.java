package ru.trofimov.timetableviewersystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.model.User;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface UserCrudDao extends CrudRepository<User, Long> {

    User findByLogin(String login) throws SQLException;

    List<User> findAllByGroupId(Long id) throws SQLException;

    List<User> findByRolesLike (String role);
}

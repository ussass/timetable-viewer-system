package ru.trofimov.timetableviewersystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.model.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    List<User> findAllByGroupId(Long id);

    List<User> findByStringRolesLike (String role);
}

package ru.trofimov.timetableviewersystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.model.Group;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
}

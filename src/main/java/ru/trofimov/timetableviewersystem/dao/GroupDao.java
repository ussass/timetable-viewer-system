package ru.trofimov.timetableviewersystem.dao;

import org.springframework.data.repository.CrudRepository;
import ru.trofimov.timetableviewersystem.model.Group;

public interface GroupDao extends CrudRepository<Group, Long> {
}

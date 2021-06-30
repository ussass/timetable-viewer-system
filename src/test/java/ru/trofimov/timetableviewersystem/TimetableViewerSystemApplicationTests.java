package ru.trofimov.timetableviewersystem;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.trofimov.timetableviewersystem.dao.mapper.StudentMapper;
import ru.trofimov.timetableviewersystem.model.Student;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class TimetableViewerSystemApplicationTests {

	@Autowired
	JdbcTemplate template;

	@Test
	void contextLoads() {
	}

	@Test
	void migrationDone() {
		List<Student> students = template
				.queryForStream("SELECT * FROM students", new StudentMapper())
				.collect(Collectors.toList());

		Assertions.assertThat(students).contains(new Student("Peter", "Parker"));
	}

}

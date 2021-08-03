package ru.trofimov.timetableviewersystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.service.GroupService;
import ru.trofimov.timetableviewersystem.service.StudentService;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @MockBean
    private GroupService groupService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetAllStudents() throws Exception {
        String url = "/students";
        Student student = new Student("Test", "test");
        student.setId(1L);
        student.setGroupId(1L);
        Group group = new Group("Test");
        group.setId(1L);
        when(studentService.findAll()).thenReturn(Arrays.asList(student));
        when(groupService.findAll()).thenReturn(Arrays.asList(group));
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("students/index"))
                .andExpect(model().attribute("active", "students"))
                .andExpect(model().attribute("students", hasSize(1)))
                .andExpect(model().attribute("students", hasItem(
                        allOf(hasProperty("id", is(student.getId())))
                )))
                .andExpect(content().string(containsString("Students List")));
    }
}

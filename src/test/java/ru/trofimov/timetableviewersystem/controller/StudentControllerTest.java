package ru.trofimov.timetableviewersystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.service.GroupService;
import ru.trofimov.timetableviewersystem.service.UserService;

import java.sql.SQLException;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @MockBean
    private GroupService groupService;

    @MockBean
    private UserService userService;

    @Qualifier("userDetailServiceIml")
    @MockBean
    UserDetailsService userDetailsService;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetAllStudents() throws Exception {
        String url = "/students";
        Student student = new Student("Test", "test");
        student.setId(1L);
        student.setGroupId(1L);
        Group group = new Group("Test");
        group.setId(1L);
        when(userService.findAllStudent()).thenReturn(Arrays.asList(student));
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

    @Test
    void shouldGetUnauthorized() throws Exception {
        String url = "/students";
        Student student = new Student("Test", "test");
        student.setId(1L);
        student.setGroupId(1L);
        Group group = new Group("Test");
        group.setId(1L);
        when(userService.findAllStudent()).thenReturn(Arrays.asList(student));
        mockMvc.perform(get(url)).andExpect(status().is(401));
    }

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetErrorMessage() throws Exception {
        String url = "/students";
        when(userService.findAllStudent()).thenThrow(new SQLException());
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("students/index"))
                .andExpect(model().attribute("active", "students"))
                .andExpect(model().attribute("errorMessage", "Failed to load data"))
                .andExpect(content().string(containsString("Students List")));
    }
}

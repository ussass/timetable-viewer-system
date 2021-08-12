package ru.trofimov.timetableviewersystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.trofimov.timetableviewersystem.model.Teacher;
import ru.trofimov.timetableviewersystem.service.TeacherService;
import ru.trofimov.timetableviewersystem.service.UserService;

import java.sql.SQLException;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private UserService userService;

    @Qualifier("userDetailServiceIml")
    @MockBean
    UserDetailsService userDetailsService;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetAllTeachers() throws Exception {
        String url = "/teachers";
        Teacher teacher = new Teacher("Test", "test");
        teacher.setId(1L);
        when(userService.findAllTeacher()).thenReturn(Arrays.asList(teacher));
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("teachers/index"))
                .andExpect(model().attribute("active", "teachers"))
                .andExpect(model().attribute("teachers", hasSize(1)))
                .andExpect(model().attribute("teachers", hasItem(
                        allOf(hasProperty("id", is(teacher.getId())))
                )))
                .andExpect(content().string(containsString("Teachers List")));
    }

    @Test
    void shouldGetUnauthorized() throws Exception {
        String url = "/teachers";
        Teacher teacher = new Teacher("Test", "test");
        teacher.setId(1L);
        when(teacherService.findAll()).thenReturn(Arrays.asList(teacher));
        mockMvc.perform(get(url)).andExpect(status().is(401));
    }

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetErrorMessage() throws Exception {
        String url = "/teachers";
        when(userService.findAllTeacher()).thenThrow(new SQLException());
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("teachers/index"))
                .andExpect(model().attribute("active", "teachers"))
                .andExpect(model().attribute("errorMessage", "Failed to load data"))
                .andExpect(content().string(containsString("Teachers List")));
    }

//    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
//    @Test
//    void shouldGetNewForm() throws Exception {
//        String url = "/teachers/new";
//        mockMvc.perform(get(url))
//                .andExpect(status().isOk())
//                .andExpect(view().name("teachers/new"))
//                .andExpect(model().attribute("active", "teachers"))
//                .andExpect(content().string(containsString("Add new teacher")));
//    }

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetUpdateForm() throws Exception {
        long id = 1L;
        String url = "/teachers/edit/" + id;
        Teacher teacher = new Teacher("Test", "test");
        teacher.setId(id);
        when(userService.findById(id)).thenReturn(teacher);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("teachers/edit"))
                .andExpect(model().attribute("active", "teachers"))
                .andExpect(model().attribute("teacher", is(teacher)))
                .andExpect(content().string(containsString("Edit teacher")));
    }

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetUpdateErrorMessage() throws Exception {
        long id = 1L;
        String url = "/teachers/edit/" + id;
        Teacher teacher = new Teacher("Test", "test");
        teacher.setId(id);
        when(userService.findById(id)).thenThrow(new SQLException());
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("teachers/edit"))
                .andExpect(model().attribute("active", "teachers"))
                .andExpect(model().attribute("errorMessage", "Failed to load data"))
                .andExpect(content().string(containsString("Edit teacher")));
    }
}

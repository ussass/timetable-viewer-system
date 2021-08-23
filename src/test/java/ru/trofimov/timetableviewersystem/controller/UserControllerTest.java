package ru.trofimov.timetableviewersystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.service.UserCourseService;
import ru.trofimov.timetableviewersystem.service.UserGroupService;
import ru.trofimov.timetableviewersystem.service.UserService;

import java.sql.SQLException;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserCourseService userCourseService;

    @MockBean
    private UserGroupService userGroupService;

    @Qualifier("userDetailServiceIml")
    @MockBean
    UserDetailsService userDetailsService;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetAllUsers() throws Exception {
        String url = "/users";
        User user = new User("Test", "test");
        user.setId(1L);
        when(userService.findAll()).thenReturn(Arrays.asList(user));
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("users/index"))
                .andExpect(model().attribute("active", "users"))
                .andExpect(model().attribute("users", hasSize(1)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(hasProperty("id", is(user.getId())))
                )))
                .andExpect(content().string(containsString("Users List")));
    }

    @Test
    void shouldGetUnauthorized() throws Exception {
        String url = "/users";
        User user = new User("Test", "test");
        user.setId(1L);
        when(userService.findAll()).thenReturn(Arrays.asList(user));
        mockMvc.perform(get(url)).andExpect(status().is(401));
    }

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetErrorMessage() throws Exception {
        String url = "/users";
        when(userService.findAll()).thenThrow(new SQLException());
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("users/index"))
                .andExpect(model().attribute("active", "users"))
                .andExpect(model().attribute("errorMessage", "Failed to load data"))
                .andExpect(content().string(containsString("Users List")));
    }

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetUpdateForm() throws Exception {
        long id = 1L;
        String url = "/users/edit/" + id;
        User user = new User("Test", "test");
        user.setId(id);
        when(userService.findById(id)).thenReturn(user);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("users/edit"))
                .andExpect(model().attribute("active", "users"))
                .andExpect(model().attribute("user", is(user)))
                .andExpect(content().string(containsString("Edit user")));
    }

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetUpdateErrorMessage() throws Exception {
        long id = 1L;
        String url = "/users/edit/" + id;
        User user = new User("Test", "test");
        user.setId(id);
        when(userService.findById(id)).thenThrow(new SQLException());
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("users/edit"))
                .andExpect(model().attribute("active", "users"))
                .andExpect(model().attribute("errorMessage", "Failed to load data"))
                .andExpect(content().string(containsString("Edit user")));
    }
}

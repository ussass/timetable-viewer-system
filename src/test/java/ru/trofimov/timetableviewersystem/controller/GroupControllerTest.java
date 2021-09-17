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
import ru.trofimov.timetableviewersystem.service.GroupService;

import java.sql.SQLException;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(GroupController.class)
class GroupControllerTest {

    @MockBean
    private GroupService groupService;

    @Qualifier("userDetailServiceIml")
    @MockBean
    UserDetailsService userDetailsService;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetAllGroups() throws Exception {
        String url = "/groups";
        Group group = new Group("Test");
        group.setId(1L);
        when(groupService.findAll()).thenReturn(Arrays.asList(group));
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("groups/index"))
                .andExpect(model().attribute("active", "groups"))
                .andExpect(model().attribute("groups", hasSize(1)))
                .andExpect(model().attribute("groups", hasItem(
                        allOf(hasProperty("id", is(group.getId())))
                )))
                .andExpect(content().string(containsString("Groups List")));
    }

    @Test
    void shouldGetUnauthorized() throws Exception {
        String url = "/groups";
        Group group = new Group("Test");
        group.setId(1L);
        when(groupService.findAll()).thenReturn(Arrays.asList(group));
        mockMvc.perform(get(url)).andExpect(status().is(401));
    }

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetErrorMessage() throws Exception {
        String url = "/groups";
        when(groupService.findAll()).thenThrow(new SQLException());
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("groups/index"))
                .andExpect(model().attribute("active", "groups"))
                .andExpect(model().attribute("errorMessage", "Failed to load data"))
                .andExpect(content().string(containsString("Groups List")));
    }

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetNewForm() throws Exception {
        String url = "/groups/new";
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("groups/new"))
                .andExpect(model().attribute("active", "groups"))
                .andExpect(content().string(containsString("Add new group")));
    }

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetUpdateForm() throws Exception {
        long id = 1L;
        String url = "/groups/edit/" + id;
        Group group = new Group("Test");
        group.setId(id);
        when(groupService.findById(id)).thenReturn(group);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("groups/edit"))
                .andExpect(model().attribute("active", "groups"))
                .andExpect(model().attribute("group", is(group)))
                .andExpect(content().string(containsString("Edit group")));
    }

    @WithMockUser(roles = {"ADMIN", "STUFF", "TEACHER", "STUDENT"})
    @Test
    void shouldGetUpdateErrorMessage() throws Exception {
        long id = 1L;
        String url = "/groups/edit/" + id;
        Group group = new Group("Test");
        group.setId(id);
        when(groupService.findById(id)).thenThrow(new SQLException());
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("groups/edit"))
                .andExpect(model().attribute("active", "groups"))
                .andExpect(model().attribute("errorMessage", "Failed to load data"))
                .andExpect(content().string(containsString("Edit group")));
    }
}

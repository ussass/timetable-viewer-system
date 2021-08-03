package ru.trofimov.timetableviewersystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.service.GroupService;

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

    @Autowired
    private MockMvc mockMvc;

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

}

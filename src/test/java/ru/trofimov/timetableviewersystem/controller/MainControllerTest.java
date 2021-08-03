package ru.trofimov.timetableviewersystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetMainPage() throws Exception {
        String url = "/";
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("active", "home"))
                .andExpect(content().string(containsString("Timetable viewer system")));
    }

}

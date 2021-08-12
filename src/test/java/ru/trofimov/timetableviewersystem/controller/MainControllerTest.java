package ru.trofimov.timetableviewersystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.trofimov.timetableviewersystem.config.WebSecurityConfig;
import ru.trofimov.timetableviewersystem.service.UserService;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Qualifier("userDetailServiceIml")
    @MockBean
    UserDetailsService userDetailsService;

    @Test
    void shouldGetMainPage() throws Exception {
        String url = "/";
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("active", "home"))
                .andExpect(content().string(containsString("Timetable viewer system")));
    }

    @Test
    void shouldGetSignupForm() throws Exception {
        String url = "/signup";
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"))
                .andExpect(content().string(containsString("Sign-up")));
    }

    @Test
    void shouldGetUnauthorized() throws Exception {
        String url = "/profile";
        mockMvc.perform(get(url)).andExpect(status().is(401));
    }

}

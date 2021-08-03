package ru.trofimov.timetableviewersystem.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.trofimov.timetableviewersystem.dao.jdbc.*;
import ru.trofimov.timetableviewersystem.model.Teacher;
import ru.trofimov.timetableviewersystem.service.ClassesService;
import ru.trofimov.timetableviewersystem.service.TeacherService;

import java.util.Arrays;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @MockBean
    private TeacherService teacherService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetAllGroups() throws Exception {
        String url = "/teachers";
        Teacher entity = new Teacher("Test", "test");
        entity.setId(1L);
        when(teacherService.findAll()).thenReturn(Arrays.asList(entity));
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("teachers/index"))
                .andExpect(model().attribute("active", "teachers"))
                .andExpect(content().string(containsString("Teachers List")));
    }

}

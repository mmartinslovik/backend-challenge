package com.example.taskmanager.rest;

import com.example.taskmanager.api.TaskDto;
import com.example.taskmanager.api.TaskPriority;
import com.example.taskmanager.api.TaskStatus;
import com.example.taskmanager.facade.TaskFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskFacade<Long> taskFacade;

    @Test
    void getAllTasks() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setCreatedAt(LocalDateTime.now());
        taskDto.setUpdatedAt(LocalDateTime.now());
        taskDto.setDueDate(LocalDateTime.now().plusDays(7));
        taskDto.setResolvedAt(null);
        taskDto.setTitle("Example Task");
        taskDto.setDescription("This is a sample task.");
        taskDto.setPriority(TaskPriority.HIGH);
        taskDto.setStatus(TaskStatus.IN_PROGRESS);

        List<TaskDto> allTasks = List.of(taskDto);

        when(taskFacade.findAll()).thenReturn(allTasks);

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("[0].id").value(taskDto.getId()))
                .andExpect(jsonPath("[0].title").value(taskDto.getTitle()))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void getTaskByIdReturns404() throws Exception {
        when(taskFacade.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/tasks/{taskId}", 999L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }
}
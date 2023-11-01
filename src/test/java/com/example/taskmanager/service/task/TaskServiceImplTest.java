package com.example.taskmanager.service.task;

import com.example.taskmanager.api.CreateTaskDto;
import com.example.taskmanager.data.model.Task;
import com.example.taskmanager.data.repository.TaskRepository;
import com.example.taskmanager.mapper.TaskMapper;
import jakarta.validation.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskServiceImplTest {

    @Autowired
    TaskMapper taskMapper;

    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        TaskRepository taskRepository = mock(TaskRepository.class);
        taskService = new TaskServiceImpl(taskRepository, Validation.buildDefaultValidatorFactory().getValidator());

        Task mockTask = new Task();
        mockTask.setId(1L);
        mockTask.setTitle("Sample Task");

        when(taskRepository.save(any(Task.class))).thenReturn(mockTask);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(mockTask));
    }

    @Test
    void createTaskTest() {
        CreateTaskDto createTaskDto = new CreateTaskDto();
        createTaskDto.setTitle("Sample Task");

        Task createdTask = taskService.save(taskMapper.toEntity(createTaskDto));

        assertEquals(1L, createdTask.getId());
        assertEquals("Sample Task", createdTask.getTitle());
    }

    @Test
    void findTaskByIdTest() {
        Optional<Task> retrievedTaskOptional = taskService.findById(1L);

        assertTrue(retrievedTaskOptional.isPresent(), "Task not found");

        Task retrievedTask = retrievedTaskOptional.get();

        assertEquals(1L, retrievedTask.getId());
        assertEquals("Sample Task", retrievedTask.getTitle());
    }
}
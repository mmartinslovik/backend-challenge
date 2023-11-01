package com.example.taskmanager.mapper;

import com.example.taskmanager.api.CreateTaskDto;
import com.example.taskmanager.api.TaskDto;
import com.example.taskmanager.data.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    void toDtoTest() {
        Task taskEntity = new Task();
        taskEntity.setDueDate(LocalDateTime.now());
        taskEntity.setResolvedAt(LocalDateTime.now());
        taskEntity.setTitle("Sample Task");
        taskEntity.setDescription("Sample Description");
        taskEntity.setPriority(com.example.taskmanager.data.model.TaskPriority.HIGH);
        taskEntity.setStatus(com.example.taskmanager.data.model.TaskStatus.IN_PROGRESS);

        TaskDto taskDto = taskMapper.toDto(taskEntity);

        assertEquals(taskEntity.getDueDate(), taskDto.getDueDate());
        assertEquals(taskEntity.getResolvedAt(), taskDto.getResolvedAt());
        assertEquals(taskEntity.getTitle(), taskDto.getTitle());
        assertEquals(taskEntity.getDescription(), taskDto.getDescription());
    }

    @Test
    void toEntityTest() {
        CreateTaskDto taskDto = new CreateTaskDto();
        taskDto.setDueDate(LocalDateTime.now());
        taskDto.setResolvedAt(LocalDateTime.now());
        taskDto.setTitle("Sample Task");
        taskDto.setDescription("Sample Description");
        taskDto.setPriority(com.example.taskmanager.api.TaskPriority.HIGH);
        taskDto.setStatus(com.example.taskmanager.api.TaskStatus.IN_PROGRESS);

        Task taskEntity = taskMapper.toEntity(taskDto);

        assertEquals(taskDto.getDueDate(), taskEntity.getDueDate());
        assertEquals(taskDto.getResolvedAt(), taskEntity.getResolvedAt());
        assertEquals(taskDto.getTitle(), taskEntity.getTitle());
        assertEquals(taskDto.getDescription(), taskEntity.getDescription());
    }
}
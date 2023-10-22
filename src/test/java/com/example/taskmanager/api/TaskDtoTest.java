package com.example.taskmanager.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDateTime;
import java.util.Set;

class TaskDtoTest {

    @Test
    void testValidTaskDto() {
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

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<TaskDto>> violations = validator.validate(taskDto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidTaskDto() {
        TaskDto taskDto = new TaskDto();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<TaskDto>> violations = validator.validate(taskDto);

        assertFalse(violations.isEmpty());

        assertTrue(violations.stream()
                .anyMatch(violation -> "title".equals(violation.getPropertyPath().toString())));
    }
}

package com.example.taskmanager.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "CreateTaskDto",
        description = "Data transfer object representing a Task creation/update."
)
public class CreateTaskDto {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime dueDate;

    private LocalDateTime resolvedAt;

    @NotBlank
    @Size(max = 20)
    private String title;

    @Size(max = 50)
    private String description;

    private TaskPriority priority;

    private TaskStatus status;
}

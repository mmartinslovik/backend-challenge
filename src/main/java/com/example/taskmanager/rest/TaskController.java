package com.example.taskmanager.rest;

import com.example.taskmanager.api.CreateTaskDto;
import com.example.taskmanager.api.TaskDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(
        name = "Tasks",
        description = "Tasks API"
)
public class TaskController {

    @Operation(
            summary = "Fetch all Tasks (ordered).",
            description = "Returns a list of all Tasks."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of all Tasks returned successfully."
    )
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Operation(
            summary = "Fetch Task by id.",
            description = "Returns a Task with the specified id."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Task returned successfully."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Task not found."
                    )
            }
    )
    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(new TaskDto());
    }

    @Operation(
            summary = "Create a Task.",
            description = "Creates a new Task."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Task created successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input."
                    )
            }
    )
    @PostMapping("/tasks")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody CreateTaskDto createTaskDto) {
        return ResponseEntity.ok(new TaskDto());
    }

    @Operation(
            summary = "Update the Task by id.",
            description = "Updates the Task with the specified id."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Task updated and returned successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Task not found."
                    )
            }
    )
    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable(value = "id") Long id, @Valid @RequestBody CreateTaskDto createTaskDto) {
        return ResponseEntity.ok(new TaskDto());
    }

    @Operation(
            summary = "Delete the Task by id.",
            description = "Deletes the Task with the specified id."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Task deleted successfully."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Task not found."
                    )
            }
    )
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.noContent().build();
    }
}

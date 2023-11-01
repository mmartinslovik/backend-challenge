package com.example.taskmanager.mapper;

import com.example.taskmanager.api.CreateTaskDto;
import com.example.taskmanager.api.TaskDto;
import com.example.taskmanager.data.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(CreateTaskDto taskDto);

    TaskDto toDto(Task task);
}

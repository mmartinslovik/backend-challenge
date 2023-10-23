package com.example.taskmanager.facade;

import com.example.taskmanager.api.CreateTaskDto;
import com.example.taskmanager.api.TaskDto;
import com.example.taskmanager.data.model.Task;
import com.example.taskmanager.exceptions.TaskNotFoundException;
import com.example.taskmanager.mapper.TaskMapper;
import com.example.taskmanager.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskFacadeImpl implements TaskFacade<Long> {

    private final TaskMapper taskMapper;
    private final TaskService taskService;

    @Autowired
    public TaskFacadeImpl(TaskMapper taskMapper, TaskService taskService) {
        this.taskMapper = taskMapper;
        this.taskService = taskService;
    }

    @Override
    public TaskDto save(CreateTaskDto createTaskDto) {
        return taskMapper.toDto(taskService.save(taskMapper.toEntity(createTaskDto)));
    }

    @Override
    public Optional<TaskDto> findById(Long id) {
        Task foundEntity = taskService.findById(id)
                .orElseThrow(TaskNotFoundException::new);
        return Optional.ofNullable(taskMapper.toDto(foundEntity));
    }

    @Override
    public List<TaskDto> findAll() {
        return taskService.findAll()
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        Task task = taskService.findById(id)
                .orElseThrow(TaskNotFoundException::new);
        taskService.deleteById(task.getId());
    }

    @Override
    public TaskDto update(Long id, CreateTaskDto createTaskDto) {
        return taskMapper.toDto(taskService.update(id, taskMapper.toEntity(createTaskDto)));
    }
}

package com.example.taskmanager.facade;

import com.example.taskmanager.api.CreateTaskDto;
import com.example.taskmanager.api.TaskDto;

import java.util.List;
import java.util.Optional;

/**
 * @param <K> Key
 */
public interface TaskFacade<K> {

    TaskDto save(CreateTaskDto createTaskDto);

    Optional<TaskDto> findById(K id);

    List<TaskDto> findAll();

    void deleteById(K id);

    TaskDto update(K id, CreateTaskDto createTaskDto);
}

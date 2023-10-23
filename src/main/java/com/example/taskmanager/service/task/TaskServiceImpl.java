package com.example.taskmanager.service.task;

import com.example.taskmanager.data.model.Task;
import com.example.taskmanager.data.repository.TaskRepository;
import com.example.taskmanager.exceptions.TaskNotFoundException;
import com.example.taskmanager.service.common.BaseServiceImpl;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl extends BaseServiceImpl<Task, Long> implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository, Validator validator) {
        super(taskRepository, validator);
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public Task update(Long id, Task updatedTask) {
        Task entityToUpdate = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);

        entityToUpdate.setTitle(entityToUpdate.getTitle());
        entityToUpdate.setDescription(updatedTask.getDescription());
        entityToUpdate.setStatus(updatedTask.getStatus());
        entityToUpdate.setPriority(updatedTask.getPriority());
        entityToUpdate.setResolvedAt(updatedTask.getResolvedAt());
        entityToUpdate.setDueDate(updatedTask.getDueDate());

        return super.save(entityToUpdate);
    }
}

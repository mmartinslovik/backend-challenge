package com.example.taskmanager.service.task;

import com.example.taskmanager.data.model.Task;
import com.example.taskmanager.service.common.BaseService;

public interface TaskService extends BaseService<Task, Long> {

    Task update(Long id, Task updatedTask);
}

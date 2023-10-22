package com.example.taskmanager.data.repository;

import com.example.taskmanager.data.model.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends BaseRepository<Task, Long> {

}

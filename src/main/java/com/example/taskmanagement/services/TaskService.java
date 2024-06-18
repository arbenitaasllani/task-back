package com.example.taskmanagement.services;

import com.example.taskmanagement.dtos.TaskDto;
import java.util.List;
public interface TaskService {
    void addTask(TaskDto taskDto);
    void update(long id, TaskDto updatedTask);
    void removeById(long id);
    TaskDto findById(long id);
    List<TaskDto> findAll();
}
package com.example.taskmanagement.services.impls;

import com.example.taskmanagement.dtos.TaskDto;
import com.example.taskmanagement.mappers.TaskMapper;
import com.example.taskmanagement.repositories.TaskRepository;
import com.example.taskmanagement.services.TaskService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public void addTask(TaskDto taskDto) {
        var entity = taskMapper.toEntity(taskDto);
        taskRepository.save(entity);
    }

    @Override
    public void update(long id, TaskDto updatedTask) {
        var updateEntity = taskRepository.findById(id);
        if (updateEntity.isEmpty())
            throw new RuntimeException("Task with that id not found");
        var entity = updateEntity.get();
        entity.setTaskName(updatedTask.getTaskName());
        entity.setDescription(updatedTask.getDescription());
        entity.setStatus(updatedTask.getStatus());
        entity.setDateAdded(updatedTask.getDateAdded());
        entity.setEstimatedFinishDate(updatedTask.getEstimatedFinishDate());
        entity.setId(updatedTask.getId());
        taskRepository.save(entity);
    }
    @Override
    public void removeById(long id) {
        taskRepository.deleteById(id);
    }
    @Override
    public TaskDto findById(long id) {
        var entity = taskRepository.findById(id);
        if (entity.isEmpty())
            throw new RuntimeException("Task is not found");
        var dto=taskMapper.toDto(entity.get());
        return dto;
    }
    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(taskMapper::toDto).toList();
    }
}
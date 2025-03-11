package com.example.tasker.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.tasker.repository.TaskRepository;
import com.example.tasker.mapper.TaskMapper;
import com.example.tasker.mapper.UserMapper;
import com.example.tasker.model.Priority;
import com.example.tasker.model.TaskDto;
import com.example.tasker.model.TaskEntity;
import com.example.tasker.model.UserEntity;

@Service
public class TaskService {
    
    @Autowired 
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    public List<TaskDto> getTasks(Long userId, LocalDateTime startDate, LocalDateTime endDate, Priority priority, Boolean completedStatus) {
        List<TaskEntity> tasks = taskRepository.findTasksByFilters(userId, startDate, endDate, priority, completedStatus);
        return tasks.stream()
            .map(taskMapper::toDto)
            .collect(Collectors.toList());
    }

    public TaskDto getTask(Long taskId) {
        return taskMapper.toDto(taskRepository.findById(taskId).orElse(null));
    }

    public TaskDto createTask(TaskDto taskDto) {
        TaskEntity newTask = taskMapper.toEntity(taskDto);

        UserEntity user = userMapper.toEntity(userService.getUser(taskDto.getUserId()));
        newTask.setUser(user);

        taskRepository.save(newTask);

        return taskMapper.toDto(newTask);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}

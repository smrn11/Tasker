package com.example.tasker.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.tasker.repository.TaskRepository;
import com.example.tasker.mapper.TaskMapper;
import com.example.tasker.mapper.UserMapper;
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

    public TaskDto getTask(Long taskId) {
        return taskMapper.entityToDto(taskRepository.findById(taskId).orElse(null));
    }

    public TaskDto createTask(TaskDto taskDto) {
        TaskEntity newTask = taskMapper.dtoToEntity(taskDto);

        UserEntity user = userMapper.toEntity(userService.getUser(taskDto.getUserId()));
        newTask.setUser(user);

        taskRepository.save(newTask);

        return taskMapper.entityToDto(newTask);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}

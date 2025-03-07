package com.example.tasker.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.tasker.repository.TaskRepository;
import com.example.tasker.model.TaskEntity;

@Service
public class TaskService {
    
    @Autowired private TaskRepository taskRepository;

    public TaskEntity getTask(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public TaskEntity createTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}

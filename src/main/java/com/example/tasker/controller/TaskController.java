package com.example.tasker.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.tasker.service.TaskService;
import com.example.tasker.mapper.TaskMapper;
import com.example.tasker.model.*;


@RestController
@RequestMapping("/tasker")
public class TaskController {

    private TaskService taskService;
    private TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/{taskId}")
    public TaskDto getTask(@PathVariable Long taskId){
        return taskMapper.entityToDto(taskService.getTask(taskId));
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto createdTaskDto = taskService.createTask(taskDto);
        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}

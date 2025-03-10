package com.example.tasker.mapper;

import org.springframework.stereotype.Component;

import com.example.tasker.model.TaskEntity;
import com.example.tasker.model.TaskDto;

@Component
public class TaskMapper {

    public TaskDto toDto(TaskEntity taskEntity) {
        if (taskEntity == null) {
            return null;
        }

        TaskDto taskDto = new TaskDto();
        taskDto.setId(taskEntity.getId());
        taskDto.setTitle(taskEntity.getTitle());
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setDueDate(taskEntity.getDueDate());
        taskDto.setPriority(taskEntity.getPriority());
        taskDto.setCompleted(taskEntity.isCompleted());
        taskDto.setUserId(taskEntity.getUser().getId());

        return taskDto;
    }

    public TaskEntity toEntity(TaskDto taskDto) {
        if (taskDto == null) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskDto.getId());
        taskEntity.setTitle(taskDto.getTitle());
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setDueDate(taskDto.getDueDate());
        taskEntity.setPriority(taskDto.getPriority());
        taskEntity.setCompleted(taskDto.isCompleted());

        return taskEntity;
    }
}

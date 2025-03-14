package com.example.tasker.mapper;

import org.springframework.stereotype.Component;

import com.example.tasker.model.TaskEntity;
import com.example.tasker.model.TaskDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class TaskMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
    private static final DateTimeFormatter ALTERNATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public TaskDto toDto(TaskEntity taskEntity) {
        if (taskEntity == null) {
            return null;
        }

        TaskDto taskDto = new TaskDto();
        taskDto.setId(taskEntity.getId());
        taskDto.setTitle(taskEntity.getTitle());
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setDueDate(formatInstant(taskEntity.getDueDate()));
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
        taskEntity.setDueDate(parseInstant(taskDto.getDueDate()));
        taskEntity.setPriority(taskDto.getPriority());
        taskEntity.setCompleted(taskDto.isCompleted());

        return taskEntity;
    }

    public String formatInstant(Instant instant) {
        if (instant == null) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(FORMATTER);
    }

    public Instant parseInstant(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.isEmpty()) {
            return null;
        }
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, FORMATTER);
            return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        } catch (DateTimeParseException e) {
            // Try parsing with ISO format
            try {
                return Instant.parse(dateTimeString);
            } catch (DateTimeParseException ex) {
                // Try parsing with alternate format
                try {
                    LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, ALTERNATE_FORMATTER);
                    return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
                } catch (DateTimeParseException exc) {
                    throw new RuntimeException("Failed to parse date: " + dateTimeString, exc);
                }
            }
        }
    }
}

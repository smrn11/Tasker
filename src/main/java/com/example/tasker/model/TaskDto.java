package com.example.tasker.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Priority priority;
    private boolean completed;
    private Long userId;
}

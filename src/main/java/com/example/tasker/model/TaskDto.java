package com.example.tasker.model;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private Instant dueDate;
    private Priority priority;
    private boolean completed;
    private Long userId;
}

package com.task_service.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.task_service.model.Task;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TaskDto {
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;
    private Boolean isClosed;
    private List<Long> workers;

    public TaskDto(Task task) {
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.creationDate = task.getCreationDate();
        this.endDate = task.getEndDate();
        this.isClosed = task.getIsClosed();
        this.workers = task.getWorkers();
    }
}

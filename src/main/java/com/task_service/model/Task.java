package com.task_service.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.task_service.model.request.TaskRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("task")
@Getter@Setter
@NoArgsConstructor
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;
    private Boolean isClosed;
    private Long createUserId;
    private List<Long> workers;

    public Task(TaskRequest request) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.createUserId = request.getCreateUserId();
        this.creationDate = LocalDateTime.now();
        this.isClosed = false;
        this.workers = new ArrayList<>();
    }
}

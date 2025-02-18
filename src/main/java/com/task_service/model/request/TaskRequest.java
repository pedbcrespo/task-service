package com.task_service.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TaskRequest {
    private String title;
    private String description;
    private Long createUserId;
}

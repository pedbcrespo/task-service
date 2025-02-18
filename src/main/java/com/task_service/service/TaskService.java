package com.task_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_service.model.Task;
import com.task_service.model.dto.TaskDto;
import com.task_service.model.request.TaskRequest;
import com.task_service.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public List<TaskDto> getAll() {
        return repository.findAll().stream().map(TaskDto::new).collect(Collectors.toList());
    }

    public TaskDto save(TaskRequest request) {
        Task saved = repository.save(new Task(request));
        return new TaskDto(saved);
    }
}


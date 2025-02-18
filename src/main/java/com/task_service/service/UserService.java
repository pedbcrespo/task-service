package com.task_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_service.model.User;
import com.task_service.model.dto.UserDto;
import com.task_service.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<UserDto> getAll() {
        return repository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    public User save(UserDto request) {
        User newUser = new User(request);
        return repository.save(newUser);
    }
}

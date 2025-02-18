package com.task_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.task_service.model.User;
import com.task_service.model.dto.UserDto;
import com.task_service.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> get() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserDto request) {
        return new ResponseEntity<>(service.save(request), HttpStatus.OK);
    }
}

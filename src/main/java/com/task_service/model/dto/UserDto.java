package com.task_service.model.dto;

import com.task_service.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserDto {
    private String name;
    private String email;

    public UserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}

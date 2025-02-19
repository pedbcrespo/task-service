package com.task_service.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserRequest {
    private String name;
    private String username;
    private String email;
    private String password;
}

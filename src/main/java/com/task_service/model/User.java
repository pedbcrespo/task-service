package com.task_service.model;

import com.task_service.model.request.UserRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="project_user")
@Getter@Setter
@NoArgsConstructor
public class User {
    @Id
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;

    public User(UserRequest request) {
        this.name = request.getName();
        this.username = request.getUsername();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }
}

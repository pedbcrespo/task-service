package com.task_service.model;

import com.task_service.model.dto.UserDto;

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
    private String email;

    public User(UserDto request) {
        this.name = request.getName();
        this.email = request.getEmail();
    }
}

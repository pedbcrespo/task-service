package com.task_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_service.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}

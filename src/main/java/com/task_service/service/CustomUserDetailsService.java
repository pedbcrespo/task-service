package com.task_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.task_service.model.User;
import com.task_service.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(u -> org.springframework.security.core.userdetails.User.builder()
                .username(u.getEmail())
                .password(((UserDetails) u).getPassword())
                .roles("USER")
                .build()
        ).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}

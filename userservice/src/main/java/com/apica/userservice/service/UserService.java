package com.apica.userservice.service;

import com.apica.userservice.dto.UserDTO;
import com.apica.userservice.entity.User;
import com.apica.userservice.kafka.UserEventProducer;
import com.apica.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private  UserRepository repository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private  UserEventProducer kafkaProducer;

    public User register(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRoles(dto.getRoles());
        User saved = repository.save(user);
        kafkaProducer.publish("User registered: " + saved.getUsername());
        return saved;
    }

    public List<User> list() {
        return repository.findAll();
    }

    public User update(Long id, UserDTO dto) {
        User user = repository.findById(id).orElseThrow();
        user.setEmail(dto.getEmail());
        user.setRoles(dto.getRoles());
        User updated = repository.save(user);
        kafkaProducer.publish("User updated: " + updated.getUsername());
        return updated;
    }

    public void delete(Long id) {
        User user = repository.findById(id).orElseThrow();
        repository.delete(user);
        kafkaProducer.publish("User deleted: " + user.getUsername());
    }
}

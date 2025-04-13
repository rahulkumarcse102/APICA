package com.apica.userservice.controller;

import com.apica.userservice.dto.UserDTO;
import com.apica.userservice.entity.User;
import com.apica.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> register(@RequestBody UserDTO dto) {
        User user = service.register(dto);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(service.list());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDTO dto) {
        User user = service.update(id, dto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

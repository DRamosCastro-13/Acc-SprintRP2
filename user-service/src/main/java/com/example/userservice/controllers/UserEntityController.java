package com.example.userservice.controllers;

import com.example.userservice.dto.NewUserDTO;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.models.UserEntity;
import com.example.userservice.services.UserEntityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Service API", description = "API for managing requests related to Users")
public class UserEntityController {

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("/{id}")
    public Mono<UserEntity> getUserById(@PathVariable Long id) {
        return userEntityService.getUserById(id);
    }

    @GetMapping
    public Flux<UserEntity> getAllUsers() {
        return userEntityService.getAllUsers();
    }

    @PostMapping
    public Mono<UserEntity> createUser(@RequestBody NewUserDTO user) {
        UserEntity newUser = new UserEntity(user.email(), user.name(), user.password());
        return userEntityService.createUser(newUser);
    }

    @PutMapping("/{id}")
    public Mono<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        return userEntityService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable Long id) {
        return userEntityService.deleteUser(id);
    }

}

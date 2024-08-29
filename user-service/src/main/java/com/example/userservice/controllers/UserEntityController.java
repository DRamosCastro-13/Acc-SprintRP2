package com.example.userservice.controllers;

import com.example.userservice.dto.NewUserDTO;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.handlers.InvalidUserException;
import com.example.userservice.models.UserEntity;
import com.example.userservice.services.UserEntityService;
import com.example.userservice.validator.UserValidator;
import io.netty.channel.unix.Errors;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Service API", description = "API for managing requests related to Users")
public class UserEntityController {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/{id}")
    public Mono<UserEntity> getUserById(@PathVariable Long id) {
        return userEntityService.getUserById(id);
    }

    @GetMapping
    public Flux<UserEntity> getAllUsers() {
        return userEntityService.getAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<UserEntity>> createUser(@Valid @RequestBody Mono<NewUserDTO> newUser) {
        return newUser.flatMap(user -> {
                    BeanPropertyBindingResult errors = new BeanPropertyBindingResult(user, "user");
                    userValidator.validate(user, errors);

                    if (errors.hasErrors()) {
                        return Mono.error(new InvalidUserException(errors));
                    }
                    UserEntity newUserEntity = new UserEntity(user.email(), user.name(), user.password());
                    return userEntityService.createUser(newUserEntity);
                })
                .map(savedUser -> ResponseEntity.ok(savedUser));
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

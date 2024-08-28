package com.example.userservice.services;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.models.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserEntityService {
    Mono<UserEntity> getUserById(Long id);
    Flux<UserEntity> getAllUsers();
    Mono<UserEntity> createUser(UserEntity user);
    Mono<UserEntity> updateUser(Long id, UserEntity user);
    Mono<Void> deleteUser(Long id);
}

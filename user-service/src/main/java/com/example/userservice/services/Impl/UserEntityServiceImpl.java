package com.example.userservice.services.Impl;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.handlers.NotFoundException;
import com.example.userservice.models.UserEntity;
import com.example.userservice.repositories.UserEntityRepository;
import com.example.userservice.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public Mono<UserEntity> getUserById(Long id) {
        return userEntityRepository.findById(id).switchIfEmpty( Mono.error( new NotFoundException("User not found")) );
    }

    @Override
    public Flux<UserEntity> getAllUsers() {
        return userEntityRepository.findAll().switchIfEmpty( Mono.error(new NotFoundException("No users found")));
    }


    @Override
    public Mono<UserEntity> createUser(UserEntity user) {
        return userEntityRepository.save(user);
    }

    @Override
    public Mono<UserEntity> updateUser(Long id, UserEntity user) {
        return userEntityRepository.findById(id).switchIfEmpty( Mono.error(new NotFoundException("User not found")) )
                .flatMap(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPassword(user.getPassword());
                    return userEntityRepository.save(existingUser);
                });
    }

    @Override
    public Mono<Void> deleteUser(Long id) {
        return userEntityRepository.findById(id).switchIfEmpty( Mono.error(new NotFoundException("User not found")) )
                .flatMap(userEntityRepository::delete);
    }
}

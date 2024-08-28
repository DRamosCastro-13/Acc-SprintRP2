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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserEntityServiceImpl.class);

    @Override
    public Mono<UserEntity> getUserById(Long id) {
        return userEntityRepository.findById(id).switchIfEmpty( Mono.error( new NotFoundException("User not found")));
    }

    @Override
    public Flux<UserEntity> getAllUsers() {
        return userEntityRepository.findAll()
                .doOnSubscribe(subscription -> logger.info("Request to retrieve all users")) // cuando alguien se suscribe se ejecua
                .doOnNext(user -> logger.debug("Retrieved user: {}", user.getName())) // por cada elemento del flux se ejecuta
                .doOnError(error -> logger.error("Error retrieving users: {}", error.getMessage())) // en caso de error se ejecuta
                .doOnComplete(() -> logger.info("Completed retrieving all users")); // cuando se complete la petición
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

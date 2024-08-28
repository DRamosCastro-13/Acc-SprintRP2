package com.example.userservice.repositories;

import com.example.userservice.models.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserEntityRepository extends ReactiveCrudRepository<UserEntity, Long> {
}

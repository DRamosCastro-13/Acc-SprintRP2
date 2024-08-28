package com.example.userservice.dto;

import com.example.userservice.models.UserEntity;
import reactor.core.publisher.Mono;

public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private String password;

    public  UserDTO(UserEntity user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

package com.example.userservice.handlers;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

public class InvalidUserException extends RuntimeException{

    private final BindingResult bindingResult;

    public InvalidUserException(BindingResult
            bindingResult) {
        super("Validation errors occurred");
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}

package com.example.userservice.handlers;

import java.time.LocalDateTime;
import java.util.List;

public class ValidationErrorResponse {

    private String errorMessages;
    private LocalDateTime timestamp;

    public ValidationErrorResponse(String errorMessages) {
        this.errorMessages = errorMessages;
        this.timestamp = LocalDateTime.now();
    }

}
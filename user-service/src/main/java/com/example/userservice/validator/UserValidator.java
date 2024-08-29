package com.example.userservice.validator;
import com.example.userservice.dto.NewUserDTO;
import com.example.userservice.models.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewUserDTO user = (NewUserDTO) target;

        // Name validation
        if (user.name() == null || user.name().isBlank()) {
            errors.rejectValue("name", "user.name.empty", "User name cannot be empty");
        }

        // Email validation
        if (user.email() == null || user.email().isEmpty()) {
            errors.rejectValue("email", "user.email.empty", "User email cannot be empty");
        } else if (!isValidEmail(user.email())) {
            errors.rejectValue("email", "user.email.invalid", "Invalid email format");
        }

        // Password validation
        if (user.password() == null || user.password().isBlank()) {
            errors.rejectValue("password", "user.password.empty", "User password cannot be empty");
        } else if (!isValidPassword(user.password())) {
            errors.rejectValue("password", "user.password.invalid",
                    "Password must be at least 8 characters long, include an uppercase, lower case and at least 2 numbers");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

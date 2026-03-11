package com.practice.springboot.employee.Validation.Validator;

import com.practice.springboot.employee.Validation.Anotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }
        boolean hasUpperCase = password.matches(".*[A-Z].*");
        boolean hasLowerCase = password.matches(".*[a-z].*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()].*");
        boolean hasMinLength = password.length() >= 10;

        return hasUpperCase && hasLowerCase && hasSpecialChar && hasMinLength;
    }
}

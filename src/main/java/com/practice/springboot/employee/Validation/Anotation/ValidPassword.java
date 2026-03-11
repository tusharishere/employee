package com.practice.springboot.employee.Validation.Anotation;

import com.practice.springboot.employee.Validation.Validator.PasswordValidator;
import com.practice.springboot.employee.Validation.Validator.PrimeNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "Password must contain uppercase, lowercase, special character and be minimum 10 characters long";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

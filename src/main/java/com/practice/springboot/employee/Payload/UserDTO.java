package com.practice.springboot.employee.Payload;

import com.practice.springboot.employee.Validation.Anotation.PrimeNumber;
import com.practice.springboot.employee.Validation.Anotation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @PrimeNumber
    private Integer luckyNumber;

    @NotBlank
    @ValidPassword
    private String password;
}

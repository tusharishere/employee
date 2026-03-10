package com.practice.springboot.employee.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentDTO {
    private Long id;

    @NotBlank(message = "Department title cannot be empty")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotNull(message = "isActive field must not be null")
    private Boolean isActive;

}

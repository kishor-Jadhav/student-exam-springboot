package com.practice.p1.DTO.Validation;

import com.practice.p1.Validators.ValidMarks;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentMasterValidationDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Invalid email format")
    private String email;
    
    @Min(value = 20, message = "Age should be greater than 20")
    @Max(value = 100, message = "Age should be less than 100")
    private int age;

    @Pattern(
    regexp = "^[0-9]{10}$",
    message = "Mobile number must be 10 digits"
    )
    private String mobNo;
    
    @ValidMarks()
    private int marks;

}

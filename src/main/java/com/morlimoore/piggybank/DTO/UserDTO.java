package com.morlimoore.piggybank.DTO;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;

//    @NotBlank(message="First name cannot be blank")
    private String first_name;

//    @NotBlank(message="Last name cannot be blank")
    private String last_name;

    @Email(message="Please enter a valid Email address")
    private String email;

    @NotBlank(message="Please a password")
    private String password;

//    @NotBlank(message="Please enter a date of birth")
    private String date_of_birth;

    @CreationTimestamp
    private LocalDateTime created_at;
}

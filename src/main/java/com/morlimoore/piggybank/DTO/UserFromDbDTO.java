package com.morlimoore.piggybank.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserFromDbDTO {
    private Long id;

    private String first_name;

    private String last_name;

    private String email;

    private String date_of_birth;

    private LocalDateTime created_at;

    private Integer active;

    private String roles = "";

    private String permissions = "";
}

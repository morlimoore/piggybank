package com.morlimoore.piggybank.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFetchDTO {

    private Long id;
    private String type;
    private Long amount;
    private LocalDateTime created_at;
    private Long user_id;
}

package com.morlimoore.piggybank.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionMakeDTO {

    private String type;

    @NotBlank(message = "Please specify an amount")
    private Long amount;

    @CreationTimestamp
    private LocalDateTime created_at;

    private Long user_id;
}

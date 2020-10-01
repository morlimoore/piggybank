package com.morlimoore.piggybank.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactionDTO {

    private String type;

    private Long amount;

    @CreationTimestamp
    private LocalDateTime created_at;

    private Long user_id;

    private String recipient_email;

}

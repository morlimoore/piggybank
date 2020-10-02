package com.morlimoore.piggybank.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="transactions")
public class Transaction {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    private String remarks;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}

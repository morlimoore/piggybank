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
@Table(name="users")
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(unique=true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String hash;

    @Column(nullable = false)
    private String date_of_birth;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime created_at;
}

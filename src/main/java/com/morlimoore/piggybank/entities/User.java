package com.morlimoore.piggybank.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private String password;

    @Column(nullable = false)
    private String date_of_birth;

    @Column(nullable=false)
    private String social_password;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime created_at;

    private Integer active;

    private String roles = "";

    private String permissions = "";

    public User(String first_name, String last_name,
                String email, String password, String date_of_birth,
                String roles, String permissions) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.date_of_birth = date_of_birth;
        this.active = 1;
        this.roles = roles;
        this.permissions = permissions;
    }


    public List<String> getRoles() {
        if (roles.length() > 0) {
            return Arrays.asList(roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissions() {
        if (roles.length() > 0) {
            return Arrays.asList(permissions.split(","));
        }
        return new ArrayList<>();
    }
}

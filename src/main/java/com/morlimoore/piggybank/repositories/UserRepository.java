package com.morlimoore.piggybank.repositories;

import com.morlimoore.piggybank.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

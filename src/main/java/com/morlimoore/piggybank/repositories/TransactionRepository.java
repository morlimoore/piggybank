package com.morlimoore.piggybank.repositories;

import com.morlimoore.piggybank.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value="SELECT * FROM transactions WHERE user_id = ?1", nativeQuery = true)
    List<Transaction> findAllTransactionsByUser(Long user_id);
}

package com.morlimoore.piggybank.services;

import com.morlimoore.piggybank.entities.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllUserTransactions(Long user_id);
}

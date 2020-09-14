package com.morlimoore.piggybank.services.impl;

import com.morlimoore.piggybank.entities.Transaction;
import com.morlimoore.piggybank.repositories.TransactionRepository;
import com.morlimoore.piggybank.services.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAllUserTransactions(Long user_id) {
        return transactionRepository.findAllTransactionsByUser(user_id);
    }


}

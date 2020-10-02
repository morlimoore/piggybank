package com.morlimoore.piggybank.services;

import com.morlimoore.piggybank.DTO.UserTransactionDTO;
import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.entities.Transaction;
import com.morlimoore.piggybank.entities.User;

import java.util.List;

public interface BankingService {

    List<Transaction> getUserTransactions(Long user_id);

    Long getUserAccountBalance(Long user_id);

    String getUserAccountBalanceFormatted(Long user_id);

    void withdraw(Transaction transaction, User user, String remarks);

    void deposit(Transaction transaction, User user, String remarks);

    boolean isEligibleToWithdraw(Long user_id, Long amount);

}
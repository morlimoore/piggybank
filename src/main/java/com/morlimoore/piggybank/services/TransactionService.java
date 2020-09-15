package com.morlimoore.piggybank.services;

import com.morlimoore.piggybank.DTO.TransactionFetchDTO;
import com.morlimoore.piggybank.DTO.TransactionMakeDTO;
import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.entities.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllUserTransactions(Long user_id);
    void makeTransaction(TransactionMakeDTO transactionMakeDTO, UserDTO userDTO, String type);
    //    void deposit(TransactionMakeDTO transactionMakeDTO, UserDTO userDTO);
//    void withdrawal(TransactionMakeDTO transactionMakeDTO, UserDTO userDTO);
    Long getUserAccountBalance(Long user_id);
    String getUserAccountBalanceFormatted(Long user_id);
}

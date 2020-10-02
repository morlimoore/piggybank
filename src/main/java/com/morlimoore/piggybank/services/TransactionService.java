package com.morlimoore.piggybank.services;

import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.DTO.UserFromDbDTO;
import com.morlimoore.piggybank.DTO.UserTransactionDTO;

public interface TransactionService {

    void makeTransaction(UserTransactionDTO userTransactionDTO, UserFromDbDTO userDTO, String type);
}

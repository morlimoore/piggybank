package com.morlimoore.piggybank.services.impl;

import com.morlimoore.piggybank.DTO.TransactionFetchDTO;
import com.morlimoore.piggybank.DTO.TransactionMakeDTO;
import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.entities.Transaction;
import com.morlimoore.piggybank.entities.User;
import com.morlimoore.piggybank.repositories.TransactionRepository;
import com.morlimoore.piggybank.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private ModelMapper modelMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Transaction> getAllUserTransactions(Long user_id) {
        List<Transaction> response = transactionRepository.findAllTransactionsByUser(user_id);
        Collections.reverse(response);
        return response;
    }

    @Override
    public void makeTransaction(TransactionMakeDTO transactionMakeDTO, UserDTO userDTO, String type) {
        Transaction transaction = modelMapper.map(transactionMakeDTO, Transaction.class);
        User user = modelMapper.map(userDTO, User.class);
        transaction.setUser(user);
        if (type.equals("Withdrawal")) {
            Long balance = getUserAccountBalance(userDTO.getId());
            transaction.setType("Withdrawal");
            if (transactionMakeDTO.getAmount() < balance) {
                transactionRepository.save(transaction);
            } else {
                System.out.println("Insufficient balance to withdraw");
            }
        } else {
            transaction.setType("Deposit");
            transactionRepository.save(transaction);
        }
    }

    public String getUserAccountBalanceFormatted(Long user_id) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.UK);
        return numberFormat.format(getUserAccountBalance(user_id));
    }

    @Override
    public Long getUserAccountBalance(Long user_id) {
        List<Transaction> transactions = getAllUserTransactions(user_id);
        Long sumOfDeposits = transactionSum(transactions, "Deposit");
        Long sumOfWithdrawals = transactionSum(transactions, "Withdrawal");
        return sumOfDeposits - sumOfWithdrawals;
    }

    private Long transactionSum(List<Transaction> transactions, String transactionType) {
        Long sum = transactions.stream()
                .filter(t -> t.getType().equals(transactionType))
                .map(t -> t.getAmount())
                .reduce((long) 0, (a, b) -> a + b);
        return sum;
    }

}

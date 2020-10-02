package com.morlimoore.piggybank.services.impl;

import com.morlimoore.piggybank.DTO.UserTransactionDTO;
import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.Exceptions.InsufficientBalanceException;
import com.morlimoore.piggybank.entities.Transaction;
import com.morlimoore.piggybank.entities.User;
import com.morlimoore.piggybank.repositories.TransactionRepository;
import com.morlimoore.piggybank.repositories.UserRepository;
import com.morlimoore.piggybank.services.BankingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class BankingServiceImpl implements BankingService {

    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public BankingServiceImpl(TransactionRepository transactionRepository,
                              ModelMapper modelMapper,
                              UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<Transaction> getUserTransactions(Long user_id) {
        List<Transaction> response = transactionRepository.findAllTransactionsByUser(user_id);
        Collections.reverse(response);
        return response;
    }

    @Override
    public void withdraw(Transaction transaction, User user, String remarks)  {
        if (isEligibleToWithdraw(user.getId(), transaction.getAmount())) {
            transaction.setUser(user);
            transaction.setType("Withdrawal");
            transaction.setRemarks(remarks);
            transactionRepository.save(transaction);
        } else {
            throw new InsufficientBalanceException("Insufficient balance to withdraw");
        }
    }

    @Override
    public void deposit(Transaction transaction, User user, String remarks) {
        transaction.setUser(user);
        transaction.setType("Deposit");
        transaction.setRemarks(remarks);
        transactionRepository.save(transaction);
    }

    @Override
    public boolean isEligibleToWithdraw(Long user_id, Long amount) {
        Long balance = getUserAccountBalance(user_id);
        return balance > amount;
    }

    @Override
    public String getUserAccountBalanceFormatted(Long user_id) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.UK);
        return numberFormat.format(getUserAccountBalance(user_id));
    }

    @Override
    public Long getUserAccountBalance(Long user_id) {
        List<Transaction> transactions = getUserTransactions(user_id);
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
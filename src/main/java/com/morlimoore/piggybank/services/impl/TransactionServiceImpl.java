package com.morlimoore.piggybank.services.impl;

import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.DTO.UserFromDbDTO;
import com.morlimoore.piggybank.DTO.UserTransactionDTO;
import com.morlimoore.piggybank.Exceptions.UserNotFoundException;
import com.morlimoore.piggybank.entities.Transaction;
import com.morlimoore.piggybank.entities.User;
import com.morlimoore.piggybank.repositories.UserRepository;
import com.morlimoore.piggybank.services.BankingService;
import com.morlimoore.piggybank.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private ModelMapper modelMapper;
    private BankingService bankingService;
    private UserRepository userRepository;

    private TransactionServiceImpl(ModelMapper modelMapper,
                                   BankingService bankingService,
                                   UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.bankingService = bankingService;
        this.userRepository = userRepository;
    }


    @Override
    public void makeTransaction(UserTransactionDTO userTransactionDTO, UserFromDbDTO userDTO, String type) throws UserNotFoundException {
        if (userTransactionDTO != null && userDTO != null) {
            Transaction transaction = modelMapper.map(userTransactionDTO, Transaction.class);
            User user = modelMapper.map(userDTO, User.class);

            if (type.equals("Withdrawal")) {
                bankingService.withdraw(transaction, user, "By SELF");

            } else if (type.equals("Deposit")) {
                bankingService.deposit(transaction, user, "By SELF");

            } else if (type.equals("Transfer")) {
                Optional<User> optional = userRepository.findUserByEmail(userTransactionDTO.getRecipient_email());
                User recipient = optional.orElseThrow(() -> new UserNotFoundException("Entered email is not a customer"));
                bankingService.withdraw(transaction, user, "TRF-OUT to " + recipient.getEmail());
                Transaction transaction2 = new Transaction();
                transaction2.setAmount(transaction.getAmount());
                bankingService.deposit(transaction2, recipient, "TRF-IN from " + userDTO.getEmail());
            }
        }
    }
}
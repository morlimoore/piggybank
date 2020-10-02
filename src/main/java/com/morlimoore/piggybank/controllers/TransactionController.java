package com.morlimoore.piggybank.controllers;

import com.morlimoore.piggybank.DTO.UserFromDbDTO;
import com.morlimoore.piggybank.DTO.UserTransactionDTO;
import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.Exceptions.InsufficientBalanceException;
import com.morlimoore.piggybank.Exceptions.UserNotFoundException;
import com.morlimoore.piggybank.services.BankingService;
import com.morlimoore.piggybank.services.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * This class handles anything that relates to the transactions a user can carry out
 */
@Controller
public class TransactionController {

    private BankingService bankingService;
    private TransactionService transactionService;

    public TransactionController(BankingService bankingService,
                                 TransactionService transactionService) {
        this.bankingService = bankingService;
        this.transactionService = transactionService;
    }



    @PostMapping("/deposit")
    public String deposit(@Valid UserTransactionDTO userTransactionDTO,
                          HttpSession session,
                          BindingResult bindingResult) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/";
//        }
        if (bindingResult.hasErrors()) {
            return "portal/dashboard";
        }
//        System.out.println(transactionFetchDTO);
        UserFromDbDTO userDTO = (UserFromDbDTO) session.getAttribute("user");
//        System.out.println("userDTO: " + userDTO);
        transactionService.makeTransaction(userTransactionDTO, userDTO, "Deposit");
        return "redirect:/portal/index";
    }

    @PostMapping("/withdrawal")
    public String withdrawal(UserTransactionDTO userTransactionDTO, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/";
//        }
        UserFromDbDTO userDTO = (UserFromDbDTO) session.getAttribute("user");
        transactionService.makeTransaction(userTransactionDTO, userDTO, "Withdrawal");
        return "redirect:/portal/index";
    }

    @PostMapping("/transfer")
    public String transfer(UserTransactionDTO userTransactionDTO, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/";
//        }
        UserFromDbDTO userDTO = (UserFromDbDTO) session.getAttribute("user");
        transactionService.makeTransaction(userTransactionDTO, userDTO, "Transfer");
        return "redirect:/portal/index";
    }

    @ExceptionHandler(value= UserNotFoundException.class)
    public String exceptionHandler() {
        return "userNotFound";
    }

    @ExceptionHandler(value = InsufficientBalanceException.class)
    public String exceptionHandler2() {
        return "insufficientBalance";
    }

}

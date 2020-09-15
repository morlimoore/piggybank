package com.morlimoore.piggybank.controllers;

import com.morlimoore.piggybank.DTO.TransactionFetchDTO;
import com.morlimoore.piggybank.DTO.TransactionMakeDTO;
import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.entities.Transaction;
import com.morlimoore.piggybank.services.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/dashboard")
    public String viewAllTransactions(HttpSession session, Model model) {
        System.out.println("In dashboard: " + session.getAttribute("user"));
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        List<Transaction> transactions = transactionService.getAllUserTransactions(userDTO.getId());
        model.addAttribute("transactions", transactions);
        model.addAttribute("accountBalance", transactionService.getUserAccountBalanceFormatted(userDTO.getId()));
        model.addAttribute("transactionFetchDTO", new TransactionFetchDTO());
        return "dashboard";
    }

    @PostMapping("/deposit")
    public String deposit(TransactionMakeDTO transactionMakeDTO, HttpSession session) {
//        System.out.println(transactionFetchDTO);
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
//        System.out.println("userDTO: " + userDTO);
        transactionService.makeTransaction(transactionMakeDTO, userDTO, "Deposit");
        return "redirect:/dashboard";
    }

    @PostMapping("/withdrawal")
    public String withdrawal(TransactionMakeDTO transactionMakeDTO, HttpSession session) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        transactionService.makeTransaction(transactionMakeDTO, userDTO, "Withdrawal");
        return "redirect:/dashboard";
    }

}

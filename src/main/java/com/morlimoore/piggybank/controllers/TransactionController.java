package com.morlimoore.piggybank.controllers;

import com.morlimoore.piggybank.entities.Transaction;
import com.morlimoore.piggybank.services.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/dashboard")
    public String viewAllTransactions(@RequestParam("user_id") Long user_id, HttpSession session, Model model) {
        System.out.println("In dashboard: " + session.getAttribute("user"));
        List<Transaction> transactions = transactionService.getAllUserTransactions(user_id);
        model.addAttribute("transactions", transactions);
        return "dashboard";
    }



}

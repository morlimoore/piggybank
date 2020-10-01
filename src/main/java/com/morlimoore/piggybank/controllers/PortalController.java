package com.morlimoore.piggybank.controllers;

import com.morlimoore.piggybank.DTO.UserFromDbDTO;
import com.morlimoore.piggybank.DTO.UserTransactionDTO;
import com.morlimoore.piggybank.entities.Transaction;
import com.morlimoore.piggybank.services.BankingService;
import com.morlimoore.piggybank.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/portal")
public class PortalController {

    private UserFromDbDTO userFromDbDTO;
    private UserService userService;
    private BankingService bankingService;

    public PortalController(UserService userService, BankingService bankingService) {
        this.userService = userService;
        this.bankingService = bankingService;
    }

    @GetMapping("index")
    public String index(Principal principal, HttpSession session, Model model, RedirectAttributes ra) {
        try {
            userFromDbDTO = userService.getUser(principal.getName());
        } catch(NoSuchElementException e) {
            ra.addAttribute("flag", "warning");
            return "redirect:/";
        }


        session.setAttribute("user", userFromDbDTO);
        List<Transaction> transactions = bankingService.getUserTransactions(userFromDbDTO.getId());
        model.addAttribute("transactions", transactions);
        model.addAttribute("accountBalance", bankingService.getUserAccountBalanceFormatted(userFromDbDTO.getId()));
        model.addAttribute("userTransactionDTO", new UserTransactionDTO());
        return "portal/dashboard";
    }

    public void setUserFromDbDTO(String email) {
        userFromDbDTO = userService.getUser((email));
    }


//    @ExceptionHandler(value = NoSuchElementException.class)
//    public String exceptionHandler() {
//        return "userNotFound";
//    }
}

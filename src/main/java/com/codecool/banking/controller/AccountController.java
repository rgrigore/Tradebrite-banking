package com.codecool.banking.controller;

import com.codecool.banking.model.AccountBalanceDTO;
import com.codecool.banking.model.NewAccountDTO;
import com.codecool.banking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/**")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/open")
    public String openAccount(NewAccountDTO newAccount) {
        return accountService.openAccount(newAccount);
    }

    @GetMapping("/{accountId}/balance")
    public AccountBalanceDTO accountBalance(@PathVariable String accountId) {
        return accountService.getBalance(accountId);
    }
}

package com.codecool.banking.controller;

import com.codecool.banking.model.AccountBalanceDTO;
import com.codecool.banking.model.NewAccountDTO;
import com.codecool.banking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/open")
    public String openAccount(@RequestBody NewAccountDTO newAccount) {
        return accountService.openAccount(newAccount);
    }

    @GetMapping("/{accountId}/balance")
    public AccountBalanceDTO accountBalance(@PathVariable String accountId) {
        return accountService.getBalanceForAccount(accountId);
    }

    @PostMapping("/{accountId}/deposit/{amount}")
    public void deposit(@PathVariable String accountId, @PathVariable String amount) {
        accountService.depositToAccount(accountId, amount);
    }

    @PostMapping("/{accountId}/withdraw/{amount}")
    public void withdraw(@PathVariable String accountId, @PathVariable String amount) {
        accountService.withdrawFromAccount(accountId, amount);
    }
}

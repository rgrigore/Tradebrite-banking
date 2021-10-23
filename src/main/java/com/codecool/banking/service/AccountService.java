package com.codecool.banking.service;

import com.codecool.banking.exception.exception.account.AccountCreationException;
import com.codecool.banking.exception.exception.account.AccountNotFoundException;
import com.codecool.banking.model.AccountBalanceDTO;
import com.codecool.banking.model.NewAccountDTO;
import com.codecool.banking.model.entity.Account;
import com.codecool.banking.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService {

    private final AccountRepository accountRepository;

    public String openAccount(NewAccountDTO newAccount) {
        try {
            Account account = Account.builder()
                    .userId(newAccount.getUserId())
                    .balance("0.0")
                    .build();

            accountRepository.save(account);
            return account.getId();
        } catch (Exception e) {
            throw new AccountCreationException("Failed to create account.", e);
        }
    }

    public AccountBalanceDTO getBalanceForAccount(String accountId) {
        Account account = getAccount(accountId);

        return AccountBalanceDTO.builder()
                .accountId(account.getId())
                .balance(account.getBalance())
                .build();
    }

    public void depositToAccount(String accountId, String amountString) {
        Account account = getAccount(accountId);

        String newBalance = addStrings(account.getBalance(), amountString);

        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    public void withdrawFromAccount(String accountId, String amountString) {
        Account account = getAccount(accountId);

        String newBalance = subtractStrings(account.getBalance(), amountString);

        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    public Account getAccount(String accountId) {
        return accountRepository.findById(accountId).orElseThrow(() ->
                new AccountNotFoundException(String.format("Account with id: <%s> not found.", accountId))
        );
    }

    public String addStrings(String s1, String s2) {
        return (new BigDecimal(s1)).add(new BigDecimal(s2)).stripTrailingZeros().toString();
    }

    public String subtractStrings(String s1, String s2) {
        return (new BigDecimal(s1)).subtract(new BigDecimal(s2)).stripTrailingZeros().toString();
    }
}

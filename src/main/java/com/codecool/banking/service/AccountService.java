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

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService {

    private final AccountRepository accountRepository;

    public String openAccount(NewAccountDTO newAccount) {
        try {
            Account account = Account.builder()
                    .userId(newAccount.getUserId())
                    .balance(0L)
                    .balanceDecimal(0L)
                    .build();

            accountRepository.save(account);
            return account.getId();
        } catch (Exception e) {
            throw new AccountCreationException("Failed to create account.", e);
        }
    }

    public AccountBalanceDTO getBalance(String accountId) {
        try {
            Account account = accountRepository.getById(accountId);

            return AccountBalanceDTO.builder()
                    .accountId(accountId)
                    .balance(String.format("%d.%d", account.getBalance(), account.getBalanceDecimal()))
                    .build();
        } catch (Exception e) {
            throw new AccountNotFoundException(String.format("Account with id: <%s> not found", accountId), e);
        }
    }
}

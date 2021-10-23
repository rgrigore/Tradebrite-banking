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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService {

    private final AccountRepository accountRepository;
    private final MathUtils mathUtils;


    public Account getAccount(String accountId) {
        return accountRepository.findById(accountId).orElseThrow(() ->
                new AccountNotFoundException(String.format("Account with id: <%s> not found.", accountId))
        );
    }

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

        String newBalance = mathUtils.addStrings(account.getBalance(), amountString);

        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    public void withdrawFromAccount(String accountId, String amountString) {
        Account account = getAccount(accountId);

        String newBalance = mathUtils.subtractStrings(account.getBalance(), amountString);

        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    @Transactional
    public void transferBetweenAccounts(String accountId, String targetId, String amount) {
        withdrawFromAccount(accountId, amount);
        depositToAccount(targetId, amount);
    }
}

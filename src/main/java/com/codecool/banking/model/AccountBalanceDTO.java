package com.codecool.banking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountBalanceDTO {
    private String accountId;
    private String balance;
}

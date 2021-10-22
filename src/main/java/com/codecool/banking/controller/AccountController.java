package com.codecool.banking.controller;

import com.codecool.banking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/**")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {

    private final AccountService accountService;
}

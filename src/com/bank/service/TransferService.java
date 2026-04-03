package com.bank.service;

import com.bank.model.Account;

import java.math.BigDecimal;

public class TransferService {
    public void transfer(Account from, Account to, BigDecimal amount) {
        from.withdraw(amount);
        to.deposit(amount);
    }
}

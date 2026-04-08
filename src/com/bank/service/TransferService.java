package com.bank.service;

import com.bank.exception.BankingException;
import com.bank.model.Account;

import java.math.BigDecimal;
import java.util.Objects;

public class TransferService {
    public void transfer(Account from, Account to, BigDecimal amount) {
        Objects.requireNonNull(from, "source account cannot be null");
        Objects.requireNonNull(to, "target account cannot be null");
        if (from.equals(to)) {
            throw new BankingException("Source and target accounts must be different.");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BankingException("Transfer amount must be greater than zero.");
        }

        from.withdraw(amount);
        to.deposit(amount);
    }
}

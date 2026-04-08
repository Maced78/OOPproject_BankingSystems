package com.bank.model;

import com.bank.exception.BankingException;
import com.bank.strategy.InterestStrategy;

import java.math.BigDecimal;
import java.util.Objects;

public class CheckingAccount extends Account {
    private final BigDecimal overdraftLimit;

    public CheckingAccount(
            String accountNumber,
            Customer owner,
            BigDecimal openingBalance,
            BigDecimal overdraftLimit,
            InterestStrategy interestStrategy
    ) {
        super(accountNumber, owner, openingBalance, interestStrategy);
        this.overdraftLimit = validateOverdraftLimit(overdraftLimit);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        validateAmount(amount);
        BigDecimal allowed = getBalance().add(overdraftLimit);
        if (allowed.compareTo(amount) < 0) {
            throw new BankingException("Overdraft limit exceeded.");
        }
        decreaseBalance(amount);
    }

    private BigDecimal validateOverdraftLimit(BigDecimal overdraftLimit) {
        BigDecimal value = Objects.requireNonNull(overdraftLimit, "overdraftLimit cannot be null");
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new BankingException("Overdraft limit cannot be negative.");
        }
        return value;
    }
}

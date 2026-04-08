package com.bank.model;

import com.bank.exception.BankingException;
import com.bank.interfaces.Identifiable;
import com.bank.interfaces.InterestBearing;
import com.bank.interfaces.Transactional;
import com.bank.strategy.InterestStrategy;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Account implements Identifiable, Transactional, InterestBearing {
    private final String accountNumber;
    private final Customer owner;
    private BigDecimal balance;
    private final InterestStrategy interestStrategy;

    protected Account(String accountNumber, Customer owner, BigDecimal openingBalance, InterestStrategy interestStrategy) {
        this.accountNumber = validateAccountNumber(accountNumber);
        this.owner = Objects.requireNonNull(owner, "owner cannot be null");
        this.balance = Objects.requireNonNull(openingBalance, "openingBalance cannot be null");
        this.interestStrategy = Objects.requireNonNull(interestStrategy, "interestStrategy cannot be null");

        if (openingBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new BankingException("Opening balance cannot be negative.");
        }
    }

    @Override
    public String getId() {
        return accountNumber;
    }

    public Customer getOwner() {
        return owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void deposit(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.add(amount);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        validateAmount(amount);
        if (balance.compareTo(amount) < 0) {
            throw new BankingException("Insufficient balance.");
        }
        decreaseBalance(amount);
    }

    @Override
    public BigDecimal applyMonthlyInterest() {
        BigDecimal interest = interestStrategy.calculate(balance);
        if (interest.compareTo(BigDecimal.ZERO) < 0) {
            throw new BankingException("Calculated interest cannot be negative.");
        }
        balance = balance.add(interest);
        return interest;
    }

    protected void decreaseBalance(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    protected void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BankingException("Amount must be greater than zero.");
        }
    }

    private String validateAccountNumber(String accountNumber) {
        String normalized = Objects.requireNonNull(accountNumber, "accountNumber cannot be null").trim();
        if (normalized.isEmpty()) {
            throw new BankingException("Account number cannot be empty.");
        }
        return normalized;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Account account)) {
            return false;
        }
        return accountNumber.equals(account.accountNumber);
    }

    @Override
    public int hashCode() {
        return accountNumber.hashCode();
    }
}

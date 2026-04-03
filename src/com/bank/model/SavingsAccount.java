package com.bank.model;

import com.bank.strategy.InterestStrategy;

import java.math.BigDecimal;

public class SavingsAccount extends Account {
    public SavingsAccount(String accountNumber, Customer owner, BigDecimal openingBalance, InterestStrategy interestStrategy) {
        super(accountNumber, owner, openingBalance, interestStrategy);
    }
}

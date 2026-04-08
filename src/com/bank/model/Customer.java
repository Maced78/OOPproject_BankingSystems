package com.bank.model;

public class Customer extends Person {
    private final AccountPortfolio portfolio;

    public Customer(String id, String fullName) {
        super(id, fullName);
        this.portfolio = new AccountPortfolio(this);
    }

    public AccountPortfolio getPortfolio() {
        return portfolio;
    }
}

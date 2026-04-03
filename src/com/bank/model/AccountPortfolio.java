package com.bank.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AccountPortfolio {
    private final Customer owner;
    private final List<Account> accounts = new ArrayList<>();

    public AccountPortfolio(Customer owner) {
        this.owner = Objects.requireNonNull(owner, "owner cannot be null");
    }

    public void addAccount(Account account) {
        accounts.add(Objects.requireNonNull(account, "account cannot be null"));
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public Customer getOwner() {
        return owner;
    }
}

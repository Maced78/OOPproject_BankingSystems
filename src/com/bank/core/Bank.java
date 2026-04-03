package com.bank.core;

import com.bank.exception.BankingException;
import com.bank.model.Account;
import com.bank.model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bank {
    private final String bankName;
    private final Map<String, Customer> customers = new HashMap<>();
    private final Map<String, Account> accounts = new HashMap<>();

    public Bank(String bankName) {
        this.bankName = Objects.requireNonNull(bankName, "bankName cannot be null");
    }

    public void registerCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public void openAccount(Account account) {
        if (!customers.containsKey(account.getOwner().getId())) {
            throw new BankingException("Customer must be registered before opening an account.");
        }

        accounts.put(account.getId(), account);
        account.getOwner().getPortfolio().addAccount(account);
    }

    public Account getAccount(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new BankingException("Account not found: " + accountNumber);
        }
        return account;
    }

    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }

    public String getBankName() {
        return bankName;
    }
}

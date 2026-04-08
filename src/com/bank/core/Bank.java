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
        this.bankName = validateBankName(bankName);
    }

    public void registerCustomer(Customer customer) {
        Objects.requireNonNull(customer, "customer cannot be null");
        if (customers.containsKey(customer.getId())) {
            throw new BankingException("Customer already exists with ID: " + customer.getId());
        }
        customers.put(customer.getId(), customer);
    }

    public void openAccount(Account account) {
        Objects.requireNonNull(account, "account cannot be null");
        if (!customers.containsKey(account.getOwner().getId())) {
            throw new BankingException("Customer must be registered before opening an account.");
        }
        if (accounts.containsKey(account.getId())) {
            throw new BankingException("Account already exists with number: " + account.getId());
        }

        accounts.put(account.getId(), account);
        account.getOwner().getPortfolio().addAccount(account);
    }

    public Account getAccount(String accountNumber) {
        String normalized = Objects.requireNonNull(accountNumber, "accountNumber cannot be null").trim();
        Account account = accounts.get(normalized);
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

    private String validateBankName(String bankName) {
        String normalized = Objects.requireNonNull(bankName, "bankName cannot be null").trim();
        if (normalized.isEmpty()) {
            throw new BankingException("Bank name cannot be empty.");
        }
        return normalized;
    }
}

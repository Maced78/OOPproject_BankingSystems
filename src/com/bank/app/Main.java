package com.bank.app;

import com.bank.core.Bank;
import com.bank.model.CheckingAccount;
import com.bank.model.Customer;
import com.bank.model.Loan;
import com.bank.model.SavingsAccount;
import com.bank.service.TransferService;
import com.bank.strategy.FixedRateInterestStrategy;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("OOP Demo Bank");

        Customer ali = new Customer("C001", "Ali Ahmad");
        Customer sara = new Customer("C002", "Sara Hasan");

        bank.registerCustomer(ali);
        bank.registerCustomer(sara);

        SavingsAccount aliSavings = new SavingsAccount(
                "SA-1001",
                ali,
                new BigDecimal("1000.00"),
                new FixedRateInterestStrategy(new BigDecimal("0.01"))
        );

        CheckingAccount saraChecking = new CheckingAccount(
                "CA-2001",
                sara,
                new BigDecimal("500.00"),
                new BigDecimal("200.00"),
                new FixedRateInterestStrategy(new BigDecimal("0.002"))
        );

        bank.openAccount(aliSavings);
        bank.openAccount(saraChecking);

        TransferService transferService = new TransferService();
        transferService.transfer(aliSavings, saraChecking, new BigDecimal("150.00"));

        BigDecimal savingsInterest = aliSavings.applyMonthlyInterest();
        BigDecimal checkingInterest = saraChecking.applyMonthlyInterest();

        Loan loan = new Loan(
                "L-9001",
                ali,
                new BigDecimal("10000.00"),
                new BigDecimal("0.08"),
                24
        );

        System.out.println("=== " + bank.getBankName() + " ===");
        System.out.println("Ali Savings Balance: " + aliSavings.getBalance());
        System.out.println("Sara Checking Balance: " + saraChecking.getBalance());
        System.out.println("Savings Interest Added: " + savingsInterest);
        System.out.println("Checking Interest Added: " + checkingInterest);
        System.out.println("Loan Total Repayment: " + loan.calculateTotalRepayment());
    }
}

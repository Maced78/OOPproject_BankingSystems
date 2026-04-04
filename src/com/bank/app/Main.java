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
        printHeader();

        Bank bank = new Bank("TrustLine Bank");
        TransferService transferService = new TransferService();

        section("1) Registering customers");
        Customer ahmad = new Customer("C001", "Ahmad Nasser");
        Customer lina = new Customer("C002", "Lina Kareem");
        bank.registerCustomer(ahmad);
        bank.registerCustomer(lina);
        System.out.println("- Customer added: " + ahmad.getFullName() + " (" + ahmad.getId() + ")");
        System.out.println("- Customer added: " + lina.getFullName() + " (" + lina.getId() + ")");

        section("2) Opening accounts");
        SavingsAccount ahmadSavings = new SavingsAccount(
                "SA-1001",
                ahmad,
                new BigDecimal("1500.00"),
                new FixedRateInterestStrategy(new BigDecimal("0.01"))
        );

        CheckingAccount linaChecking = new CheckingAccount(
                "CA-2001",
                lina,
                new BigDecimal("600.00"),
                new BigDecimal("300.00"),
                new FixedRateInterestStrategy(new BigDecimal("0.002"))
        );

        bank.openAccount(ahmadSavings);
        bank.openAccount(linaChecking);
        System.out.println("- Opened Savings Account for Ahmad with opening balance: $" + ahmadSavings.getBalance());
        System.out.println("- Opened Checking Account for Lina with opening balance: $" + linaChecking.getBalance());

        section("3) Transfer operation");
        BigDecimal transferAmount = new BigDecimal("220.00");
        transferService.transfer(ahmadSavings, linaChecking, transferAmount);
        System.out.println("- Transfer successful: $" + transferAmount + " from Ahmad's savings to Lina's checking.");
        System.out.println("- Ahmad's savings balance after transfer: $" + ahmadSavings.getBalance());
        System.out.println("- Lina's checking balance after transfer: $" + linaChecking.getBalance());

        section("4) Applying monthly interest");
        BigDecimal savingsInterest = ahmadSavings.applyMonthlyInterest();
        BigDecimal checkingInterest = linaChecking.applyMonthlyInterest();
        System.out.println("- Savings interest credited to Ahmad: $" + savingsInterest);
        System.out.println("- Checking interest credited to Lina: $" + checkingInterest);
        System.out.println("- Ahmad's updated savings balance: $" + ahmadSavings.getBalance());
        System.out.println("- Lina's updated checking balance: $" + linaChecking.getBalance());

        section("5) Loan simulation");
        Loan loan = new Loan(
                "L-9001",
                ahmad,
                new BigDecimal("12000.00"),
                new BigDecimal("0.08"),
                24
        );
        System.out.println("- Loan created for Ahmad: $12000.00 at 8% annual rate for 24 months.");
        System.out.println("- Total estimated repayment: $" + loan.calculateTotalRepayment());

        section("6) Demo summary");
        System.out.println("This demo covered:");
        System.out.println("- Customer registration");
        System.out.println("- Account opening");
        System.out.println("- Money transfer");
        System.out.println("- Monthly interest calculation");
        System.out.println("- Loan repayment estimation");

        System.out.println("\nThank you for reviewing the Banking System project.");
    }

    private static void printHeader() {
        System.out.println("======================================================");
        System.out.println("        Banking System - OOP Java Demonstration       ");
        System.out.println("======================================================");
    }

    private static void section(String title) {
        System.out.println("\n" + title);
        System.out.println("------------------------------------------------------");
    }
}

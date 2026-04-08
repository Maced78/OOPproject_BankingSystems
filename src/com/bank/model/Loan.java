package com.bank.model;

import com.bank.exception.BankingException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Loan {
    private final String loanId;
    private final Customer borrower;
    private final BigDecimal principal;
    private final BigDecimal annualRate;
    private final int durationMonths;

    public Loan(String loanId, Customer borrower, BigDecimal principal, BigDecimal annualRate, int durationMonths) {
        this.loanId = validateLoanId(loanId);
        this.borrower = Objects.requireNonNull(borrower, "borrower cannot be null");
        this.principal = validatePrincipal(principal);
        this.annualRate = validateAnnualRate(annualRate);
        this.durationMonths = validateDuration(durationMonths);
    }

    public BigDecimal calculateTotalRepayment() {
        BigDecimal years = BigDecimal.valueOf(durationMonths).divide(BigDecimal.valueOf(12), 4, RoundingMode.HALF_UP);
        BigDecimal interest = principal.multiply(annualRate).multiply(years);
        return principal.add(interest).setScale(2, RoundingMode.HALF_UP);
    }

    public String getLoanId() {
        return loanId;
    }

    public Customer getBorrower() {
        return borrower;
    }

    private String validateLoanId(String loanId) {
        String normalized = Objects.requireNonNull(loanId, "loanId cannot be null").trim();
        if (normalized.isEmpty()) {
            throw new BankingException("Loan ID cannot be empty.");
        }
        return normalized;
    }

    private BigDecimal validatePrincipal(BigDecimal principal) {
        BigDecimal value = Objects.requireNonNull(principal, "principal cannot be null");
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BankingException("Loan principal must be greater than zero.");
        }
        return value;
    }

    private BigDecimal validateAnnualRate(BigDecimal annualRate) {
        BigDecimal value = Objects.requireNonNull(annualRate, "annualRate cannot be null");
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new BankingException("Annual rate cannot be negative.");
        }
        return value;
    }

    private int validateDuration(int durationMonths) {
        if (durationMonths <= 0) {
            throw new BankingException("Loan duration must be greater than zero months.");
        }
        return durationMonths;
    }
}

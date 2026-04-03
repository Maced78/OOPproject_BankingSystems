package com.bank.model;

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
        this.loanId = Objects.requireNonNull(loanId, "loanId cannot be null");
        this.borrower = Objects.requireNonNull(borrower, "borrower cannot be null");
        this.principal = Objects.requireNonNull(principal, "principal cannot be null");
        this.annualRate = Objects.requireNonNull(annualRate, "annualRate cannot be null");
        this.durationMonths = durationMonths;
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
}

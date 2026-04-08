package com.bank.strategy;

import com.bank.exception.BankingException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class FixedRateInterestStrategy implements InterestStrategy {
    private final BigDecimal monthlyRate;

    public FixedRateInterestStrategy(BigDecimal monthlyRate) {
        BigDecimal value = Objects.requireNonNull(monthlyRate, "monthlyRate cannot be null");
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new BankingException("Monthly rate cannot be negative.");
        }
        this.monthlyRate = value;
    }

    @Override
    public BigDecimal calculate(BigDecimal balance) {
        return balance.multiply(monthlyRate).setScale(2, RoundingMode.HALF_UP);
    }
}

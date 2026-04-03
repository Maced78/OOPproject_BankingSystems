package com.bank.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class FixedRateInterestStrategy implements InterestStrategy {
    private final BigDecimal monthlyRate;

    public FixedRateInterestStrategy(BigDecimal monthlyRate) {
        this.monthlyRate = Objects.requireNonNull(monthlyRate, "monthlyRate cannot be null");
    }

    @Override
    public BigDecimal calculate(BigDecimal balance) {
        return balance.multiply(monthlyRate).setScale(2, RoundingMode.HALF_UP);
    }
}

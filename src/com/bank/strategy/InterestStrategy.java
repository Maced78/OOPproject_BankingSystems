package com.bank.strategy;

import java.math.BigDecimal;

public interface InterestStrategy {
    BigDecimal calculate(BigDecimal balance);
}

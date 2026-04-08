package com.bank.interfaces;

import java.math.BigDecimal;

public interface Transactional {
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount);
}

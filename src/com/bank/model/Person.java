package com.bank.model;

import com.bank.exception.BankingException;
import com.bank.interfaces.Identifiable;

import java.util.Objects;

public abstract class Person implements Identifiable {
    private final String id;
    private final String fullName;

    protected Person(String id, String fullName) {
        this.id = validateId(id);
        this.fullName = validateFullName(fullName);
    }

    @Override
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    private String validateId(String id) {
        String normalized = Objects.requireNonNull(id, "id cannot be null").trim();
        if (normalized.isEmpty()) {
            throw new BankingException("Customer ID cannot be empty.");
        }
        return normalized;
    }

    private String validateFullName(String fullName) {
        String normalized = Objects.requireNonNull(fullName, "fullName cannot be null").trim();
        if (normalized.length() < 3) {
            throw new BankingException("Full name must be at least 3 characters.");
        }

        boolean hasLetter = normalized.chars().anyMatch(Character::isLetter);
        boolean onlyAllowedChars = normalized.chars().allMatch(ch -> Character.isLetter(ch) || ch == ' ' || ch == '-');

        if (!hasLetter || !onlyAllowedChars) {
            throw new BankingException("Full name must contain letters only (spaces and '-' are allowed).");
        }

        return normalized;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Person person)) {
            return false;
        }
        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

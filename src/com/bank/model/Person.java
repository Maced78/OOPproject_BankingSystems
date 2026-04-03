package com.bank.model;

import com.bank.interfaces.Identifiable;

import java.util.Objects;

public abstract class Person implements Identifiable {
    private final String id;
    private final String fullName;

    protected Person(String id, String fullName) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
        this.fullName = Objects.requireNonNull(fullName, "fullName cannot be null");
    }

    @Override
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
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

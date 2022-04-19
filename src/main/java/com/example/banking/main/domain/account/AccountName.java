package com.example.banking.main.domain.account;

import java.util.Objects;

public class AccountName {

    private final String name;

    private AccountName(String name) {
        this.name = name;
    }

    public static AccountName Create(String name) {
        return new AccountName(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountName that = (AccountName) o;
        return Objects.equals(name, that.name);
    }
}

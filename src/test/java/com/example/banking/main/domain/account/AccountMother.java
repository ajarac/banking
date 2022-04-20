package com.example.banking.main.domain.account;

public class AccountMother {
    public static Account random() {
        return Account.Create(AccountName.Create("Random Account"));
    }
}

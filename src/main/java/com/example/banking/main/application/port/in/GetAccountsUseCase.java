package com.example.banking.main.application.port.in;

import com.example.banking.main.application.dto.AccountDTO;
import com.example.banking.main.application.port.out.AccountStorage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAccountsUseCase {

    private final AccountStorage accountStorage;

    public GetAccountsUseCase(AccountStorage accountStorage) {
        this.accountStorage = accountStorage;
    }

    public List<AccountDTO> invoke() {
        return accountStorage.getList().stream().map(AccountDTO::from).collect(Collectors.toList());
    }
}

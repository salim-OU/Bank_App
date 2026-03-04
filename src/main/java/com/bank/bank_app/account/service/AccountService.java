package com.bank.bank_app.account.service;

import com.bank.bank_app.account.dto.AccountDto;
import com.bank.bank_app.account.model.Account;
import com.bank.bank_app.auth_User.model.User;
import com.bank.bank_app.shared.dto.Response;
import com.bank.bank_app.shared.enums.AccountType;

import java.util.List;

public interface AccountService {
    Account createAccount(AccountType accountType, User user);

    Response<List<AccountDto>> getMyAccounts();

    Response<?> closeAccount(String accountNumber);
}
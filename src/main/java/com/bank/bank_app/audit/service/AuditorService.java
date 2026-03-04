package com.bank.bank_app.audit.service;

import com.bank.bank_app.account.dto.AccountDto;
import com.bank.bank_app.auth_User.dto.UserDto;
import com.bank.bank_app.transaction.dto.TransactionDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AuditorService {

    Map<String, Long> getSystemTotals();

    Optional<UserDto> findUserByEmail(String email);

    Optional<AccountDto> findAccountDetailsByAccountNumber(String accountNumber);

    List<TransactionDto> findTransactionsByAccountNumber(String accountNumber);

    Optional<TransactionDto> findTransactionById(Long transactionId);
}
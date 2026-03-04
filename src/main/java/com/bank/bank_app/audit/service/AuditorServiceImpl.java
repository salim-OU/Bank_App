package com.bank.bank_app.audit.service;

import com.bank.bank_app.account.dto.AccountDto;
import com.bank.bank_app.account.repository.AccountRepository;
import com.bank.bank_app.auth_User.dto.UserDto;
import com.bank.bank_app.auth_User.repository.UserRepository;
import com.bank.bank_app.transaction.dto.TransactionDto;
import com.bank.bank_app.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditorServiceImpl implements  AuditorService {
    private final UserRepository userRepo;
    private final AccountRepository accountRepo;
    private final TransactionRepository transactionRepo;
    private final ModelMapper modelMapper;



    @Override
    public Map<String, Long> getSystemTotals() {

        long totalUsers = userRepo.count();
        long totalAccounts = accountRepo.count();
        long totalTransactions = transactionRepo.count();
        return Map.of(
                "totalUsers", totalUsers,
                "totalAccounts", totalAccounts,
                "totalTransactions", totalTransactions
        );
    }
    @Override
    public Optional<UserDto> findUserByEmail(String email) {

        return userRepo.findByEmail(email)
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public Optional<AccountDto> findAccountDetailsByAccountNumber(String accountNumber) {

        return accountRepo.findByAccountNumber(accountNumber)
                .map(account -> modelMapper.map(account, AccountDto.class));
    }

    @Override
    public List<TransactionDto> findTransactionsByAccountNumber(String accountNumber) {

        return transactionRepo.findByAccount_AccountNumber(accountNumber).stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TransactionDto> findTransactionById(Long transactionId) {
        return transactionRepo.findById(transactionId)
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class));
    }
}

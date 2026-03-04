package com.bank.bank_app.transaction.service;

import com.bank.bank_app.account.model.Account;
import com.bank.bank_app.account.repository.AccountRepository;
import com.bank.bank_app.auth_User.service.UserService;
import com.bank.bank_app.notification.service.NotificationService;
import com.bank.bank_app.shared.dto.Response;
import com.bank.bank_app.shared.enums.TransactionStatus;
import com.bank.bank_app.shared.exceptions.InsufficientBalanceException;
import com.bank.bank_app.shared.exceptions.InvalidTransactionException;
import com.bank.bank_app.shared.exceptions.NotFoundException;
import com.bank.bank_app.transaction.dto.TransactionDto;
import com.bank.bank_app.transaction.dto.TransactionRequest;
import com.bank.bank_app.transaction.model.Transaction;
import com.bank.bank_app.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;
    private final NotificationService notificationService;
    private final UserService userService;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public Response<?> createTransaction(TransactionRequest transactionRequest) {

        Transaction transaction = new Transaction();

        transaction.setTransactionType(transactionRequest.getTransactionType());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDescription(transactionRequest.getDescription());

        switch (transactionRequest.getTransactionType()) {
            case DEPOSIT -> handleDeposit(transactionRequest, transaction);
            case WITHDRAWAL -> handleWithdrawal(transactionRequest, transaction);
            case TRANSFER -> handleTransfer(transactionRequest, transaction);
            default -> throw new InvalidTransactionException("Invalid transaction type");
        }

        transaction.setStatus(TransactionStatus.SUCCESS);
        Transaction savedTxn = transactionRepo.save(transaction);

        //send notification out
        //sendTransactionNotifications(savedTxn);


        return Response.builder()
                .statusCode(200)
                .message("Transaction successful")
                .build();


    }

    @Override
    public Response<List<TransactionDto>> getTransactionsForMyAccount(String accountNumber, int page, int size) {
        return null;
    }


    private void handleDeposit(TransactionRequest request, Transaction transaction) {

        Account account = accountRepo.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new NotFoundException("Account not found"));

        account.setBalance(account.getBalance().add(request.getAmount()));
        transaction.setAccount(account);
        accountRepo.save(account);
    }

    private void handleWithdrawal(TransactionRequest request, Transaction transaction) {

        Account account = accountRepo.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new NotFoundException("Account not found"));

        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(request.getAmount()));
        transaction.setAccount(account);
        accountRepo.save(account);
    }

    private void handleTransfer(TransactionRequest request, Transaction transaction) {

        Account sourceAccount = accountRepo.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new NotFoundException("Account not found"));

        Account destination = accountRepo.findByAccountNumber(request.getDestinationAccountNumber())
                .orElseThrow(() -> new NotFoundException("Destination Account not found"));

        if (sourceAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance in source account");

        }

    }

}

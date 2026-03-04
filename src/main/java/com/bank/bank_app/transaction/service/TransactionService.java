package com.bank.bank_app.transaction.service;

import com.bank.bank_app.shared.dto.Response;
import com.bank.bank_app.transaction.dto.TransactionDto;
import com.bank.bank_app.transaction.dto.TransactionRequest;

import java.util.List;

public interface TransactionService {
    Response<?> createTransaction(TransactionRequest transactionRequest);
    Response<List<TransactionDto>> getTransactionsForMyAccount(String accountNumber, int page, int size);
}
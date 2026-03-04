package com.bank.bank_app.audit.controller;


import com.bank.bank_app.account.dto.AccountDto;
import com.bank.bank_app.audit.service.AuditorService;
import com.bank.bank_app.auth_User.dto.UserDto;
import com.bank.bank_app.transaction.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditorController {

    private final AuditorService auditorService;


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('AUDITOR')")
    @GetMapping("/totals")
    public ResponseEntity<Map<String, Long>> getSystemTotals() {
        return ResponseEntity.ok(auditorService.getSystemTotals());
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('AUDITOR')")
    @GetMapping("/users")
    public ResponseEntity<UserDto> findUserByEmail(@RequestParam String email) {

        Optional<UserDto> userDTO = auditorService.findUserByEmail(email);

        return userDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping("/accounts")
    public ResponseEntity<AccountDto> findAccountDetailsByAccountNumber(@RequestParam String accountNumber) {

        Optional<AccountDto> accountDTO = auditorService.findAccountDetailsByAccountNumber(accountNumber);

        return accountDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('AUDITOR')")
    @GetMapping("/transactions/by-account")
    public ResponseEntity<List<TransactionDto>> getTransactionsByAccountNumber(@RequestParam String accountNumber) {

        List<TransactionDto> transactionDTOList = auditorService.findTransactionsByAccountNumber(accountNumber);

        if (transactionDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transactionDTOList);
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('AUDITOR')")
    @GetMapping("/transactions/by-id")
    public ResponseEntity<TransactionDto> getTransactionById(@RequestParam Long id) {

        Optional<TransactionDto> transactionDTO = auditorService.findTransactionById(id);

        return transactionDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}
package com.wallet.api.walletapp.controller;

import com.wallet.api.walletapp.dto.TransactionRequest;
import com.wallet.api.walletapp.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {
    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Void> processTransaction(@RequestBody TransactionRequest request) {
        walletService.processTransaction(
                request.getWalletId(),
                request.getOperationType(),
                request.getAmount());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable UUID walletId) {
        return ResponseEntity.ok(walletService.getBalance(walletId));
    }
}

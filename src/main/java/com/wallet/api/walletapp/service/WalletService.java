package com.wallet.api.walletapp.service;

import com.wallet.api.walletapp.entity.Wallet;
import com.wallet.api.walletapp.exception.InsufficientFundsException;
import com.wallet.api.walletapp.exception.WalletNotFoundException;
import com.wallet.api.walletapp.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {
    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public void processTransaction(UUID walletId, String operationType, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        if (operationType.equals("DEPOSIT")) {
            wallet.setBalance(wallet.getBalance().add(amount));
        } else if (operationType.equals("WITHDRAW")) {
            if (wallet.getBalance().compareTo(amount) < 0) {
                throw new InsufficientFundsException("Insufficient funds");
            }
            wallet.setBalance(wallet.getBalance().subtract(amount));
        }
    }

    public BigDecimal getBalance(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
        return wallet.getBalance();
    }
}

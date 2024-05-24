package com.wallet.api.walletapp.service;

import com.wallet.api.walletapp.dto.WalletRequest;
import com.wallet.api.walletapp.entity.OperationType;
import com.wallet.api.walletapp.entity.Wallet;
import com.wallet.api.walletapp.exception.InsufficientFundsException;
import com.wallet.api.walletapp.exception.WalletNotFoundException;
import com.wallet.api.walletapp.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class WalletService {
    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void updateWallet(WalletRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId())
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        if (request.getOperationType().equals(OperationType.DEPOSIT)) {
            wallet.setBalance(request.getAmount() + wallet.getBalance());
        } else if (request.getOperationType().equals(OperationType.WITHDRAW)) {
            if (wallet.getBalance() < 0 || wallet.getBalance() < request.getAmount()) {
                throw new InsufficientFundsException("Insufficient funds");
            }
            wallet.setBalance(wallet.getBalance() - request.getAmount());
        }
        walletRepository.save(wallet);
    }

    public double getBalance(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
        return wallet.getBalance();
    }
}

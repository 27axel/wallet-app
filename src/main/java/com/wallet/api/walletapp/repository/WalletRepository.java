package com.wallet.api.walletapp.repository;

import com.wallet.api.walletapp.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}

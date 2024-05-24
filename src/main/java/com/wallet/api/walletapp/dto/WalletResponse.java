package com.wallet.api.walletapp.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class WalletResponse {
    private UUID walletId;
    private double balance;
}

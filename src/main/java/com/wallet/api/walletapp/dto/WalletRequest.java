package com.wallet.api.walletapp.dto;

import com.wallet.api.walletapp.entity.OperationType;
import lombok.Data;

import java.util.UUID;

@Data
public class WalletRequest {
    private UUID walletId;
    private OperationType operationType;
    private double amount;
}

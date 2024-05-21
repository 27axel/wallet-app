package com.wallet.api.walletapp.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransactionRequest {
    private UUID walletId;
    private String operationType;
    private BigDecimal amount;
}

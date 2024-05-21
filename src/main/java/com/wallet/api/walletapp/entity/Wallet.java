package com.wallet.api.walletapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class Wallet {
    @Id
    private UUID id;
    private BigDecimal balance;
}

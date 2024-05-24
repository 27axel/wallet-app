package com.wallet.api.walletapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.api.walletapp.dto.WalletRequest;
import com.wallet.api.walletapp.entity.OperationType;
import com.wallet.api.walletapp.entity.Wallet;
import com.wallet.api.walletapp.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WalletController.class)
public class WalletControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WalletService walletService;

    private UUID uuid;
    private Wallet wallet;
    private WalletRequest walletRequest;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        wallet = new Wallet();
        wallet.setId(uuid);
        wallet.setBalance(100L);
        walletRequest = new WalletRequest();
        walletRequest.setAmount(100L);
        walletRequest.setWalletId(uuid);
    }

    @Test
    void getBalanceTest() throws Exception {
        when(walletService.getBalance(uuid)).thenReturn(wallet.getBalance());

        mockMvc.perform(get("/api/v1/wallet/{walletId}", uuid))
                .andExpect(status().isOk());
    }

    @Test
    void depositWalletTest() throws Exception {
        walletRequest.setOperationType(OperationType.DEPOSIT);

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(walletRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(walletService, times(1)).updateWallet(walletRequest);
    }

    @Test
    void withdrawWalletTest() throws Exception {
        walletRequest.setOperationType(OperationType.WITHDRAW);

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(walletRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(walletService, times(1)).updateWallet(walletRequest);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
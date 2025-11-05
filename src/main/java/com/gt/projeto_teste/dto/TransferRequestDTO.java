package com.gt.projeto_teste.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferRequestDTO {
    private String originAccount;
    private String destinationAccount;
    private BigDecimal amount;
    private LocalDate transferDate;

    // --- Getters e Setters ---

    public String getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(String originAccount) {
        this.originAccount = originAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }
}

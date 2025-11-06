package com.gt.projeto_teste.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferRequestDTO {

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Conta de origem deve ter 10 dígitos")
    private String originAccount;

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Conta de destino deve ter 10 dígitos")
    private String destinationAccount;

    @NotNull
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal amount;

    @NotNull
    private LocalDate transferDate;

    public TransferRequestDTO() {}

    public String getOriginAccount() { return originAccount; }
    public void setOriginAccount(String originAccount) { this.originAccount = originAccount; }

    public String getDestinationAccount() { return destinationAccount; }
    public void setDestinationAccount(String destinationAccount) { this.destinationAccount = destinationAccount; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getTransferDate() { return transferDate; }
    public void setTransferDate(LocalDate transferDate) { this.transferDate = transferDate; }
}

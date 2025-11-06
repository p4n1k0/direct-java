package com.gt.projeto_teste.service;

import com.gt.projeto_teste.dto.TransferRequestDTO;
import com.gt.projeto_teste.model.Transfer;
import com.gt.projeto_teste.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class TransferService {

    private final TransferRepository repo;

    public TransferService(TransferRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Transfer createTransfer(TransferRequestDTO req) {
        LocalDate today = LocalDate.now();
        LocalDate transferDate = req.getTransferDate();

        long days = ChronoUnit.DAYS.between(today, transferDate);
        if (days < 0) {
            throw new IllegalArgumentException("Data de transferência não pode ser anterior a hoje.");
        }

        Fee fee = calculateFee(days, req.getAmount());
        if (!fee.isApplicable()) {
            throw new IllegalArgumentException("Não existe taxa aplicável para o período solicitado.");
        }

        Transfer t = new Transfer();
        t.setOriginAccount(req.getOriginAccount());
        t.setDestinationAccount(req.getDestinationAccount());
        t.setAmount(req.getAmount());
        t.setFee(fee.getFeeAmount());
        t.setTransferDate(transferDate);
        t.setSchedulingDate(today);
        t.setCreatedAt(LocalDateTime.now());

        return repo.save(t);
    }

    public Iterable<Transfer> listAll() {
        return repo.findAll();
    }

    private Fee calculateFee(long days, BigDecimal amount) {
        // tabela:
        // 0..0 -> fixa 3.00 + 2.5%
        if (days >= 0 && days <= 0) {
            BigDecimal fixed = BigDecimal.valueOf(3.00);
            BigDecimal pct = amount.multiply(BigDecimal.valueOf(0.025));
            return Fee.applicable(fixed.add(pct));
        }
        // 1..10 -> fixa 12.00 (0%)
        if (days >= 1 && days <= 10) {
            return Fee.applicable(BigDecimal.valueOf(12.00));
        }
        // 11..20 -> 8.2% (sem fixa)
        if (days >= 11 && days <= 20) {
            BigDecimal pct = amount.multiply(BigDecimal.valueOf(0.082));
            return Fee.applicable(pct);
        }
        // 21..30 -> 6.9%
        if (days >= 21 && days <= 30) {
            BigDecimal pct = amount.multiply(BigDecimal.valueOf(0.069));
            return Fee.applicable(pct);
        }
        // 31..40 -> 4.7%
        if (days >= 31 && days <= 40) {
            BigDecimal pct = amount.multiply(BigDecimal.valueOf(0.047));
            return Fee.applicable(pct);
        }
        // 41..50 -> 1.7%
        if (days >= 41 && days <= 50) {
            BigDecimal pct = amount.multiply(BigDecimal.valueOf(0.017));
            return Fee.applicable(pct);
        }

        // fora do intervalo: não aplicável
        return Fee.notApplicable();
    }

    private static class Fee {
        private final boolean applicable;
        private final BigDecimal feeAmount;
        private Fee(boolean ap, BigDecimal amt) {
            applicable = ap;
            feeAmount = amt;
        }
        public static Fee applicable(BigDecimal amt) { return new Fee(true, amt.setScale(2, BigDecimal.ROUND_HALF_EVEN)); }
        public static Fee notApplicable() { return new Fee(false, BigDecimal.ZERO); }
        public boolean isApplicable() { return applicable; }
        public BigDecimal getFeeAmount() { return feeAmount; }
    }
}

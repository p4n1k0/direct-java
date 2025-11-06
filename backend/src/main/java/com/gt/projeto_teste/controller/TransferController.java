package com.gt.projeto_teste.controller;

import com.gt.projeto_teste.dto.TransferRequestDTO;
import com.gt.projeto_teste.dto.TransferResponseDTO;
import com.gt.projeto_teste.model.Transfer;
import com.gt.projeto_teste.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TransferRequestDTO req) {
        try {
            Transfer t = service.createTransfer(req);
            TransferResponseDTO resp = toDto(t);
            return ResponseEntity.ok(resp);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(java.util.Map.of("error", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(java.util.Map.of("error", "Erro interno"));
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<TransferResponseDTO>> list() {
        Iterable<Transfer> list = service.listAll();
        java.util.List<TransferResponseDTO> dtoList = new java.util.ArrayList<>();
        for (Transfer t : list) {
            dtoList.add(toDto(t));
        }
        return ResponseEntity.ok(dtoList);
    }

    private TransferResponseDTO toDto(Transfer t) {
        TransferResponseDTO dto = new TransferResponseDTO();
        dto.setId(t.getId());
        dto.setOriginAccount(t.getOriginAccount());
        dto.setDestinationAccount(t.getDestinationAccount());
        dto.setAmount(t.getAmount());
        dto.setFee(t.getFee());
        dto.setSchedulingDate(t.getSchedulingDate());
        dto.setTransferDate(t.getTransferDate());
        BigDecimal total = t.getAmount().add(t.getFee());
        dto.setTotalAmount(total);
        return dto;
    }
}

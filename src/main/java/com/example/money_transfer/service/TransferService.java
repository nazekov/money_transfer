package com.example.money_transfer.service;

import com.example.money_transfer.model.Transfer;
import com.example.money_transfer.model.dto.TransferDto;
import java.time.LocalDate;
import java.util.List;

public interface TransferService {

    Transfer save(Transfer transfer, Long cashboxId);

    Transfer update(Long cashboxId, String code);

    List<TransferDto> getAllTransfers(Long cashboxId);

    List<TransferDto> findAllTransfersByDate(Long cashboxId, LocalDate localDate);
}

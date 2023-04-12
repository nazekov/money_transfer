package com.example.money_transfer.service.impl;

import com.example.money_transfer.enums.Status;
import com.example.money_transfer.model.Cashbox;
import com.example.money_transfer.model.Transfer;
import com.example.money_transfer.repository.TransferRepository;
import com.example.money_transfer.service.CashboxService;
import com.example.money_transfer.service.TransferService;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    private final CashboxService cashboxService;

    public TransferServiceImpl(TransferRepository transferRepository,
                               CashboxService cashboxService) {
        this.transferRepository = transferRepository;
        this.cashboxService = cashboxService;
    }

    @Override
    public Transfer save(Transfer transfer, Long cashboxId) {
        Cashbox cashbox = cashboxService.findById(cashboxId);

        long code = (long) (Math.random() * 1000000);
        while (transferRepository.existsByUniqueCode(code)) {
            code = (long) (Math.random() * 1000000);
        }
        transfer.setUniqueCode(code);

        transfer.setStatus(Status.CREATED);
        transfer.setCreatedDate(new Date());
        transfer.setCashbox(cashbox);

        transfer = transferRepository.save(transfer);
        transfer.setNumber(transfer.getId());
        transferRepository.save(transfer);
        return transfer;
    }

    @Override
    public Transfer changeStatus() {
        return null;
    }
}

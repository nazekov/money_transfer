package com.example.money_transfer.service.impl;

import com.example.money_transfer.enums.Status;
import com.example.money_transfer.model.Balance;
import com.example.money_transfer.model.Cashbox;
import com.example.money_transfer.model.Transfer;
import com.example.money_transfer.repository.TransferRepository;
import com.example.money_transfer.service.BalanceService;
import com.example.money_transfer.service.CashboxService;
import com.example.money_transfer.service.TransferService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    private final CashboxService cashboxService;

    private final BalanceService balanceService;

    public TransferServiceImpl(TransferRepository transferRepository,
                               CashboxService cashboxService,
                               BalanceService balanceService) {
        this.transferRepository = transferRepository;
        this.cashboxService = cashboxService;
        this.balanceService = balanceService;
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

        transfer.setCommission(transfer.getMoney().multiply(new BigDecimal("0.05")));
        transfer.setTotalMoney(transfer.getMoney().add(transfer.getCommission()));

        transferRepository.save(transfer);
        balanceService.update(cashboxId, transfer.getTotalMoney());
        return transfer;
    }

    @Override
    public Transfer changeStatus() {
        return null;
    }
}

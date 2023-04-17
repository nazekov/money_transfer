package com.example.money_transfer.service.impl;

import com.example.money_transfer.enums.Status;
import com.example.money_transfer.mapper.TransferMapper;
import com.example.money_transfer.model.Cashbox;
import com.example.money_transfer.model.Transfer;
import com.example.money_transfer.model.dto.TransferDto;
import com.example.money_transfer.repository.TransferRepository;
import com.example.money_transfer.service.BalanceService;
import com.example.money_transfer.service.CashboxService;
import com.example.money_transfer.service.TransferService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

        transfer.setCommission(transfer.getMoney().multiply(new BigDecimal("0.05")));
        transfer.setTotalMoney(transfer.getMoney().add(transfer.getCommission()));

        transferRepository.save(transfer);
        balanceService.increase(cashboxId, transfer.getTotalMoney());
        return transfer;
    }

    @Override
    public Transfer update(Long cashboxId, String code) {
        Long ucode = Long.valueOf(code);

        if (isAvailableTransfer(ucode)) {
            Transfer transfer = transferRepository
                            .findByStatusAndUniqueCodeEquals(Status.CREATED, ucode)
                            .orElseThrow(
                                () -> new RuntimeException("transfer not found")
                            );
            BigDecimal moneyTransfer = transfer.getMoney();

            balanceService.decrease(transfer.getCashbox().getId(), moneyTransfer);

            transfer.setCompletedDate(new Date());
            transfer.setStatus(Status.COMPLETED);
            transfer.setRecipientCashboxId(cashboxId);

            if (!cashboxId.equals(transfer.getCashbox().getId())) {
                balanceService.increase(cashboxId, moneyTransfer); // money came to cashbox
                balanceService.decrease(cashboxId, moneyTransfer); // client got money
            }

            // after save new state transfer to db
            return transferRepository.save(transfer);
        }

        return null;
    }

    @Override
    public List<TransferDto> getAllTransfers(Long cashboxId) {
        List<Transfer> allByCashbox_id = transferRepository.findAllByCashbox_Id(cashboxId);
        List<TransferDto> transferDtos =
                TransferMapper.INSTANCE.transferListToTransferDtoList(allByCashbox_id);

        List<Transfer> allByCashboxIdIsNot = transferRepository.findAllByCashboxIdIsNot(cashboxId);
        List<TransferDto> transferDtosIsNot
                = TransferMapper.INSTANCE.transferListToTransferDtoList(allByCashboxIdIsNot);
        transferDtosIsNot.forEach(transferDto -> transferDto.setUniqueCode("******"));

        transferDtos.addAll(transferDtosIsNot);
        return transferDtos;
    }

    private boolean isAvailableTransfer(Long ucode) {
        return transferRepository.existsByUniqueCode(ucode) &&
                !transferRepository.existsByUniqueCodeAndStatusEquals(ucode, Status.COMPLETED);
    }
}

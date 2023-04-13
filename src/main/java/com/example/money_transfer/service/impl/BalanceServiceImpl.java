package com.example.money_transfer.service.impl;

import com.example.money_transfer.model.Balance;
import com.example.money_transfer.repository.BalanceRepository;
import com.example.money_transfer.service.BalanceService;
import com.example.money_transfer.service.CashboxService;
import com.example.money_transfer.utils.DateUtil;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    private final CashboxService cashboxService;

    public BalanceServiceImpl(BalanceRepository balanceRepository,
                              CashboxService cashboxService) {
        this.balanceRepository = balanceRepository;
        this.cashboxService = cashboxService;
    }

    @Override
    public Balance findActualBalanceByCashboxId(Long id) {
        return balanceRepository.findByCashbox_IdAndEndDateEquals(
                                    id,
                                    DateUtil.getInstance().getEndDate()
                                );
    }

    @Override
    public Balance save(Balance balance) {
        return balanceRepository.save(balance);
    }

    @Override
    public Balance update(Long cashboxId, BigDecimal totalTransferMoney) {
        Balance oldBalance = findActualBalanceByCashboxId(cashboxId);
        oldBalance.setEndDate(new Date());
        save(oldBalance);

        Balance newBalance = new Balance();
        newBalance.setBalance(oldBalance.getBalance().add(totalTransferMoney));
        newBalance.setCashbox(cashboxService.findById(cashboxId));
        return save(newBalance);
    }
}

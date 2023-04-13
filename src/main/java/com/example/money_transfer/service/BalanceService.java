package com.example.money_transfer.service;

import com.example.money_transfer.model.Balance;

import java.math.BigDecimal;

public interface BalanceService {

    Balance findActualBalanceByCashboxId(Long id);

    Balance save(Balance balance);

    Balance update(Long cashboxId, BigDecimal totalTransferMoney);
}

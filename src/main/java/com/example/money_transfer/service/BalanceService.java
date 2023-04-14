package com.example.money_transfer.service;

import com.example.money_transfer.model.Balance;

import java.math.BigDecimal;

public interface BalanceService {

    Balance findActualBalanceByCashboxId(Long id);

    Balance save(Balance balance);

    Balance increase(Long cashboxId, BigDecimal money);

    Balance decrease(Long cashboxId, BigDecimal money);
}

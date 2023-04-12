package com.example.money_transfer.service;

import com.example.money_transfer.model.Balance;

public interface BalanceService {

    Balance findActualBalanceByCashboxId(Long id);
}

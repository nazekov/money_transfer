package com.example.money_transfer.service.impl;

import com.example.money_transfer.model.Balance;
import com.example.money_transfer.service.BalanceService;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Override
    public Balance findActualBalanceByCashboxId(Long id) {
        return null;
    }
}

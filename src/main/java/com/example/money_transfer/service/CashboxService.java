package com.example.money_transfer.service;

import com.example.money_transfer.model.Cashbox;

import java.util.List;

public interface CashboxService {

    Cashbox save(Cashbox cashbox);

    List<Cashbox> findAll();
}

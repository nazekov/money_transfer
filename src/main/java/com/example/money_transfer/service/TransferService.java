package com.example.money_transfer.service;

import com.example.money_transfer.model.Transfer;

public interface TransferService {

    Transfer save(Transfer transfer, Long cashboxId);

    Transfer update(Long cashboxId, String code);
}

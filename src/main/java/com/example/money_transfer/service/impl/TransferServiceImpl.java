package com.example.money_transfer.service.impl;

import com.example.money_transfer.model.Transfer;
import com.example.money_transfer.repository.TransferRepository;
import com.example.money_transfer.service.TransferService;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public Transfer save() {
        return null;
    }

    @Override
    public Transfer changeStatus() {
        return null;
    }
}

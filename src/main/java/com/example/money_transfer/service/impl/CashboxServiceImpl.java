package com.example.money_transfer.service.impl;

import com.example.money_transfer.model.Cashbox;
import com.example.money_transfer.repository.CashboxRepository;
import com.example.money_transfer.service.CashboxService;
import com.example.money_transfer.utils.DateUtil;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CashboxServiceImpl implements CashboxService {

    private final CashboxRepository cashboxRepository;

    public CashboxServiceImpl(CashboxRepository cashboxRepositorya) {
        this.cashboxRepository = cashboxRepositorya;
    }

    @Override
    public List<Cashbox> findAll() {
        return cashboxRepository.findAll();
    }

    @Override
    public Cashbox save(Cashbox cashbox) {
        return cashboxRepository.save(cashbox);
    }

    @Override
    public Cashbox findById(long id) {
        return cashboxRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException("Cashbox not found")
                );
    }

}

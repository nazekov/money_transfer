package com.example.money_transfer.controller;

import com.example.money_transfer.enums.Status;
import com.example.money_transfer.model.Transfer;
import com.example.money_transfer.service.CashboxService;
import com.example.money_transfer.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;
    private final CashboxService cashboxService;

    public TransferController(TransferService transferService,
                              CashboxService cashboxService) {
        this.transferService = transferService;
        this.cashboxService = cashboxService;
    }

    @PostMapping("/save/{cashboxId}")
    public String save(@ModelAttribute Transfer transfer,
                                        @PathVariable Long cashboxId) {
        transfer = transferService.save(transfer, cashboxId);
        System.out.println("Transfer: " + transfer);
        return "redirect:/cashbox/" + cashboxId;
    }
}
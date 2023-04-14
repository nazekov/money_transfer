package com.example.money_transfer.controller;

import com.example.money_transfer.model.Transfer;
import com.example.money_transfer.service.CashboxService;
import com.example.money_transfer.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "transfer-successful";
    }

    @PostMapping("/update/{cashboxId}")
    public String update(@PathVariable Long cashboxId,
                       @RequestParam("ucode") String code,
                         Model model) {
        Transfer transfer = transferService.update(cashboxId, code);
        if (transfer != null) {
            model.addAttribute("transfer", transfer);
            return "transfer-update";
        }
        return "transfer-error";
    }
}
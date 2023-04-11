package com.example.money_transfer.controller;

import com.example.money_transfer.service.CashboxService;
import com.example.money_transfer.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;


    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }
}
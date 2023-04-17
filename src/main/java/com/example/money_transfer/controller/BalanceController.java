package com.example.money_transfer.controller;

import com.example.money_transfer.model.Balance;
import com.example.money_transfer.service.BalanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping
    public String getActualBalancesByCashbox(Model model) {
        List<Balance> actualBalances = balanceService.findActualBalances();
        model.addAttribute("balanceList", actualBalances);
        return "cashboxes-balances";
    }
}

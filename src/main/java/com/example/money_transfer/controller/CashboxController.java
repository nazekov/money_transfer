package com.example.money_transfer.controller;

import com.example.money_transfer.model.Cashbox;
import com.example.money_transfer.model.Transfer;
import com.example.money_transfer.service.CashboxService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/cashbox")
public class CashboxController {

    private final CashboxService cashboxService;

    public CashboxController(CashboxService cashboxService) {
        this.cashboxService = cashboxService;
    }

    @GetMapping
    public String getAllCashboxes(Model model) {
        List<Cashbox> cashboxList = cashboxService.findAll();
        model.addAttribute("cashboxList", cashboxList);
        return "list-cashboxes";
    }

    @GetMapping("/{id}")
    public String getCashbox(@PathVariable long id, Model model) {
        Cashbox cashbox = cashboxService.findById(id);
        model.addAttribute("cashbox", cashbox);
        return "cashbox";
    }

    @GetMapping("/form/{cashboxId}")
    public String getViewForCreateTransfer(@PathVariable long cashboxId, Model model) {
        Cashbox cashbox = cashboxService.findById(cashboxId);
        model.addAttribute("cashbox", cashbox);
        model.addAttribute("transfer", new Transfer());
        return "add-transfer";
    }


}

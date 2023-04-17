package com.example.money_transfer.controller;

import com.example.money_transfer.model.Cashbox;
import com.example.money_transfer.model.Transfer;
import com.example.money_transfer.model.dto.TransferDto;
import com.example.money_transfer.service.CashboxService;
import com.example.money_transfer.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, formatter));
            }
        });
    }

    @GetMapping("/form-send/{cashboxId}")
    public String getViewForCreateTransfer(@PathVariable long cashboxId, Model model) {
        Cashbox cashbox = cashboxService.findById(cashboxId);
        model.addAttribute("cashbox", cashbox);
        model.addAttribute("transfer", new Transfer());
        return "add-transfer";
    }

    @GetMapping("/form-get/{cashboxId}")
    public String getViewForGetMoney(@PathVariable long cashboxId, Model model) {
        Cashbox cashbox = cashboxService.findById(cashboxId);
        model.addAttribute("cashbox", cashbox);
        return "form-get-money";
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

    @GetMapping("/all-info/{cashboxId}")
    public String getAllTransfers(@PathVariable Long cashboxId, Model model) {
        List<TransferDto> transferList = transferService.getAllTransfers(cashboxId);
        model.addAttribute("transferList", transferList);
        return "transfer-all-info";
    }

    @GetMapping("/find/{cashboxId}")
    public String findAllTransfers(@PathVariable Long cashboxId, Model model) {
        Cashbox cashbox = cashboxService.findById(cashboxId);
        model.addAttribute("cashbox", cashbox);
        return "transfer-find";
    }

    @PostMapping("/find/by-date/{cashboxId}")
    public String findAllTransfersByDate(@PathVariable Long cashboxId,
                                         @RequestParam("date") LocalDate date,
                                         Model model) {
        List<TransferDto> transferList = transferService.findAllTransfersByDate(cashboxId, date);
        model.addAttribute("transferList", transferList);
        return "transfer-all-info";
    }
}
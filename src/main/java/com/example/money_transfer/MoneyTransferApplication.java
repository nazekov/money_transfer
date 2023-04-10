package com.example.money_transfer;

import com.example.money_transfer.model.Cashbox;
import com.example.money_transfer.service.CashboxService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MoneyTransferApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferApplication.class, args);
    }

    //@Bean
    public CommandLineRunner run(CashboxService cashboxService) {
        return args -> {
            for (int i = 0; i < 2; i++) {
                Cashbox cashbox = new Cashbox();
                cashboxService.save(cashbox);
            }
        };
    }
}

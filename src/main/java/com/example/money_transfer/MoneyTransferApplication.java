package com.example.money_transfer;

import com.example.money_transfer.model.Balance;
import com.example.money_transfer.model.Cashbox;
import com.example.money_transfer.service.CashboxService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class MoneyTransferApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferApplication.class, args);
    }

//    @Bean
    public CommandLineRunner run(CashboxService cashboxService) {
        return args -> {
            for (int i = 0; i < 2; i++) {
                Balance balance = new Balance();
                balance.setBalance(new BigDecimal(100000));

                Cashbox cashbox = new Cashbox();
                cashbox.setBalances(Arrays.asList(balance));

                balance.setCashbox(cashbox);

                cashboxService.save(cashbox);
            }
        };
    }
}

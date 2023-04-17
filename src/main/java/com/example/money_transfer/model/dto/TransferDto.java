package com.example.money_transfer.model.dto;

import com.example.money_transfer.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferDto {

    Long id;

    Status status;

    Date createdDate;

    Date completedDate;

    BigDecimal money;

    BigDecimal commission;

    BigDecimal totalMoney;

    String nameSender;

    String nameRecipient;

    String phoneSender;

    String phoneRecipient;

    String uniqueCode;

    Long cashboxId;

    Long recipientCashboxId;
}

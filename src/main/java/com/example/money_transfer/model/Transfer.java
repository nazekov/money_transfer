package com.example.money_transfer.model;

import com.example.money_transfer.enums.Status;
import com.example.money_transfer.enums.Valuta;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tb_transfers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nameSender;

    String nameRecipient;

    String phoneSender;

    String phoneRecipient;

    @Enumerated(EnumType.STRING)
    Status status;

    Long uniqueCode;

    BigDecimal money;

    BigDecimal commission;

    BigDecimal totalMoney;

    @Enumerated(EnumType.STRING)
    Valuta valuta;

    String comment;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss:S")
    @Column(nullable = false)
    Date createdDate;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss:S")
    Date completedDate;

    @ManyToOne
    @JoinColumn(name = "cashbox_id", referencedColumnName = "id")
    Cashbox cashbox;

    Long recipientCashboxId;
}

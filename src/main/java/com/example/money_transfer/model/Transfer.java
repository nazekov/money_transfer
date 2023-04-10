package com.example.money_transfer.model;

import com.example.money_transfer.enums.Status;
import com.example.money_transfer.enums.Valuta;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

@Entity
@Table(name = "tb_transfers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nameSender;

    String nameRecipient;

    @Enumerated(EnumType.STRING)
    Status status;

    Long uniqueCode;

    double money;

    @Enumerated(EnumType.STRING)
    Valuta valuta;

    String comment;

    @ManyToOne
    @JoinColumn(name = "cashbox_id", referencedColumnName = "id")
    Cashbox cashbox;
}

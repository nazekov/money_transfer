package com.example.money_transfer.model;

import com.example.money_transfer.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_balances")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    double balance;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss:S")
    @Column(nullable = false)
    Date startDate;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss:S")
    @Column(nullable = false)
    Date endDate;

    @ManyToOne
    @JoinColumn(name = "cashbox_id", referencedColumnName = "id")
    Cashbox cashbox;

    @PrePersist
    private void setDates() {
        setStartDate(new Date());
        setEndDate(DateUtil.getInstance().getEndDate());
    }
}

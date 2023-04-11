package com.example.money_transfer.model;

import com.example.money_transfer.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_cashboxes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cashbox {

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

    @OneToMany(mappedBy = "cashbox", cascade = CascadeType.ALL)
    List<Transfer> transfers;

    @PrePersist
    private void setDates() {
        setStartDate(new Date());
        setEndDate(DateUtil.getInstance().getEndDate());
    }
}

package com.example.money_transfer.repository;

import com.example.money_transfer.model.Cashbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.Optional;

@Repository
public interface CashboxRepository extends JpaRepository<Cashbox, Long> {

    Optional<Cashbox> findByIdAndEndDateEquals(long id, Date date);
}

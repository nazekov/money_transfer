package com.example.money_transfer.repository;

import com.example.money_transfer.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    Balance findByCashbox_IdAndEndDateEquals(Long id, Date date);
}

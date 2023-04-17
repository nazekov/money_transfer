package com.example.money_transfer.repository;

import com.example.money_transfer.enums.Status;
import com.example.money_transfer.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    boolean existsByUniqueCode(Long code);

    boolean existsByUniqueCodeAndStatusEquals(Long code, Status status);

    Optional<Transfer> findByStatusAndUniqueCodeEquals(Status status, Long code);

    List<Transfer> findAllByCashbox_Id(Long cashboxId);

    List<Transfer> findAllByCashboxIdIsNot(Long cashboxId);
}

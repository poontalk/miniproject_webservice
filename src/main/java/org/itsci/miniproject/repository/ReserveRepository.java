package org.itsci.miniproject.repository;

import jakarta.transaction.Transactional;
import org.itsci.miniproject.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve,String> {
    List<Reserve> findByStatusAndCustomer_UserId(String status, String customerId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Reserve e WHERE e.reserveId = :id")
    void deleteByReserveTable(String id);
}

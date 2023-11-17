package org.itsci.miniproject.repository;

import jakarta.transaction.Transactional;
import org.itsci.miniproject.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve,String> {
    @Query("SELECT r FROM Reserve r WHERE  r.status = 'reserved' AND r.customer.userId = :customerId")
    List<Reserve> findOngoingOrReservedByCustomerId(@Param("customerId") String customerId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Reserve e WHERE e.reserveId = :id")
    void deleteByReserveTable(String id);
    @Query("SELECT r FROM Reserve r WHERE r.status = 'reserved' ")
    List<Reserve> findOngoingOrReserve();
    @Query("SELECT r FROM Reserve r WHERE r.status = 'complete' AND r.customer.userId = :customerId")
    List<Reserve> getReservesByCustomerCustomerId(String customerId);

    Reserve findByReceiptId(String receiptId);
}

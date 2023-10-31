package org.itsci.miniproject.repository;

import jakarta.transaction.Transactional;
import org.itsci.miniproject.model.Reserve;
import org.itsci.miniproject.model.ReserveDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReserveDetailRepository extends JpaRepository<ReserveDetail, String> {
    List<ReserveDetail> findReserveDetailByReserve_ReserveId(String reserveId);

    @Transactional
    @Modifying
    @Query("DELETE FROM ReserveDetail e WHERE e.reservedetailId = :id")
    void deleteByTable(String id);

    @Query("SELECT rd FROM ReserveDetail rd WHERE rd.reserve.status = 'ongoing' or rd.reserve.status = 'reserved'")
    List<ReserveDetail> findOngoingReserveDetails();
}

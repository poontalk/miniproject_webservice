package org.itsci.miniproject.repository;

import jakarta.transaction.Transactional;
import org.itsci.miniproject.model.Reserve;
import org.itsci.miniproject.response.ReportIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ReserveRepository extends JpaRepository<Reserve,String> {
    @Query("SELECT r FROM Reserve r WHERE  r.status = 'reserved' AND r.customer.userId = :customerId")
    List<Reserve> findOngoingOrReservedByCustomerId(@Param("customerId") String customerId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Reserve e WHERE e.reserveId = :id")
    void deleteByReserveTable(String id);
   /* @Query("SELECT r FROM Reserve r WHERE r.status = 'reserved' ")
    List<Reserve> findOngoingOrReserve();*/
    @Query("SELECT r FROM Reserve r WHERE r.status = 'complete' AND r.customer.userId = :customerId")
    List<Reserve> getReservesByCustomerCustomerId(String customerId);
    Reserve findByReceiptId(String receiptId);
    @Query("select r from Reserve r where r.status = 'reserved' AND r.barber.barberId = :barberId")
    List<Reserve> findReserveByBarberBarberId(String barberId);

    List<Reserve> findByStatusOrderByPayDateAsc(String status);
    @Query(value = "SELECT DATE_FORMAT(pay_date, '%Y-%m') AS month, SUM(total_price) AS totalMonthly FROM Reserve " +
            "WHERE pay_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 6 MONTH) AND CURDATE() AND status = 'complete' " +
            "GROUP BY month ORDER BY pay_date DESC", nativeQuery = true)
    List<Map<String, Object>> findTotalMonthlySales();

    @Query(value = "SELECT DATE_FORMAT(pay_date, '%Y-%m-%d') AS daily, SUM(total_price) AS total " +
            "FROM reserve " +
            "WHERE status = 'complete' " +
            "GROUP BY pay_date " +
            "ORDER BY pay_date DESC ", nativeQuery = true)
    List<Map<String, Object>> findDailyTotal();
    /*@Query("select r.reserveId from Reserve r")
    List<String> findMissingNumber();*/
 }

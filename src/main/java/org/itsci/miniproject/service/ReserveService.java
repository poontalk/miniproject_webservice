package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Reserve;
import org.itsci.miniproject.model.User;
import org.itsci.miniproject.response.ReportIncome;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ReserveService {
    List<Reserve> getAllReserves ();

    Reserve getReserveById(String reserveId);

    Reserve saveReserve (Map<String,String> map);

    Reserve updateReserve (Reserve reserve);

    void deleteReserve(String reserveId);

    List<Reserve> findReserveByStatusAndCustomerId(String customerId);

    List<Reserve> findReserveForBarber(String barberId);

    List<Reserve> getReserveByCustomerId(String customerId);

    Reserve getReceipt(String receiptId);

    Reserve updateConfirmPayment(String reserveId);

    Reserve cancelJob(String reserveId);

    List<ReportIncome> getWeeklyTotal();

    List<Map<String, Object>> getTotalMonthlySales();

    List<Object[]> getDailyTotal();
}

package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Reserve;
import org.itsci.miniproject.model.User;

import java.util.List;
import java.util.Map;

public interface ReserveService {
    List<Reserve> getAllReserves ();

    Reserve getReserveById(String reserveId);

    Reserve saveReserve (Map<String,String> map);

    Reserve updateReserve (Reserve reserve);

    void deleteReserve(String reserveId);

    List<Reserve> findReserveByStatusAndCustomerId(String status,String customerId);
}
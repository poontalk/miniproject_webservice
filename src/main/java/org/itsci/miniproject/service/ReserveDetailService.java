package org.itsci.miniproject.service;

import org.itsci.miniproject.model.ReserveDetail;
import org.itsci.miniproject.response.ScheduleTimeCountDTO;

import java.util.List;
import java.util.Map;

public interface ReserveDetailService {
    List<ReserveDetail> getAllData();

    List<ReserveDetail> getListReserve();

    ReserveDetail getReserveDetailById(String reserveDetailId);

    ReserveDetail saveReserveDetail(Map<String,String> map);

    ReserveDetail updateReserveDetail(ReserveDetail reserveDetail);

    void deleteReserveDetail(String reserveDetailId);

    List<ReserveDetail> findReserveDetailByReserveId(String reserveId);

    List<ReserveDetail> findReserveDetailByStatus();

    List<ScheduleTimeCountDTO> countScheduleTime();

    List<ScheduleTimeCountDTO> findScheduleTimeByUserId(String userId);
}

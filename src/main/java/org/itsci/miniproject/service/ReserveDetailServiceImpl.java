package org.itsci.miniproject.service;

import org.itsci.miniproject.model.*;
import org.itsci.miniproject.repository.BarberRepository;
import org.itsci.miniproject.repository.ReserveDetailRepository;
import org.itsci.miniproject.repository.ReserveRepository;
import org.itsci.miniproject.repository.ServiceRepository;
import org.itsci.miniproject.response.ScheduleTimeCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class ReserveDetailServiceImpl implements ReserveDetailService {

    @Autowired
    private ReserveDetailRepository reserveDetailRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private BarberRepository barberRepository;

    @Override
    public List<ReserveDetail> getAllData() {
        return reserveDetailRepository.findAll();
    }

    @Override
    public ReserveDetail getReserveDetailById(String reserveDetailId) {
        return reserveDetailRepository.getReferenceById(reserveDetailId);
    }

    @Override
    public ReserveDetail saveReserveDetail(Map<String, String> map) {
        String reserveId = generateReserveId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String reserveDetailId = generateReserveDetailId();
        org.itsci.miniproject.model.Service serviceModel = serviceRepository.getServiceByServiceName(map.get("serviceName"));
        LocalDateTime scheduleTime = LocalDateTime.parse(map.get("scheduleDate") + " " + map.get("scheduleTime") + ":00", formatter);
        Reserve reserve = reserveRepository.getReferenceById(reserveId);
        setBarberAfterFindBarber(scheduleTime, reserveId);
        ReserveDetail reserveDetail = new ReserveDetail(reserveDetailId, serviceModel.getPrice(), scheduleTime, serviceModel.getTimespend(), reserve, serviceModel);
        return reserveDetailRepository.save(reserveDetail);
    }

    @Override
    public ReserveDetail updateReserveDetail(ReserveDetail reserveDetail) {
        return reserveDetailRepository.save(reserveDetail);
    }

    @Override
    public void deleteReserveDetail(String reserveDetailId) {
        ReserveDetail reserveDetail = reserveDetailRepository.getReferenceById(reserveDetailId);
        reserveDetailRepository.delete(reserveDetail);
    }

    @Override
    public List<ReserveDetail> findReserveDetailByReserveId(String reserveId) {
        return reserveDetailRepository.findReserveDetailByReserve_ReserveId(reserveId);
    }

    @Override
    public List<ReserveDetail> findReserveDetailByStatus() {
        return reserveDetailRepository.findOngoingReserveDetails();
    }

    @Override
    public List<ScheduleTimeCountDTO> countScheduleTime() {

        //System.out.println(countList);
        return reserveDetailRepository.countByScheduleTime();
    }

    @Override
    public List<ScheduleTimeCountDTO> findScheduleTimeByUserId(String userId) {
        return reserveDetailRepository.findScheduleTimeByUserId(userId);
    }

    public void setBarberAfterFindBarber(LocalDateTime localDateTime, String reserveId) {
        List<Barber> barberList = barberRepository.findAvailableBarbers(localDateTime);
        Reserve reserve = reserveRepository.getReferenceById(reserveId);
        reserve.setBarber(barberList.get(0));
        reserveRepository.save(reserve);
    }

    public long getReserveDetailCount() {
        try {
            return reserveDetailRepository.count();
        } catch (Exception e) {
            return 0;
        }
    }

    public String generateReserveDetailId() {
        String result = "" + (getReserveDetailCount() + 1);
        while (result.length() < 4) {
            result = "0" + result;
        }
        result = "RD" + result;
        return result;
    }

    public long getReserveCount() {
        try {
            return reserveRepository.count();
        } catch (Exception e) {
            return 0;
        }
    }

    public String generateReserveId() {
        String result = "" + (getReserveCount());
        while (result.length() < 4) {
            result = "0" + result;
        }
        result = "R" + result;
        return result;
    }

}

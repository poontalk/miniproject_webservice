package org.itsci.miniproject.service;

import org.itsci.miniproject.model.*;
import org.itsci.miniproject.repository.ReserveDetailRepository;
import org.itsci.miniproject.repository.ReserveRepository;
import org.itsci.miniproject.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class ReserveDetailServiceImpl implements ReserveDetailService{

    @Autowired
    private ReserveDetailRepository reserveDetailRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ReserveRepository reserveRepository;

    @Override
    public List<ReserveDetail> getAllData() {
        return reserveDetailRepository.findAll();
    }

    @Override
    public List<ReserveDetail> getListReserve() {
        return null;
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
        LocalDateTime scheduleDate = LocalDateTime.parse(map.get("scheduleDate")+" "+"00:00", formatter);
        Reserve reserve = reserveRepository.getReferenceById(reserveId);
        ReserveDetail reserveDetail = new ReserveDetail(reserveDetailId,serviceModel.getPrice(),scheduleDate,serviceModel.getTimespend(),reserve,serviceModel);
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

    public long getReserveDetailCount(){
        try{
            return reserveDetailRepository.count();
        }catch (Exception e){
            return 0;
        }
    }

    public String generateReserveDetailId(){
        String result = "" + (getReserveDetailCount() + 1);
        while (result.length() < 4){
            result = "0" + result;
        }
        result = "RD"+ result;
        return result;
    }
    public long getReserveCount(){
        try{
            return reserveRepository.count();
        }catch (Exception e){
            return 0;
        }
    }

    public String generateReserveId(){
        String result = "" + (getReserveCount());
        while (result.length() < 4){
            result = "0" + result;
        }
        result = "R"+ result;
        return result;
    }

}

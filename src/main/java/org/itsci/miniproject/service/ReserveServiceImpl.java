package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Barber;
import org.itsci.miniproject.model.Customer;
import org.itsci.miniproject.model.Reserve;
import org.itsci.miniproject.model.ReserveDetail;
import org.itsci.miniproject.repository.BarberRepository;
import org.itsci.miniproject.repository.CustomerRepository;
import org.itsci.miniproject.repository.ReserveDetailRepository;
import org.itsci.miniproject.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class ReserveServiceImpl implements ReserveService{
    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BarberRepository barberRepository;
    @Autowired
    private ReserveDetailRepository reserveDetailRepository;

    @Override
    public List<Reserve> getAllReserves() {
        return reserveRepository.findAll();
    }

    @Override
    public Reserve getReserveById(String reserveId) {
        return reserveRepository.getReferenceById(reserveId);
    }

    @Override
    public Reserve saveReserve(Map<String, String> map) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dT = LocalDateTime.now();
        String formattedDateTime = dT.format(formatter);
        String reserveId = generateReserveId();
        String status = "ongoing";
        LocalDateTime reserveDate = LocalDateTime.parse(formattedDateTime, formatter);
        LocalDateTime payDate = LocalDateTime.parse(map.get("reserveDate")+" "+"00:00", formatter);
        String receiptId = generateReceiptId();
        Customer customer = customerRepository.getCustomerByUserId(map.get("userId"));
        LocalDateTime scheduleDate = LocalDateTime.parse(map.get("reserveDate")+" "+ "00:00",formatter);
        double totalPrice = Double.parseDouble(map.get("price"));
        //Barber barberId = barberRepository.getReferenceById("B0001");
        Reserve reserve = new Reserve(reserveId,reserveDate,status,totalPrice,payDate,receiptId,scheduleDate,null,customer);
        return reserveRepository.save(reserve);
    }

    @Override
    public Reserve updateReserve(Reserve reserve) {
        return reserveRepository.save(reserve);
    }

    @Override
    public void deleteReserve(String reserveId) {
        Reserve reserve = reserveRepository.getReferenceById(reserveId);
        List<ReserveDetail> reserveDetail = reserveDetailRepository.findReserveDetailByReserve_ReserveId(reserveId);
        for (ReserveDetail detail : reserveDetail) {
            reserveDetailRepository.deleteByTable(detail.getReservedetailId());
        }
         reserveRepository.deleteByReserveTable(reserve.getReserveId());
    }

    @Override
    public List<Reserve> findReserveByStatusAndCustomerId(String customerId) {
            return reserveRepository.findOngoingOrReservedByCustomerId(customerId);
    }

    @Override
    public List<Reserve> findReserveForBarber() {
        return reserveRepository.findOngoingOrReserve();
    }

    @Override
    public List<Reserve> getReserveByCustomerId(String customerId) {
        return reserveRepository.getReservesByCustomerCustomerId(customerId);
    }

    @Override
    public Reserve getReceipt(String receiptId) {
        return reserveRepository.findByReceiptId(receiptId);
    }

    public long getReserveCount(){
        try{
            return reserveRepository.count();
        }catch (Exception e){
            return 0;
        }
    }

    public String generateReserveId(){
        String result = "" + (getReserveCount() + 1);
        while (result.length() < 4){
            result = "0" + result;
        }
        result = "R"+ result;
        return result;
    }

    public String generateReceiptId(){
        String result = "" + (getReserveCount() + 1);
        while (result.length() < 4){
            result = "0" + result;
        }
        result = "RC"+ result;
        return result;
    }
}

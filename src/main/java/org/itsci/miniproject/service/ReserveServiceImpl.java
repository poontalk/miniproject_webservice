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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
        String status = "reserved";
        LocalDateTime reserveDate = LocalDateTime.parse(formattedDateTime, formatter);
        LocalDateTime payDate = LocalDateTime.parse(map.get("scheduleDate")+" "+"00:00", formatter);
        String receiptId = generateReceiptId();
        Customer customer = customerRepository.getCustomerByUserId(map.get("userId"));
        LocalDateTime scheduleDate = LocalDateTime.parse(map.get("scheduleDate")+" "+ "00:00",formatter);
        double totalPrice = Double.parseDouble(map.get("totalPrice"));
        Barber barber = findBarberForReserve(scheduleDate);
        Reserve reserve = new Reserve(reserveId,reserveDate,status,totalPrice,payDate,receiptId,scheduleDate,barber,customer);
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

    @Override
    public Reserve updateConfirmPayment(String reserveId) {
        Reserve reserve = reserveRepository.getReferenceById(reserveId);
        reserve.setStatus("complete");
        return reserveRepository.save(reserve);
    }

    @Override
    public Reserve cancelJob(String reserveId) {
        Reserve reserve = reserveRepository.getReferenceById(reserveId);
        reserve.setStatus("canceled");
        return reserveRepository.save(reserve);
    }

    public Barber findBarberForReserve(LocalDateTime scheduleDate){
        Barber barber2 = new Barber();
       List<Reserve> reserve = reserveRepository.findByScheduleDate(scheduleDate);
       if(reserve.size() == 0){
           List<Barber> barberList = barberRepository.findAll();
           return barberList.get(0);
       }else{
           for (Reserve item : reserve) {

               barber2 = barberRepository.getReferenceById(item.getBarber().getBarberId());
               System.out.println(item.getBarber().getBarberId());
           }
           return barber2;
       }
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

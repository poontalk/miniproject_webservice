package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Barber;
import org.itsci.miniproject.model.Customer;
import org.itsci.miniproject.model.Reserve;
import org.itsci.miniproject.model.ReserveDetail;
import org.itsci.miniproject.repository.BarberRepository;
import org.itsci.miniproject.repository.CustomerRepository;
import org.itsci.miniproject.repository.ReserveDetailRepository;
import org.itsci.miniproject.repository.ReserveRepository;
import org.itsci.miniproject.response.ReportIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ReserveServiceImpl implements ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private CustomerRepository customerRepository;
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
        LocalDateTime payDate = LocalDateTime.parse(map.get("scheduleDate") + " " + "00:00", formatter);
        String receiptId = generateReceiptId();
        Customer customer = customerRepository.getCustomerByUserId(map.get("userId"));
        LocalDateTime scheduleDate = LocalDateTime.parse(map.get("scheduleDate") + " " + "00:00", formatter);
        double totalPrice = Double.parseDouble(map.get("totalPrice"));
        Reserve reserve = new Reserve(reserveId, reserveDate, status, totalPrice, payDate, receiptId, scheduleDate, null, customer);
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
    public List<Reserve> findReserveForBarber(String barberId) {
        LocalDateTime currentTime = LocalDateTime.now();
        List<ReserveDetail> reserveDetailList = reserveDetailRepository.findOngoingReserveDetails();
        int i = 1;
        if(reserveDetailList != null){
            for (ReserveDetail list: reserveDetailList) {
                Duration duration = Duration.between(list.getScheduleTime(),currentTime);
                long hoursDifference = duration.toHours();
                long minutesDifference = duration.toMinutes() % 60;
                System.out.println(i +". Compare value: " + hoursDifference + " " + minutesDifference);
                if(hoursDifference > list.getSumTimeSpend() || (hoursDifference == list.getSumTimeSpend() && minutesDifference > 15)){
                    list.getReserve().setStatus("canceled");
                    reserveRepository.save(list.getReserve());
                }
                i++;
            }
        }
        return reserveRepository.findReserveByBarberBarberId(barberId);
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
    @Override
    public List<Map<String, Object>> getTotalMonthlySales() {
        return reserveRepository.findTotalMonthlySales();
    }
    @Override
    public List<Map<String, Object>> getDailyTotal() {
        return reserveRepository.findDailyTotal();
    }


    @Override
    public List<ReportIncome> getWeeklyTotal() {
        List<Reserve> reserves = reserveRepository.findByStatusOrderByPayDateDesc("complete");
        List<LocalDateTime> betweenDefaultAndWeek  = new ArrayList<>();
        Map<String, Double> weeklyTotalMap = new HashMap<>();

        for (Reserve reserve : reserves) {
            LocalDateTime defaultDate = reserve.getPayDate();
            LocalDateTime WeekAgo = defaultDate.minus(7, ChronoUnit.DAYS);
            LocalDateTime currentTime = defaultDate;
            if(!betweenDefaultAndWeek.contains(currentTime)){
                if (betweenDefaultAndWeek!= null) {
                    betweenDefaultAndWeek.clear();
                }while (currentTime.isAfter(WeekAgo)){
                    betweenDefaultAndWeek.add(currentTime);
                    currentTime = currentTime.minusDays(1);
                }
            }
            String week =  defaultDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            week += " - " + WeekAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (betweenDefaultAndWeek.contains(defaultDate)) {
                   LocalDateTime oldWeek = betweenDefaultAndWeek.get(0);
                   String oldWeeks = oldWeek.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                   oldWeeks += " - " + oldWeek.minus(7,ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                double totalWeek = weeklyTotalMap.getOrDefault(oldWeeks, 0.0);
                totalWeek += reserve.getTotalPrice();
                weeklyTotalMap.put(oldWeeks, totalWeek);
            } else {
                weeklyTotalMap.put(week, reserve.getTotalPrice()); // Add a new entry for the week
            }

        }

        List<ReportIncome> result = new ArrayList<>();
        for (Map.Entry<String, Double> entry : weeklyTotalMap.entrySet()) {
            ReportIncome dto = new ReportIncome(entry.getKey(), entry.getValue());
            result.add(dto);
        }
        Collections.sort(result, Comparator.comparing(ReportIncome::getReportDate).reversed());

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
        String result = "" + (getReserveCount() + 1);
        while (result.length() < 4) {
            result = "0" + result;
        }
        result = "R" + result;
        return result;
    }

    public String generateReceiptId() {
        String result = "" + (getReserveCount() + 1);
        while (result.length() < 4) {
            result = "0" + result;
        }
        result = "RC" + result;
        return result;
    }
}

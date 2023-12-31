package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Barber;
import org.itsci.miniproject.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BarberService {

    List<Barber> getAllBarber();

    Barber getBarberById (String barberId);

    Barber saveBarber(Map<String,String> map);

    Barber updateBarber(Barber barber);
    void deleteBarber(String barberId);

    void deleteByTableId(String barberId);

    void deleteAuthorityLoginById(String barberId);

    long getBarberCount();

    List<Barber> findAvailableBarbers(LocalDateTime localDateTime);

    Barber getBarberByUserId(String userId);

}

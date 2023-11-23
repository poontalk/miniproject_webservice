package org.itsci.miniproject.repository;

import jakarta.transaction.Transactional;
import org.itsci.miniproject.model.Barber;
import org.itsci.miniproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BarberRepository extends JpaRepository<Barber,String> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Barber e WHERE e.barberId = :id")
    void deleteByTableId(String id);

    List<Barber> findByBarberStatus(String status);

    @Query("update Barber b set b.barberStatus = :barberStatus where b.barberId = :barberId")
    void doEditStatus(@Param("barberStatus") String barberStatus, @Param("barberId") String barberId);
}

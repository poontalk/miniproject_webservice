package org.itsci.miniproject.repository;

import jakarta.transaction.Transactional;
import org.itsci.miniproject.model.Barber;
import org.itsci.miniproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BarberRepository extends JpaRepository<Barber,String> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Barber e WHERE e.barberId = :id")
    void deleteByTableId(String id);
}

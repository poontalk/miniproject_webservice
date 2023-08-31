package org.itsci.miniproject.repository;

import org.itsci.miniproject.model.Barber;
import org.itsci.miniproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository extends JpaRepository<Barber,String> {

}

package org.itsci.miniproject.repository;

import org.itsci.miniproject.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<Reserve,String> {

}

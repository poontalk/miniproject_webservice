package org.itsci.miniproject.repository;

import org.itsci.miniproject.model.Owner;
import org.itsci.miniproject.response.ReportIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner,String> {

}

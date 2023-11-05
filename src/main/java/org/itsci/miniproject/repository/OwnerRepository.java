package org.itsci.miniproject.repository;

import org.itsci.miniproject.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,String> {
}

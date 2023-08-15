package org.itsci.miniproject.repository;

import org.itsci.miniproject.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service,String> {
    List<Service> getServicesByServiceNameContainingIgnoreCase(String serviceName);
}

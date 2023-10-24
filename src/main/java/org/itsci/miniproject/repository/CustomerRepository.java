package org.itsci.miniproject.repository;

import org.itsci.miniproject.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,String> {

    Customer getCustomerByUserId (String userId);
}

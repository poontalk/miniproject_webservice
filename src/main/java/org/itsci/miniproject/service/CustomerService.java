package org.itsci.miniproject.service;



import org.itsci.miniproject.model.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(String customerId);

    Customer saveCustomer(Map<String ,String> map);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(String customerId);

    Customer getCustomerByUserId(String userId);
}

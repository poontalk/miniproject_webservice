package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Customer;
import org.itsci.miniproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerRepository.getReferenceById(customerId);
    }

    @Override
    public Customer saveCustomer(Map<String, String> map) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(String customerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        customerRepository.delete(customer);
    }
}

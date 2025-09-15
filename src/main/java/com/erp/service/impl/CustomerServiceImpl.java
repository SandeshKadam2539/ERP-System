package com.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.Customer;
import com.erp.repository.CustomerRepository;
import com.erp.service.CustomerService;
import com.erp.exception.ResourceNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // CREATE
    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer); // ID should be null for new customer
    }

    // READ - Get All
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // READ - Get by ID
    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
    }

    // UPDATE
    @Override
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));

        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setEmail(customerDetails.getEmail());
        existingCustomer.setPhone(customerDetails.getPhone());
        existingCustomer.setAddress(customerDetails.getAddress());
        //existingCustomer.setGstin(customerDetails.getGstin()); // optional field

        return customerRepository.save(existingCustomer);
    }

    // DELETE
    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        customerRepository.delete(customer);
    }
}

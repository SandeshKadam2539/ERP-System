package com.erp.service;

import com.erp.entity.Customer;
import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id); // ✅ missing method added
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
}

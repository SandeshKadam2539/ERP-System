package com.erp.controller;

import com.erp.dto.CustomerDto;
import com.erp.entity.Customer;
import com.erp.service.CustomerService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    // ADMIN and SALES_EXECUTIVE can add customer
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SALES_EXECUTIVE')")
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDto dto) {
        Customer customer = new Customer();
        customer.setId(null);
        customer.setVersion(null);

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        return ResponseEntity.ok(service.addCustomer(customer));
    }


    // ADMIN and SALES_EXECUTIVE can view customers
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SALES_EXECUTIVE')")
    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(service.getAllCustomers());
    }

    // Only ADMIN can update
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
        return ResponseEntity.ok(service.updateCustomer(id, customer));
    }

    // Only ADMIN can delete
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}

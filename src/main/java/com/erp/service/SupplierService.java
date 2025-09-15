package com.erp.service;

import com.erp.entity.Supplier;
import com.erp.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository repository;

    public Supplier addSupplier(Supplier supplier) {
        return repository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return repository.findAll();
    }

    public Supplier updateSupplier(Supplier updatedSupplier) {
        Supplier existing = repository.findById(updatedSupplier.getId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        existing.setName(updatedSupplier.getName());
        existing.setEmail(updatedSupplier.getEmail());
        existing.setPhone(updatedSupplier.getPhone());
        existing.setAddress(updatedSupplier.getAddress());

        // Hibernate handles version automatically
        return repository.save(existing);
    }

    public void deleteSupplier(Long id) {
        repository.deleteById(id);
    }
}

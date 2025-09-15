package com.erp.controller;

import com.erp.dto.SupplierDto;
import com.erp.entity.Supplier;
import com.erp.service.SupplierService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService service;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SALES_EXECUTIVE')")
    @PostMapping
    public ResponseEntity<Supplier> addSupplier(@RequestBody SupplierDto dto) {
        Supplier supplier = new Supplier();
        supplier.setId(null);
        supplier.setVersion(null);  // Important for fresh insert

        supplier.setName(dto.getName());
        supplier.setEmail(dto.getEmail());
        supplier.setPhone(dto.getPhone());
        supplier.setAddress(dto.getAddress());

        return ResponseEntity.ok(service.addSupplier(supplier));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SALES_EXECUTIVE')")
    @GetMapping
    public ResponseEntity<List<Supplier>> getAll() {
        return ResponseEntity.ok(service.getAllSuppliers());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody SupplierDto dto) {
        Supplier supplier = new Supplier();
        supplier.setId(id);
        supplier.setVersion(dto.getVersion());  // Must be passed from frontend

        supplier.setName(dto.getName());
        supplier.setEmail(dto.getEmail());
        supplier.setPhone(dto.getPhone());
        supplier.setAddress(dto.getAddress());

        return ResponseEntity.ok(service.updateSupplier(supplier));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}

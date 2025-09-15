package com.erp.controller;

import com.erp.dto.PurchaseOrderRequestDTO;
import com.erp.dto.PurchaseOrderResponseDTO;
import com.erp.entity.PurchaseOrderStatus;
import com.erp.service.PurchaseOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService service;

    public PurchaseOrderController(PurchaseOrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PurchaseOrderResponseDTO> create(@RequestBody PurchaseOrderRequestDTO dto) {
        return ResponseEntity.ok(service.createPurchaseOrder(dto));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrderResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PurchaseOrderResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam PurchaseOrderStatus status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }
}

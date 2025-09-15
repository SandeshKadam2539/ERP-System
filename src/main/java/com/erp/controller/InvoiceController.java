package com.erp.controller;

import com.erp.dto.*;
import com.erp.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // Create Invoice from Sales Order
    @PostMapping
    public InvoiceResponseDTO generateInvoice(@RequestBody InvoiceRequestDTO dto) {
        return invoiceService.generateInvoice(dto);
    }

    // Mark Invoice as Paid
    @PutMapping("/{id}/status")
    public InvoiceResponseDTO markAsPaid(@PathVariable Long id) {
        return invoiceService.markAsPaid(id);
    }
}

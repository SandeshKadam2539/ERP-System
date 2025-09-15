package com.erp.service;

import com.erp.dto.InvoiceResponseDTO;
import com.erp.entity.Invoice;
import com.erp.entity.SalesOrder;
import com.erp.entity.SalesOrderItem;
import com.erp.repository.InvoiceRepository;
import com.erp.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    public InvoiceResponseDTO generateInvoice(Long salesOrderId) {
        Optional<SalesOrder> optionalSalesOrder = salesOrderRepository.findById(salesOrderId);

        if (optionalSalesOrder.isEmpty()) {
            throw new RuntimeException("Sales Order not found with ID: " + salesOrderId);
        }

        SalesOrder salesOrder = optionalSalesOrder.get();

        // ✅ Total calculation
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (SalesOrderItem item : salesOrder.getItems()) {
            // ensure unitPrice not null
            BigDecimal unitPrice = item.getUnitPrice() != null ? item.getUnitPrice() : BigDecimal.ZERO;
            BigDecimal itemTotal = unitPrice.multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);
        }

        // ✅ 18% GST tax (BigDecimal मध्ये)
        BigDecimal taxPercentage = new BigDecimal("0.18");
        BigDecimal taxAmount = totalAmount.multiply(taxPercentage);

        // ✅ Grand Total = total + tax
        BigDecimal grandTotal = totalAmount.add(taxAmount);

        // ✅ Save invoice entity
        Invoice invoice = new Invoice();
        invoice.setSalesOrder(salesOrder);
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setTotalAmount(totalAmount);
        invoice.setTaxAmount(taxAmount);
        invoice.setGrandTotal(grandTotal);

        invoiceRepository.save(invoice);

        // ✅ Prepare response DTO
        InvoiceResponseDTO invoiceResponseDTO = new InvoiceResponseDTO();
        invoiceResponseDTO.setInvoiceId(invoice.getId());
        invoiceResponseDTO.setSalesOrderId(salesOrder.getId());
        invoiceResponseDTO.setTotalAmount(totalAmount);
        invoiceResponseDTO.setTaxAmount(taxAmount);   // ✅ BigDecimal वापरले
        invoiceResponseDTO.setGrandTotal(grandTotal);

        return invoiceResponseDTO;
    }
}

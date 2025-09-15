package com.erp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderResponseDTO {
    private Long id;
    private String supplierName;
    private LocalDate orderDate;
    private LocalDate expectedDeliveryDate;
    private String status;
    private BigDecimal totalAmount;
    private List<PurchaseOrderItemDetailDTO> items;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public LocalDate getExpectedDeliveryDate() { return expectedDeliveryDate; }
    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) { this.expectedDeliveryDate = expectedDeliveryDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public List<PurchaseOrderItemDetailDTO> getItems() { return items; }
    public void setItems(List<PurchaseOrderItemDetailDTO> items) { this.items = items; }
}

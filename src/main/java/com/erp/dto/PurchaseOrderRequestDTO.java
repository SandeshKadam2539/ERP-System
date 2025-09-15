package com.erp.dto;

import com.erp.entity.PurchaseOrderStatus;
import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderRequestDTO {
    private Long supplierId;
    private LocalDate orderDate;
    private LocalDate expectedDeliveryDate;
    private PurchaseOrderStatus status;
    private List<PurchaseOrderItemDTO> items;

    // Getters & Setters
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public LocalDate getExpectedDeliveryDate() { return expectedDeliveryDate; }
    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) { this.expectedDeliveryDate = expectedDeliveryDate; }
    public PurchaseOrderStatus getStatus() { return status; }
    public void setStatus(PurchaseOrderStatus status) { this.status = status; }
    public List<PurchaseOrderItemDTO> getItems() { return items; }
    public void setItems(List<PurchaseOrderItemDTO> items) { this.items = items; }
}

package com.erp.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

public class GRNRequestDTO {
    private Long purchaseOrderId;
    private LocalDate receivedDate;
    private List<GRNItemDTO> items;
    
	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public LocalDate getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(LocalDate receivedDate) {
		this.receivedDate = receivedDate;
	}
	public List<GRNItemDTO> getItems() {
		return items;
	}
	public void setItems(List<GRNItemDTO> items) {
		this.items = items;
	}
    
    
}
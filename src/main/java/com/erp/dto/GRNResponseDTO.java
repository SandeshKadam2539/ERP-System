package com.erp.dto;

import java.time.LocalDate;
import java.util.List;

public class GRNResponseDTO {
    private Long id;
    private LocalDate receivedDate;
    private Long purchaseOrderId;
    private List<GRNItemDTO> items;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(LocalDate receivedDate) {
		this.receivedDate = receivedDate;
	}
	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public List<GRNItemDTO> getItems() {
		return items;
	}
	public void setItems(List<GRNItemDTO> items) {
		this.items = items;
	}
    
    
}

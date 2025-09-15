package com.erp.dto;

import lombok.Data;

@Data
public class InvoiceRequestDTO {
    private Long salesOrderId;
    private Double taxPercent; // e.g., 18% GST
	public Long getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(Long salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public Double getTaxPercent() {
		return taxPercent;
	}
	public void setTaxPercent(Double taxPercent) {
		this.taxPercent = taxPercent;
	}
    
}

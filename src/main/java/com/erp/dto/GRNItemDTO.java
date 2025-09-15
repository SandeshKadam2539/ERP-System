package com.erp.dto;


public class GRNItemDTO {
    private Long productId;
    private String productName;
    private int receivedQuantity;
    
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getReceivedQuantity() {
		return receivedQuantity;
	}
	public void setReceivedQuantity(int receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

    
}

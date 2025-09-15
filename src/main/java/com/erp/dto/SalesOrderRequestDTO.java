package com.erp.dto;

import lombok.Data;
import java.util.List;

@Data
public class SalesOrderRequestDTO {
    private String customerName;
    private List<OrderItemDTO> items;
    
    

    public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public List<OrderItemDTO> getItems() {
		return items;
	}



	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}



	@Data
    public static class OrderItemDTO {
        private Long productId;
        private int quantity;
		public Long getProductId() {
			return productId;
		}
		public void setProductId(Long productId) {
			this.productId = productId;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
        
        
    }
}

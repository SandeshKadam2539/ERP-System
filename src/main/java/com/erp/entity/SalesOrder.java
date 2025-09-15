package com.erp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private Date orderDate = new Date();

    // ✅ Default value to avoid null
    private String status = "PENDING";

    @JsonManagedReference
    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesOrderItem> items = new ArrayList<>();
    
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<SalesOrderItem> getItems() {
		return items;
	}

	public void setItems(List<SalesOrderItem> items) {
		this.items = items;
	}

	public void addItem(SalesOrderItem item) {
        item.setSalesOrder(this);
        items.add(item);
    }

    // Null-safe check helper method
    public boolean hasStatus(String checkStatus) {
        return status != null && status.equalsIgnoreCase(checkStatus);
    }
}

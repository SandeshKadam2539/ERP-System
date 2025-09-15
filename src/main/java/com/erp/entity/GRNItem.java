package com.erp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GRNItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private int receivedQuantity;

    @ManyToOne
    @JoinColumn(name = "grn_id")
    @JsonBackReference
    private GRN grn;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getReceivedQuantity() {
		return receivedQuantity;
	}

	public void setReceivedQuantity(int receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

	public GRN getGrn() {
		return grn;
	}

	public void setGrn(GRN grn) {
		this.grn = grn;
	}
    
    
}

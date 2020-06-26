package com.mindtree.shoppingcart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@EmbeddedId
	@JsonIgnore
	private OrderDetailsPK orderdetailspk;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "fk_cart_id", insertable = false, updatable = false)
	private ShoppingCart shoppingcart;
	
	@ManyToOne
	@JoinColumn(name = "fk_product_id", insertable = false, updatable = false)
	private Product product;
			
	@Column(name="Quantity",nullable=false)
	private int quantity;


	public OrderDetails(ShoppingCart shoppingcart, Product product, int quantity) {
		super();
		this.shoppingcart = shoppingcart;
		this.product = product;
		this.quantity = quantity;
	}
	
	public OrderDetails(OrderDetailsPK orderdetailspk, int quantity) {
		super();
		this.orderdetailspk = orderdetailspk;
		this.quantity = quantity;
	}
	
	
    
	@Transient
    public Double getTotalPrice() {
        return getProduct().getProductAmount() * getQuantity();
    }
	
	



}

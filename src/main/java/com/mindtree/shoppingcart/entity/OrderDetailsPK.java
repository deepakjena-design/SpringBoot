package com.mindtree.shoppingcart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderDetailsPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	//@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="cart_id")
	private ShoppingCart cart;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="product_id")
	private Product product;
	
*/
	
	@Column(name="fk_cart_id")
	protected int cartid;
	
	@Column(name="fk_product_id")
	protected int productid;


}

package com.mindtree.shoppingcart.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ShoppingCart")
public class ShoppingCart {
	
	@Id
	@GeneratedValue
	private int cartId;
	
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name="user_cart_Id")
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy="shoppingcart")
	private List<OrderDetails> orderDetails;

	public ShoppingCart(@Valid int cartId, User user) {
		super();
		this.cartId = cartId;
		this.user = user;
	}
	
	@Transient
	@JsonIgnore
	public int getNumberOfProductInCart() {
		return getOrderDetails().size();
	}
	
	@Transient
	@JsonIgnore
	public double getTotalCartAmount()
	{
		double sum = 0D;
		sum = getOrderDetails().stream().mapToDouble(order -> order.getTotalPrice()).sum();
		return sum;
		
	}
}

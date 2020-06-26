package com.mindtree.shoppingcart.dto;

import com.mindtree.shoppingcart.entity.ShoppingCart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ShoppingCartRequest {
	
	private ShoppingCart shoppingCart;
	
	public double getTotalCartAmount() {
		return shoppingCart.getTotalCartAmount();
	}
	
	public int getNumberOfProductInCart() {
		return shoppingCart.getNumberOfProductInCart();
	}

}

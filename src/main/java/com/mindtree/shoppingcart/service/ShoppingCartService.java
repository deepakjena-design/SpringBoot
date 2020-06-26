package com.mindtree.shoppingcart.service;

import javax.validation.Valid;

import com.mindtree.shoppingcart.dto.ShoppingCartRequest;

public interface ShoppingCartService {
	
	public String saveOrUpdateOrder(@Valid int cartId,@Valid int userId,@Valid int Prodid, @Valid int qty) ;
	public String removeProductById(@Valid int cartId,@Valid int Prodid);
	public String removeAllProducts(@Valid int cartId);
	public ShoppingCartRequest getAllProductsInCart(int cartId);

}

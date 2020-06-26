package com.mindtree.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mindtree.shoppingcart.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer>{
	
	/*@Query("select quantity from Product where product_id = ?1 and cart_prod_fk = ?2")
	Integer countProduct(int prodId,int cartId);
	*/
	
	
}

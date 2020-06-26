package com.mindtree.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.shoppingcart.entity.OrderDetails;
import com.mindtree.shoppingcart.entity.OrderDetailsPK;

public interface OrderRepository extends JpaRepository<OrderDetails, OrderDetailsPK>{
	
	public List<OrderDetails> findByOrderdetailspkCartid(int cartId);
	
	public void deleteByOrderdetailspkCartid(int cartId);

}

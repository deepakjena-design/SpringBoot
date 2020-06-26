package com.mindtree.shoppingcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.shoppingcart.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	
	public Optional<Product> findByProductName(String name);
	
	public List<Product> findByCategory(String category);
	
	
	
}

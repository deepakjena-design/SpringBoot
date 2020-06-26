package com.mindtree.shoppingcart.service;

import java.util.List;

import com.mindtree.shoppingcart.dto.ProductRequest;
import com.mindtree.shoppingcart.entity.Product;

public interface ProductService {

	public ProductRequest getProductById(int id) throws Exception;
	public ProductRequest getProductByName(String name)throws Exception ;
	public ProductRequest getProductByCategory(String category) throws Exception;
		

}

package com.mindtree.shoppingcart.dto;

import java.util.List;

import com.mindtree.shoppingcart.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRequest {
	private Product product;
	
	private List<Product> prodList;
}

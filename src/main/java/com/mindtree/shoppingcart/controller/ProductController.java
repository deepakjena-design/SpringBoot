package com.mindtree.shoppingcart.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingcart.dto.ProductRequest;
import com.mindtree.shoppingcart.exception.ResourceNotFoundException;
import com.mindtree.shoppingcart.exception.ServiceException;
import com.mindtree.shoppingcart.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
	
	Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService prodService;

	@RequestMapping(method=RequestMethod.GET, value ="/{id}")
	public ProductRequest getProductById(@PathVariable int id)throws Exception {
		log.info("[ProductController][getProductById]");
		ProductRequest product = null;
		try {
			product = prodService.getProductById(id);
			
		}catch(ResourceNotFoundException eX) {
			log.info("[ProductController][getProductById][ResourceNotFoundException]"+eX.getMessage());
			throw new ResourceNotFoundException(eX.getMessage());
		}catch(Exception eX) {
			log.info("[ProductController][getProductById][Exception]"+eX.getMessage());
			throw new ServiceException();
		}
		return product;
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/fetch/{name}" )
	public ProductRequest getProductByName(@PathVariable String name)throws Exception {
		log.info("[ProductController][getProductByName]");
		ProductRequest product = null;
		try {
			product = prodService.getProductByName(name);
			
		}catch(ResourceNotFoundException eX) {
			log.info("[ProductController][getProductByName][ResourceNotFoundException]"+eX.getMessage());
			throw new ResourceNotFoundException(eX.getMessage());
		}catch(Exception eX) {
			log.info("[ProductController][getProductByName][Exception]"+eX.getMessage());
			throw new ServiceException();
		}
		return product;
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/fetchcategory/{category}" )
	public ProductRequest getProductsByCategory(@PathVariable String category)throws Exception {
		log.info("[ProductController][getProductsByCategory]");
		ProductRequest product = null;
		try {
			product = prodService.getProductByCategory(category);
			
		}catch(ResourceNotFoundException eX) {
			log.info("[ProductController][getProductsByCategory][ResourceNotFoundException]"+eX.getMessage());
			throw new ResourceNotFoundException(eX.getMessage());
		}catch(Exception eX) {
			log.info("[ProductController][getProductsByCategory][Exception]"+eX.getMessage());
			throw new ServiceException();
		}
		return product;
	}
	
	/*
	@RequestMapping(method=RequestMethod.POST, value ="/GETPRODUCT")
	public Product getProductDetails(@RequestBody Product prod)throws Exception {
		log.info("[ProductController][getProductDetails]");
		Product product = null;
		try {
			if(prod.getProductId() !=0 ) {
				product = prodService.getProductById(prod.getProductId());
			}else if(prod.getProductName() != "") {
				product = prodService.getProductByName(prod.getProductName());
			}else if(prod.getCategory() != "") {
				product = prodService.getProductByCategory(prod.getCategory());
			}else {
				throw new Exception("Please enter the valid Product Details");
			}
			if(product == null) throw new Exception("No such Product Available");
		}catch(Exception eX) {
			log.info("[ProductController][getProductDetails][Exception]"+eX.getMessage());
			throw eX;
		}
		return product;
	}
	*/
}

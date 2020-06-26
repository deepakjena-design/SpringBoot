package com.mindtree.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.shoppingcart.dto.ProductRequest;
import com.mindtree.shoppingcart.entity.Product;
import com.mindtree.shoppingcart.exception.ResourceNotFoundException;
import com.mindtree.shoppingcart.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl  implements ProductService{
	
	Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository prodRepo;
	
	@Override
	public ProductRequest getProductById(int id)  {
		
		log.info("[ProductServiceImpl][getProductById]");
		try {
			Product prod = prodRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product '"+id+"' is not available."));
			ProductRequest prodReq = new ProductRequest();
			prodReq.setProduct(prod);
			return prodReq;
		}catch(ResourceNotFoundException eX) {
			log.info("[ProductServiceImpl][getProductById][ResourceNotFoundException]");
			throw eX;
		}catch(Exception eX) {
			log.info("[ProductServiceImpl][getProductById][Exception]");
			//eX.printStackTrace();
			throw new ResourceNotFoundException(
				      "System Issue, Please try after sometime");
		}
	}
	
	@Override
	public ProductRequest getProductByName(String name) {
		log.info("[ProductServiceImpl][getProductByName]");
		try{
			Optional<Product> prod = prodRepo.findByProductName(name);
		
			if(prod.isPresent()){
				ProductRequest prodReq = new ProductRequest();
				prodReq.setProduct(prod.get());
				return prodReq;
			}else {
				log.info("[ProductServiceImpl][getProductByName]"+"Product '"+name+"' is not available.");
				throw new ResourceNotFoundException("Product '"+name+"' is not available.");
			}
		}catch(ResourceNotFoundException eX) {
			log.info("[ProductServiceImpl][getProductByName][ResourceNotFoundException]");
			throw eX;
		}catch(Exception eX) {
			log.info("[ProductServiceImpl][getProductByName][Exception]");
			//eX.printStackTrace();
			throw new ResourceNotFoundException(
				      "System Issue, Please try after sometime");
		}
	}
	
	@Override
	public ProductRequest getProductByCategory(String category) throws Exception{
		log.info("[ProductServiceImpl][getProductByCategory]");
		try {
			List<Product> prodList = prodRepo.findByCategory(category);
		
			if(!prodList.isEmpty()) {
				ProductRequest prodReq = new ProductRequest();
				prodReq.setProdList(prodList);
				return prodReq;
			}else {
				throw new ResourceNotFoundException("Product category '"+category+"' is not available.");
			}
		}catch(ResourceNotFoundException eX) {
			log.info("[ProductServiceImpl][getProductByCategory][ResourceNotFoundException]");
			throw eX;
		}catch(Exception eX) {
			log.info("[ProductServiceImpl][getProductByCategory][Exception]");
			throw new ResourceNotFoundException(
				      "System Issue, Please try after sometime");
		}
	}
	
}

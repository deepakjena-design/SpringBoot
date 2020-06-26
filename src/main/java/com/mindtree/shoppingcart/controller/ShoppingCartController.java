package com.mindtree.shoppingcart.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingcart.dto.ShoppingCartRequest;
import com.mindtree.shoppingcart.exception.ResourceNotFoundException;
import com.mindtree.shoppingcart.exception.ServiceException;
import com.mindtree.shoppingcart.service.ShoppingCartService;

@RestController
@Validated
public class ShoppingCartController {
	
	Logger log = LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	private ShoppingCartService shoppingCart;
	
	@PostMapping("/placeOrder")
		public ResponseEntity<String> saveOrUpdateOrder(@Valid @RequestParam int cartId,@Valid @RequestParam int userId,
				@Valid @RequestParam int productId, @RequestParam @Min(value=0,message="Quantity must be equal or greater than zero.") int quantity) 
						throws ResourceNotFoundException {
		log.info("[ShoppingCartController][saveOrUpdateOrder]");	
		try{
			String msg = shoppingCart.saveOrUpdateOrder(cartId,userId,productId,quantity);	
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		}catch(ResourceNotFoundException eX) {
			log.info("[ShoppingCartController][saveOrUpdateOrder][ResourceNotFoundException]"+eX.getMessage());
			throw new ResourceNotFoundException(eX.getMessage());
		}catch(Exception eX) {
			log.info("[ShoppingCartController][saveOrUpdateOrder][Exception]"+eX.getMessage());
			throw new ServiceException();
		}
	}
	
	@DeleteMapping("/removeProductById")
	public ResponseEntity<String> removeProductById(@Valid @RequestParam int cartId,
				@Valid @RequestParam int productId){
		log.info("[ShoppingCartController][removeProductById]");
		try {
			String msg = shoppingCart.removeProductById(cartId,productId);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		}catch(ResourceNotFoundException eX) {
			log.info("[ShoppingCartController][removeProductById][ResourceNotFoundException]"+eX.getMessage());
			throw new ResourceNotFoundException(eX.getMessage());
		}catch(Exception eX) {
			log.info("[ShoppingCartController][removeProductById][Exception]"+eX.getMessage());
			throw new ServiceException();
		}
	}
	
	@DeleteMapping("/removeAllProducts")
	public ResponseEntity<String> removeAllProducts(@Valid @RequestParam int cartId){
		log.info("[ShoppingCartController][removeAllProducts]");
		try {
			String msg = shoppingCart.removeAllProducts(cartId);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		}catch(ResourceNotFoundException eX) {
			log.info("[ShoppingCartController][removeAllProducts][ResourceNotFoundException]"+eX.getMessage());
			throw new ResourceNotFoundException(eX.getMessage());
		}catch(Exception eX) {
			log.info("[ShoppingCartController][removeAllProducts][Exception]"+eX.getMessage());
			throw new ServiceException();
		}
		
	}
	
	@GetMapping("/getAllProductsInCart")
	public ShoppingCartRequest getAllProductsInCart(@RequestParam(required = false) @NotNull @Valid int cartId)throws Exception{
		log.info("[ShoppingCartController][getAllProductsInCart]");	
		try {
			ShoppingCartRequest cartReq = shoppingCart.getAllProductsInCart(cartId);
			return cartReq;
		}catch(ResourceNotFoundException eX) {
			log.info("[ShoppingCartController][getAllProductsInCart][ResourceNotFoundException]"+eX.getMessage());
			throw new ResourceNotFoundException(eX.getMessage());
		}catch(Exception eX) {
			log.info("[ShoppingCartController][getAllProductsInCart][Exception]"+eX.getMessage());
			throw new ServiceException();
		}
	}
}

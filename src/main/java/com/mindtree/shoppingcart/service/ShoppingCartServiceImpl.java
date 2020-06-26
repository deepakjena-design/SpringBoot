package com.mindtree.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.shoppingcart.dto.ShoppingCartRequest;
import com.mindtree.shoppingcart.entity.OrderDetails;
import com.mindtree.shoppingcart.entity.OrderDetailsPK;
import com.mindtree.shoppingcart.entity.Product;
import com.mindtree.shoppingcart.entity.ShoppingCart;
import com.mindtree.shoppingcart.entity.User;
import com.mindtree.shoppingcart.exception.ResourceNotFoundException;
import com.mindtree.shoppingcart.repository.OrderRepository;
import com.mindtree.shoppingcart.repository.ProductRepository;
import com.mindtree.shoppingcart.repository.ShoppingCartRepository;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService{
	
	Logger log = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	ProductRepository prodRepo;
	
	
	@Override
	public String saveOrUpdateOrder(@Valid int cartId,@Valid int userId, @Valid int prodId, @Valid int qty) {
		log.info("[ShoppingCartServiceImpl][saveOrUpdateOrder]");
		String status = "Added";
		String message ;
		try {
			
			ShoppingCart cart = null;
			//userId validation
			//userid and cartid validation
			Optional<ShoppingCart> shoppingCart =  shoppingCartRepo.findById(cartId);//.orElse(
			if(!shoppingCart.isPresent()) {
				cart = shoppingCartRepo.save(new ShoppingCart(cartId,new User(userId)));
			}
			else {
				cart = shoppingCart.get();
			}
			Product prod = prodRepo.findById(prodId).orElseThrow(()->new ResourceNotFoundException("Product '"+prodId+"' is not available."));
			OrderDetailsPK orderPk = new OrderDetailsPK(cart.getCartId(), prod.getProductId());
					
			if(qty == 0) {
				orderRepo.deleteById(orderPk);
				status = "removed";
			}else {
				OrderDetails orderDet = new OrderDetails(orderPk, qty);
				orderRepo.save(orderDet);
			}
				
			message = 	"Product " + prod.getProductName()+ " " + status+" successfully";
		}catch(ResourceNotFoundException eX) {
			log.info("[ShoppingCartServiceImpl][saveOrUpdateOrder][ResourceNotFoundException]");
			throw eX;
		}catch(Exception eX) {
			log.info("[ShoppingCartServiceImpl][saveOrUpdateOrder][Exception]");
			//eX.printStackTrace();
			throw new ResourceNotFoundException(
				      "System Issue, Please try after sometime");
		}
		return message;
	}


	@Override
	public String removeProductById(@Valid int cartId, @Valid int prodId) {
		log.info("[ShoppingCartServiceImpl][removeProductById]");
		String message;
		try {
			ShoppingCart shoppingCart =  shoppingCartRepo.findById(cartId).orElseThrow(()->new ResourceNotFoundException("No cart available. Please add cart and product."));
			Product prod = prodRepo.findById(prodId).orElseThrow(()->new ResourceNotFoundException("Product is not available. Please try after sometime."));
			OrderDetailsPK orderPk = new OrderDetailsPK(shoppingCart.getCartId(), prod.getProductId());
			orderRepo.deleteById(orderPk);
			message = "Product "+ prod.getProductName() + " removed successfully";
		}catch(ResourceNotFoundException eX) {
			log.info("[ShoppingCartServiceImpl][removeProductById][ResourceNotFoundException]");
			throw eX;
		}catch(Exception eX) {
			log.info("[ShoppingCartServiceImpl][removeProductById][Exception]");
			//eX.printStackTrace();
			throw new ResourceNotFoundException(
				      "System Issue, Please try after sometime");
		}
		return message;
		
	}


	@Override
	public String removeAllProducts(@Valid int cartId) {
		log.info("[ShoppingCartServiceImpl][removeAllProducts]");
		String message;
		try {
			ShoppingCart shoppingCart =  shoppingCartRepo.findById(cartId).orElseThrow(()->new ResourceNotFoundException("No cart available. Please add cart and product."));
			//orderRepo.removeAllProducts(shoppingCart.getCartId());
			orderRepo.deleteByOrderdetailspkCartid(shoppingCart.getCartId());
			message = "All the products removed successfully.";
		}catch(ResourceNotFoundException eX) {
			log.info("[ShoppingCartServiceImpl][removeAllProducts][ResourceNotFoundException]");
			throw eX;
		}catch(Exception eX) {
			log.info("[ShoppingCartServiceImpl][removeAllProducts][Exception]");
			throw new ResourceNotFoundException(
				      "System Issue, Please try after sometime");
		}
		return message;
	}

	@Override
	public ShoppingCartRequest getAllProductsInCart(int cartId) {
		log.info("[ShoppingCartServiceImpl][getAllProductsAndTotalAmountForCart]");
		ShoppingCartRequest shoppIngReq;
		try {
			ShoppingCart shoppingCart =  shoppingCartRepo.findById(cartId).orElseThrow(()->new ResourceNotFoundException("No cart available. Please add cart and product."));
			
			List<OrderDetails> orderDetails = orderRepo.findByOrderdetailspkCartid(shoppingCart.getCartId());
		
			if(orderDetails.size() == 0) throw new ResourceNotFoundException("Cart is empty.Please add product to your cart.");
			shoppingCart.setOrderDetails(orderDetails);
			shoppIngReq = new ShoppingCartRequest();
			shoppIngReq.setShoppingCart(shoppingCart);;
			
			
		}catch(ResourceNotFoundException eX) {
			log.info("[ShoppingCartServiceImpl][getAllProductsAndTotalAmountForCart][ResourceNotFoundException]");
			throw eX;
		}catch(Exception eX) {
			log.info("[ShoppingCartServiceImpl][getAllProductsAndTotalAmountForCart][Exception]");
			throw new ResourceNotFoundException(
				      "System Issue, Please try after sometime");
		}
		return shoppIngReq;
	}
	
}

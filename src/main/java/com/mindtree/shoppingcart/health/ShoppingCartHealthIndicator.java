package com.mindtree.shoppingcart.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.system.DiskSpaceHealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartHealthIndicator {//implements HealthIndicator {

	
	public Health health() {
		System.out.println("inside health mentod of Deepak");
		return Health.up().build();
		
	}

	
	
}

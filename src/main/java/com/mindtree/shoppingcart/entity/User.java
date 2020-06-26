package com.mindtree.shoppingcart.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	
	@Id
	int userId;
	String userName;
	
	public User(int userId) {
		super();
		this.userId = userId;
	}
		
	
}

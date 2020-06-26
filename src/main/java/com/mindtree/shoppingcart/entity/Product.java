package com.mindtree.shoppingcart.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Product")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="Category",discriminatorType= DiscriminatorType.STRING)
public class Product {
	
	@Id
	@Column(name="ProdID")
	@JsonIgnore
	private int productId;
	
	@Column(name="Name")
	@NotNull(message = "Product name is required.")
	private String productName;
	
	@Column(name="Price")
	private double productAmount;
	
	@Column(name="Category",insertable=false, updatable=false)
	@NotNull(message = "Product category is required.")
	protected String category;
	
}

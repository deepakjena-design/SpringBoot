package com.mindtree.shoppingcart.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@DiscriminatorValue(value="Book")
public class Book extends Product{
	
	@Column(name="Genre")
	private String genre;
	@Column(name="Author")
	private String author;
	@Column(name="Publcations")
	private String publications;
	

}

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
@DiscriminatorValue(value="Apparal")
public class Apparal extends Product{
	
	@Column(name="type")
	private String type;
	@Column(name="brand")
	private String brand;
	@Column(name="design")
	private String design;

}

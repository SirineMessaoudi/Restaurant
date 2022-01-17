package com.ds_JEE_M_H.Entities;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("Dessert")
public class Dessert  extends Met{

	public Dessert() {
		// TODO Auto-generated constructor stub
	}

}

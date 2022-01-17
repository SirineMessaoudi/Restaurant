package com.ds_JEE_M_H.Entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("Entree")
public class Entree extends Met {

	public Entree() {
		// TODO Auto-generated constructor stub
	}

}

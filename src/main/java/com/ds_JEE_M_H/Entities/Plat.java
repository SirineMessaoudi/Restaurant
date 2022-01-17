package com.ds_JEE_M_H.Entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("Plat")
public class Plat extends Met {


	public Plat() {
		// TODO Auto-generated constructor stub
	}
}

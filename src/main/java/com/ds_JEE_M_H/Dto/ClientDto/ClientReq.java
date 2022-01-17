package com.ds_JEE_M_H.Dto.ClientDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClientReq {

	public ClientReq() {
		// TODO Auto-generated constructor stub
	}
	 private long id;

	    private  String nom;
	    private String prenom;

	    private LocalDate dateDeNaissance;

	    private  String courriel;
	    private  String telephone;

}

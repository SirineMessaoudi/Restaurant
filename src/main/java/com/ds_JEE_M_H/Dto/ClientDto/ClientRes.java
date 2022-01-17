package com.ds_JEE_M_H.Dto.ClientDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRes {

	public ClientRes() {
		// TODO Auto-generated constructor stub
	}

	private long id;

    private  String nom;
    private String prenom;

    private LocalDate dateDeNaissance;

    private  String courriel;
    private  String telephone;
}

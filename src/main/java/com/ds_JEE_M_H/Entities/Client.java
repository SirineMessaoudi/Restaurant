package com.ds_JEE_M_H.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "Client")
@NoArgsConstructor
@AllArgsConstructor
public class Client {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(length = 50)
	private String nom ;
	
	@Column(length = 50)
	private String prenom ;
	
	private LocalDate dateNaissance ; 
	
	@Column(length = 50)
	private String courriel ;
	
	@Column(length = 50)
	private String telephone ;
	
	
	@OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE)
	@JsonIgnore
    private List<Ticket> tickets;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(Long id, String nom, String prenom, LocalDate dateNaissance, String courriel, String telephone,
			List<Ticket> tickets) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.courriel = courriel;
		this.telephone = telephone;
		this.tickets = tickets;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	
}
package com.ds_JEE_M_H.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ds_JEE_M_H.Entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.STRING,
        name = "met_type_id",
        columnDefinition = "TEXT"
)
public class Met {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true,nullable = false)
	private String nom ;
	
	@Column(length = 50)
	private float prix ;
	
	public Met() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Met(String nom, float prix) {
		super();
		this.nom = nom;
		this.prix = prix;
	}
	
	@ManyToMany(mappedBy = "mets")
	@JsonIgnore
	private List<Ticket> tickets;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	
}

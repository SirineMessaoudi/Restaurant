package com.ds_JEE_M_H.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "tickets")
@ToString(exclude = "tickets")
@javax.persistence.Table(name = "myTable")

public class Table {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true,nullable = false)
	private int numero;
	
	@Column(length = 50)
	private int nbCouvert ;
	
	@Column(length = 50)
	private String type ;
	
	@Column(length = 50)
	private float supplement ;
	
   
	public Table() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Table(int numero, int nbCouvert, String type, float supplement) {
		super();
		this.numero = numero;
		this.nbCouvert = nbCouvert;
		this.type = type;
		this.supplement = supplement;
	}
	
	 public List<Ticket> getTickets() {
	        return tickets;
	    }

	    public void setTickets(List<Ticket> tickets) {
	        this.tickets = tickets;
	    }

	    @OneToMany(mappedBy = "table",cascade = CascadeType.REMOVE)
	    @JsonIgnore
	    private List<Ticket> tickets;

	    
	    //getter and setters : 
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public int getNumero() {
			return numero;
		}
		public void setNumero(int numero) {
			this.numero = numero;
		}
		public int getNbCouvert() {
			return nbCouvert;
		}
		public void setNbCouvert(int nbCouvert) {
			this.nbCouvert = nbCouvert;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public float getSupplement() {
			return supplement;
		}
		public void setSupplement(float supplement) {
			this.supplement = supplement;
		}
	
	    
}

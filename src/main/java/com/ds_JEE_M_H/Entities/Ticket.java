package com.ds_JEE_M_H.Entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int numero;

	 private Instant date;
	
	@Column(length = 50)
	private int nbCouvert ;
	
	@Column(length = 50)
	private float addition ;
	
	@ManyToOne
	private Client client;
	
	@ManyToOne
	private  Table table;
	
	@ManyToMany
    @JoinTable(name = "compose")
    private List<Met>  mets;
	
	public Ticket(int numero, Instant date, int nbCouvert, float addition, Client client, Table table, List<Met> mets) {
		super();
		this.numero = numero;
		this.date = date;
		this.nbCouvert = nbCouvert;
		this.addition = addition;
		this.client = client;
		this.table = table;
		this.mets = mets;
	}

	 public List<Met> getMets() {
	        return mets;
	    }

	    public void setMets(List<Met> mets) {
	        this.mets = mets;
	    }

		public int getNumero() {
			return numero;
		}

		public void setNumero(int numero) {
			this.numero = numero;
		}

		public Instant getDate() {
			return date;
		}

		public void setDate(Instant date) {
			this.date = date;
		}

		public int getNbCouvert() {
			return nbCouvert;
		}

		public void setNbCouvert(int nbCouvert) {
			this.nbCouvert = nbCouvert;
		}

		public float getAddition() {
			return addition;
		}

		public void setAddition(float addition) {
			this.addition = addition;
		}

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}

		public Table getTable() {
			return table;
		}

		public void setTable(Table table) {
			this.table = table;
		}
	
	
	
}

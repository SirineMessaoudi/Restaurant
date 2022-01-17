package com.ds_JEE_M_H.Dto.TicketDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.ds_JEE_M_H.Dto.ClientDto.ClientReq;
import com.ds_JEE_M_H.Dto.MetDto.MetReq;
import com.ds_JEE_M_H.Dto.TableDto.TableReq;

import com.ds_JEE_M_H.Entities.Client;
import com.ds_JEE_M_H.Entities.Met;
import com.ds_JEE_M_H.Entities.Table;

import com.ds_JEE_M_H.Repositories.TableRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketReq {

	public TicketReq() {
		// TODO Auto-generated constructor stub
	}
	private int numero;
    private Instant date;
    private int nbCouvert ;
    private double addition;
    private ClientReq client;
    private TableReq table;
    private List<MetReq> mets;
}

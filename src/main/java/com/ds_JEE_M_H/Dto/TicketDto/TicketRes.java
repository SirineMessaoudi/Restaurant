package com.ds_JEE_M_H.Dto.TicketDto;

import com.ds_JEE_M_H.Dto.MetDto.MetRes;

import  com.ds_JEE_M_H.Entities.Client;
import  com.ds_JEE_M_H.Entities.Met;
import  com.ds_JEE_M_H.Entities.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;


import java.time.Instant;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRes {
	public TicketRes() {
		// TODO Auto-generated constructor stub
	}

    private int numero;
    private Instant date;
    private int nbCouvert ;
    private double addition;
    private Client client;
    private Table table;
    private List<MetRes> mets;
}

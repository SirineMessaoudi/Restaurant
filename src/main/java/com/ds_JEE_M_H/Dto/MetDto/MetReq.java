package com.ds_JEE_M_H.Dto.MetDto;

import com.ds_JEE_M_H.Dto.TicketDto.TicketReq ;

import com.ds_JEE_M_H.Entities.Ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetReq {
	public MetReq() {
		// TODO Auto-generated constructor stub
	}
	
    private long id;

    private String nom;
    private double prix;

}
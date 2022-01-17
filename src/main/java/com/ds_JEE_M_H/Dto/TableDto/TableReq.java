package com.ds_JEE_M_H.Dto.TableDto;

import lombok.Data;
import com.ds_JEE_M_H.Dto.TicketDto.TicketReq ;
import com.ds_JEE_M_H.Entities.Ticket;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Data
public class TableReq {

	public TableReq() {
		// TODO Auto-generated constructor stub
	}
	
    private long id;
    private int numero;
    private int nbCouvert;
    private String type;
    private double supplement;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getSupplement() {
        return supplement;
    }
    public void setSupplement(double supplement) {
        this.supplement = supplement;
    }
	

}

package com.ds_JEE_M_H.Dto.TableDto;

import com.ds_JEE_M_H.Dto.TicketDto.TicketReq ;
import com.ds_JEE_M_H.Entities.Ticket;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
public class TableRes {

	public TableRes() {
		// TODO Auto-generated constructor stub
	}
    private long id;
    private int numero;
    private int nbCouvert;
    @Getter
    @Setter
    private String type;

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


    @Getter
    @Setter
    private double supplement ;
   /* private String tickets;
    public String getTickets() {
        return tickets;
    }
    public void setTickets(String  tickets) {
        this.tickets = tickets;
    }*/

}



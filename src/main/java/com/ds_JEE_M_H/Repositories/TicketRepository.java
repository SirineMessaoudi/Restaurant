package com.ds_JEE_M_H.Repositories;
import com.ds_JEE_M_H.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
	
}



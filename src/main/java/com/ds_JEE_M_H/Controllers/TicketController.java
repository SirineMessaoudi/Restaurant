package com.ds_JEE_M_H.Controllers;


import com.ds_JEE_M_H.Dto.MetDto.MetRes;
import com.ds_JEE_M_H.Dto.TableDto.TableRes;
import com.ds_JEE_M_H.Dto.TicketDto.TicketReq;
import com.ds_JEE_M_H.Dto.TicketDto.TicketRes;
import com.ds_JEE_M_H.Services.TicketService;
import com.ds_JEE_M_H.Entities.Client;
import com.ds_JEE_M_H.Entities.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/ticket")
public class TicketController {
	

	public TicketController() {
		// TODO Auto-generated constructor stub
	}
	
    private TicketService ticketservice;

    @Autowired
    public TicketController(TicketService ticketserv) {
        this.ticketservice = ticketserv;
    }



  /* Crud Operations here */ 
    
//afficher tout les tickets :

    @GetMapping
    public List<Ticket> getAllTicket(){
        return ticketservice.getAllTickets();
    }
    

//Afficher des Tickets by id :
      @GetMapping("/{num}")
      public  TicketRes getByIdTicket(@PathVariable("num") int num){
          return ticketservice.searchById(num);
      }
      
//Ajouter des tickets :
    @PostMapping("/addTicket")
    public Ticket addTicket(@RequestBody Ticket ticket){
        return ticketservice.addTicket(ticket);
    }
    
//modifier des tickets :
    @PostMapping("/update/{num}")
    public TicketRes updateTable(@RequestBody TicketReq ticket, @PathVariable("num")int num){
        return ticketservice.modifierTicket(num,ticket);
    }


//supprimer les numéros des tickets By id: 
    @DeleteMapping("/{num}")
    public String deleteByIdTicket(@PathVariable("num") int num){
        return ticketservice.deleteTicket(num);
    }

    
    
 /* questions part 3 here */
//q1 (a) : afficher le plat le plus acheté à une période donnée:
    @GetMapping("/top/plat/{begin}/{end}")
    public MetRes getTopPlat(@PathVariable("begin") Instant begin, @PathVariable("end") Instant end){
        return ticketservice.mostBuyedPlat(begin,end);
    }
    
//q2 (b) afficher le client le plus fidèle au restaurant :
    @GetMapping("/client/fidel/{begin}/{end}")
    public Client ClientplusFidel(@PathVariable("begin") Instant begin, @PathVariable("end") Instant end){
        return ticketservice.ClientplusFidel(begin, end);
    }
    
//q3 (c) la table la plus réservée : 
    @GetMapping("/top/reserved/table")
    public TableRes getTopPlat(){
        return ticketservice.mostReservedTable();
    }
    
    
//q4 (d)  afficher le jour de la semaine le plus réservé par un client donné:

    @GetMapping("/top/reservedday/{id}")
    public Instant JourPlusReserveduClient(@PathVariable("id")int id){
        return ticketservice.JourPlusReserveduClient(id);
    }    
    
//q5 (e) Retourner le revenu par jour, semaine et mois : 
     @GetMapping("/revenue")
     public String RevenueDerniere(){
         return ticketservice.RevenueDerniere();
     }   
    
    
//q6 (f)  Retourner le revenu pour une période donnée: 

    @GetMapping("/revenue/period/{begin}/{end}")
    public Double RevenuePeriod(@PathVariable("begin") Instant begin, @PathVariable("end") Instant end){
        return ticketservice.RevenuePeriod(begin, end);
    }
    


/*errors and exception part here */
    //catch Errors and Exceptions
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // To Return 1 validation error
        //return new ResponseEntity<String>(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        StringBuilder errors = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.append(error.getField() + ": "+ error.getDefaultMessage()+".\n");
        }
        return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
    }



}


package com.ds_JEE_M_H.Controllers;

import com.ds_JEE_M_H.Dto.ClientDto.ClientReq;
import com.ds_JEE_M_H.Dto.ClientDto.ClientRes;
import com.ds_JEE_M_H.Services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/client")
public class ClientController {
	
	public ClientController() {
		// TODO Auto-generated constructor stub
	}
	
    private ClientService clientservice;

    @Autowired
    public ClientController(ClientService clientservice) {
        this.clientservice = clientservice;
    }
/* Crud Operations here */    
    
//Afficher un client :
    @GetMapping
    public List<ClientRes> getAllTable(){
        return clientservice.getAllClients();
       
    }
    
//Ajouter un client :
    @PostMapping("/addClient")
    public ClientRes addClient(@RequestBody ClientReq client){
        return clientservice.addClient(client);
    }
    
//Supprimer un client :
    @DeleteMapping("/delete/{id}")
    public String deleteByIdTable(@PathVariable("id") long id){
        return clientservice.deleteClient(id);
    }
    
  //Modifier un client :
    @PostMapping("/update/{id}")
    public ClientRes updateTabel(@RequestBody ClientReq client,@PathVariable("id")long id){
        return clientservice.modifierClient(id,client);
    }
 //Rechercher un client :
    @GetMapping("/{id}")
    public  ClientRes getByIdClient(@PathVariable("id") long id){
        return clientservice.searchById(id);
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


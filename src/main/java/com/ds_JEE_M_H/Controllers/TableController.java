package com.ds_JEE_M_H.Controllers;

import com.ds_JEE_M_H.Dto.TableDto.TableReq;
import com.ds_JEE_M_H.Dto.TableDto.TableRes;
import com.ds_JEE_M_H.Dto.TicketDto.TicketReq;
import com.ds_JEE_M_H.Services.TableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/table")
public class TableController {
	

	public TableController() {
		// TODO Auto-generated constructor stub
	}
	
    private TableService tableservice ;

    @Autowired
    public TableController(TableService tableservice) {
        this.tableservice = tableservice;
    }


 /* Crud Operations here */ 
    
    
//afficher tout les tables : 
    @GetMapping
   public List<TableRes> getAllTable(){
       return tableservice.getAllTables();
    }
    
    //afficher une table par son id : 
    @GetMapping("/{id}")
    public TableRes getByIdTable(@PathVariable("id") long id){
       return tableservice.searchById(id);
    }
    
    
  //afficher une table par son numero : 
    @GetMapping("/numero/{id}")
     public  TableRes getByNumTable(@PathVariable("id") int num){
        return tableservice.getbyNumero(num);
    }
    
    
    //Ajouter une table : 
     @PostMapping("/addTable")
    public TableRes addTable(@RequestBody TableReq table){
        return tableservice.addTable(table);
     }

     
//modifier une table :
     @PostMapping("/update/{id}")
    public TableRes updateTabel(@RequestBody TableReq table,@PathVariable("id")long id){
        return tableservice.modifierTable(id,table);
     }
     
//supprimer une table : 
     @DeleteMapping("/{id}")
    public String deleteByIdTable(@PathVariable("id") long id){
        return tableservice.deleteTable(id);
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
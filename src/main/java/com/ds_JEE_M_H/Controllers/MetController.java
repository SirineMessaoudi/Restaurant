package com.ds_JEE_M_H.Controllers;

import com.ds_JEE_M_H.Dto.MetDto.MetReq;
import com.ds_JEE_M_H.Dto.MetDto.MetRes;

import com.ds_JEE_M_H.Services.MetService;
import com.ds_JEE_M_H.Entities.Dessert;
import com.ds_JEE_M_H.Entities.Entree;
import com.ds_JEE_M_H.Entities.Met;
import com.ds_JEE_M_H.Entities.Plat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("api/met/")
public class MetController {
	
	public MetController() {
		// TODO Auto-generated constructor stub
	}
	
	
    private MetService metservice;

    @Autowired
    public MetController(MetService metservice) {
        this.metservice = metservice;
    }

/* Crud Operations here */ 
    
    //afficher :
    //afficher tout les mets : 
      @GetMapping
      public List<MetRes> getAllMets(){
          return metservice.getAllMets();
      }
      
      /*afficher le type Plat here */
      @GetMapping("/plat")
      public List<MetRes> getAllPlats(){
          return metservice.getAllPlats();
      }
      
      /*afficher le type Dessert here */
      @GetMapping("/dessert")
      public List<MetRes> getAllDesserts(){
          return metservice.getAllDesserts();
      }
      
      /*afficher le type Entree here */
      @GetMapping("/entree")
      public List<MetRes> getAllEntrees(){
          return metservice.getAllEntrees();
      }

  //Afficher met by id :
    @GetMapping("/{id}")
    public  MetRes getByIdMet(@PathVariable("id") long id){
        return metservice.searchById(id);
    }
    
  //Afficher par nom (soit Plat ,Entree,Dessert) :
    @GetMapping("/nom/{nom}")
    public MetRes getByNomMet(@PathVariable("nom") String nom){
        return metservice.getByNom(nom);
    }

    //Ajouter un met selon le type de Met (Plat ou Entree ou Dessert ) : 
    /*Type Plat here */
    @PostMapping("/plat")
    public MetRes addPlat(@RequestBody MetReq met,String type){

        return metservice.addPlat(met);
    }
    /*Type Dessert here */
    @PostMapping("/dessert")
    public MetRes addDessert(@RequestBody MetReq met, String type){
        return metservice.addDessert(met);
    }
    /*Type Entree here */
    @PostMapping("/entree")
    public MetRes addEntree(@RequestBody MetReq met, String type){
        return metservice.addEntree(met);
    }


//Modifier un Met :
    @PostMapping("/update/{id}")
    public MetRes updateTabel(@RequestBody MetReq met, @PathVariable("id")long id){
        return metservice.modifierMet(id,met);
    }
    
//supprimer un met :
    @DeleteMapping("/delete/{id}")
    public String deleteByIdTable(@PathVariable("id") long id){
        return metservice.deleteMet(id);
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


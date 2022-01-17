package com.ds_JEE_M_H.Services;

import com.ds_JEE_M_H.Dto.TableDto.TableReq;
import com.ds_JEE_M_H.Dto.TableDto.TableRes;
import com.ds_JEE_M_H.Dto.TicketDto.TicketReq;
import com.ds_JEE_M_H.Entities.Table;
import com.ds_JEE_M_H.Repositories.TableRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TableService {

	private ModelMapper mapp = new ModelMapper();
    private TableRepository tableRepository;
    
  //constractor : 
    @Autowired
    public TableService (TableRepository tableRepository) {
        super();
        this.tableRepository = tableRepository;
    }
/* crud part here */
    //afficher tous les table :
    public List<TableRes> getAllTables(){
         List<Table> tables= tableRepository.findAll();
        List<TableRes> response=new ArrayList<>();
        for (Table table:tables) {
            response.add(mapp.map(table,TableRes.class));
        }
        return response;

    }
    
  //afficher une table avec son numéro :
    public TableRes getbyNumero(int num){
        Optional<Table> opt=  tableRepository.findByNumero(num);
        Table table;
        if(opt.isPresent())
            table=opt.get();
        else
            throw new NoSuchElementException("table avec ce numero "+num+" introuvable!  ;");
        return mapp.map(table,TableRes.class);
    }
    
    //ajouter une table : 
    public TableRes addTable(TableReq tableReq){
        Table table=mapp.map(tableReq,Table.class);

        Table tableDb= tableRepository.save(table);
        return mapp.map(tableDb,TableRes.class);
    }
    
  //Supprimer une table par son id :
    public String deleteTable(long id){
        TableRes table=searchById(id);
        tableRepository.deleteById(id);
        return "Table supprimée avec succes!";
    }

    //modifier une table :
    public TableRes modifierTable(long id ,TableReq newTableReq){
        TableRes tableResponse=searchById(id);
        Table oldtable=mapp.map(tableResponse,Table.class);
        Table newTable=mapp.map(newTableReq,Table.class);

        if(newTable.getNbCouvert()!=0)
            oldtable.setNbCouvert(newTable.getNbCouvert());

        if (newTable.getNumero() !=0)
            oldtable.setNumero(newTable.getNumero());

        if(newTable.getType()!=null)
            oldtable.setType(newTable.getType());

        if (newTable.getSupplement()!=0)
            oldtable.setSupplement(newTable.getSupplement());

             Table TableBD= tableRepository.save(oldtable);

        return mapp.map(TableBD,TableRes.class);
    }
    
    
    //rechercher une table par son id :
    public TableRes searchById(long id){
        Optional<Table> tableOpt=tableRepository.findById(id);
        Table table;
        if(tableOpt.isPresent())
            table=tableOpt.get();
        else
            throw new NoSuchElementException("Table avec Id introuvable");

        return mapp.map(table,TableRes.class);

    }
}





package com.ds_JEE_M_H.Services;

import com.ds_JEE_M_H.Dto.MetDto.MetReq;
import com.ds_JEE_M_H.Dto.MetDto.MetRes;

import com.ds_JEE_M_H.Entities.Dessert;
import com.ds_JEE_M_H.Entities.Entree;
import com.ds_JEE_M_H.Entities.Met;
import com.ds_JEE_M_H.Entities.Plat;
import com.ds_JEE_M_H.Repositories.DessertRepository;
import com.ds_JEE_M_H.Repositories.EntreeRepository;
import com.ds_JEE_M_H.Repositories.MetRepository;
import  com.ds_JEE_M_H.Repositories.PlatRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MetService {
    private ModelMapper mapp = new ModelMapper();
    
    private MetRepository metRepository;
    private PlatRepository platRepository;
    private DessertRepository dessertRepository;
    private EntreeRepository entreeRepository;

    public MetService(MetRepository metRepository, PlatRepository platRepository, DessertRepository dessertRepository,
			EntreeRepository entreeRepository) {
		super();
		this.metRepository = metRepository;
		this.platRepository = platRepository;
		this.dessertRepository = dessertRepository;
		this.entreeRepository = entreeRepository;
	}


/*crud part here */ 

//afficher tous les mets : 
       public List<MetRes> getAllMets(){
           List<MetRes> metsResp=new ArrayList<>();
                 List<Met> mets= metRepository.findAll();
                 for (Met met:mets){
                     metsResp.add(mapp.map(met,MetRes.class));
                 }
           return metsResp;
       }
       
 //Afficher tous les plats :
       public List<MetRes> getAllPlats(){
           List<MetRes> metsResp=new ArrayList<>();
           List<Plat> plats= platRepository.findAll();
           for (Plat met:plats){
               metsResp.add(mapp.map(met,MetRes.class));
           }
           return metsResp;
       }
       
       
 //Afficher tous les desserts :
       public List<MetRes> getAllDesserts(){
           List<MetRes> metsResp=new ArrayList<>();
           List<Dessert> desserts= dessertRepository.findAll();
           for (Dessert met:desserts){
               metsResp.add(mapp.map(met,MetRes.class));
           }
           return metsResp;
       }

 //Afficher tous les entrées :
       public List<MetRes> getAllEntrees(){
           List<MetRes> metsResp=new ArrayList<>();
           List<Entree> entrees= entreeRepository.findAll();
           for (Entree met:entrees){
               metsResp.add(mapp.map(met,MetRes.class));
           }
           return metsResp;
       }

  //Ajout un met(plat => Entree => Dessert ) :
 //ajouter un plat :
    public MetRes addPlat(MetReq metreq){
        Plat met=mapp.map(metreq,Plat.class);
        met.setNom(met.getNom().toUpperCase().trim());
        return mapp.map(platRepository.save(met),MetRes.class);
    }
    
 //ajouter une entrée :
    public MetRes addEntree(MetReq metreq){
        Entree met=mapp.map(metreq,Entree.class);
        met.setNom(met.getNom().toUpperCase().trim());
        return mapp.map(entreeRepository.save(met),MetRes.class);
    }
// ajouter un dessert : 
    public MetRes addDessert(MetReq metreq){
        Dessert met=mapp.map(metreq,Dessert.class);
        met.setNom(met.getNom().toUpperCase().trim());
        return mapp.map(dessertRepository.save(met),MetRes.class);
    }

//Rechercher un plat par son id : 
    public MetRes searchById(long id){
        Optional<Met> metOpt=metRepository.findById(id);
        Met met;
        if(metOpt.isPresent())
            met=metOpt.get();
        else
            throw new NoSuchElementException("Met avec ("+id+") introuvable ;");

        return mapp.map(met,MetRes.class);

    }
    
//Rechercher un plat par son nom : 
    public MetRes getByNom(String nom){
        String functionNom=nom.toUpperCase();
        Optional<Met> opt=metRepository.findByNom(functionNom);
        Met met;
        if (opt.isPresent())
            met= opt.get();
        else
            throw new NoSuchElementException(("met avec nom ("+nom+") introuvable"));
        return  mapp.map(met,MetRes.class);
    }

 

 //Supprimer un met par son id : 
    public String deleteMet(long id){
        searchById(id);
        metRepository.deleteById(id);
        return " Met supprimeé! ";
    }

 //Rechercher un met par son id :
    public MetRes modifierMet(long id , MetReq newMetReq){
        searchById(id);
               Met newMet=mapp.map(newMetReq,Met.class);
               Optional<Met> opt=metRepository.findById(id);
               Met oldMet=null;
               if(opt.isPresent()){
                   oldMet= opt.get();
               }

        if(newMet.getNom()!=null)
            oldMet.setNom(newMet.getNom().toUpperCase().trim());

        if (newMet.getPrix() !=0)
            oldMet.setPrix(newMet.getPrix());
            metRepository.save(oldMet);

        return mapp.map(oldMet,MetRes.class);
    }
}


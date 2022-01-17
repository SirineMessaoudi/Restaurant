package com.ds_JEE_M_H.Services;

import com.ds_JEE_M_H.Dto.ClientDto.ClientReq;
import com.ds_JEE_M_H.Dto.ClientDto.ClientRes;
import com.ds_JEE_M_H.Entities.Client;
import com.ds_JEE_M_H.Repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private ModelMapper mapp = new ModelMapper();
    
//Constructor : 
    @Autowired
    public ClientService (ClientRepository clientRepository) {
        super();
        this.clientRepository = clientRepository;
        }
    

   /*Crud Here  */
    
//afficher tous les client : 
    public  List<ClientRes> getAllClients(){
        List<Client> clients=clientRepository.findAll();
        List<ClientRes> reponses=new ArrayList<>();

        for (Client client:clients) {
            reponses.add(mapp.map(client,ClientRes.class));
        }

        return reponses;

    }
    
    //ajouter Un client :
    
    public  ClientRes addClient(ClientReq clientReq){
        Client client=mapp.map(clientReq,Client.class);
        Client clientBD=clientRepository.save(client);

        return mapp.map(clientBD,ClientRes.class);
    }
    
 //supprimer un client : 
    public  String deleteClient(long id){
        ClientRes clientRes=searchById(id);
        Client client=mapp.map(clientRes,Client.class);
        clientRepository.delete(client);
        return " Client supprimé! ";
    }  
//Rechercher un client :
    public ClientRes searchById(long id){
        Optional<Client> clientOpt=clientRepository.findById(id);
        Client client;
        if(clientOpt.isPresent())
            client=clientOpt.get();
        else
            throw new NoSuchElementException("client avec ("+id+") introuvable ;");

        return mapp.map(client, ClientRes.class);

    }

    
//Modifier un client : 
    public  ClientRes modifierClient(long id , ClientReq clientReq){
        //PersonEntity personRequest = mapper.map(request, PersonEntity.class);
        Client newClient=mapp.map(clientReq,Client.class);
        ClientRes clientRes=searchById(id);

        Client oldClient=mapp.map(clientRes,Client.class);

        if(newClient.getCourriel()!=null)
            oldClient.setCourriel(newClient.getCourriel());

        if (newClient.getDateNaissance() !=null)
            oldClient.setDateNaissance(newClient.getDateNaissance());

        if (newClient.getNom()!=null)
            oldClient.setNom(newClient.getNom());

        if (newClient.getPrenom()!=null)
            oldClient.setPrenom(newClient.getPrenom());

        if (newClient.getTelephone()!=null)
            oldClient.setTelephone(newClient.getTelephone());

       Client savedClient= clientRepository.save(oldClient);

        return mapp.map(savedClient,ClientRes.class);
    }


}
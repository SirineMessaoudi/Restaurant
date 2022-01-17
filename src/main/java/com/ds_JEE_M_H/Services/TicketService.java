package com.ds_JEE_M_H.Services;

import com.ds_JEE_M_H.Dto.MetDto.MetRes;
import com.ds_JEE_M_H.Dto.TableDto.TableRes;
import com.ds_JEE_M_H.Dto.TicketDto.TicketReq;
import com.ds_JEE_M_H.Dto.TicketDto.TicketRes ;

import com.ds_JEE_M_H.Entities.*;
import com.ds_JEE_M_H.Repositories.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private ModelMapper mapp = new ModelMapper();

    private ClientService clientservice;
    private TableService tableservice;
    
    private ClientRepository  clientRepository;
    private TicketRepository  ticketRepository;
    private MetRepository     metRepository;
    private TableRepository   tableRepository;
   
   //constractor
    
    @Autowired
    
    public TicketService(TicketRepository ticketRepository, ClientRepository clientRepository, MetRepository metRepository,TableRepository tableRepository) {
        super();
        this.clientservice=new ClientService(clientRepository);
        this.tableservice=new TableService(tableRepository);
        this.ticketRepository = ticketRepository;
        this.clientRepository = clientRepository;
        this.metRepository = metRepository;
        this.tableRepository =tableRepository;
        
    }
    
    /* crud here */
  //afficher tous les tickets :
    public List<Ticket> getAllTickets(){
        List<Ticket> tickets=ticketRepository.findAll();
        return  tickets;
    }
  //ajouter des tickets :
    public Ticket addTicket(Ticket ticket){
        ticket.setDate(Instant.now());
        ticket.setAddition(0);
        List<Met>  mets= ticket.getMets();
        this.tableservice.searchById(ticket.getTable().getId());
        this.clientservice.searchById(ticket.getClient().getId());
        for (Met met:mets){
          Optional<Met> opt=metRepository.findById(met.getId());
          if(opt.isPresent()){
              ticket.setAddition(ticket.getAddition()+opt.get().getPrix());
          }else {
              throw new NoSuchElementException("Met avec id :"+met.getId()+" introuvable");
          }
        }
        Ticket ticketInBase=ticketRepository.save(ticket);
        return ticketInBase;
    }
  
//supprimer une ticket : 
    public String deleteTicket(int num){
        searchById(num);
        ticketRepository.deleteById(num);
        return " Ticket supprimée! ";
    }
//modifier une ticket :
    public TicketRes modifierTicket(int num ,TicketReq ticketreq){
        Ticket newTicket=mapp.map(ticketreq,Ticket.class);
        searchById(num);
        Optional<Ticket> opt=ticketRepository.findById(num);

        Ticket oldTicket= opt.isPresent()?opt.get():null;

        //generated auto
        if (newTicket.getNumero()!=0)
              oldTicket.setNumero(newTicket.getNumero());
        if(newTicket.getAddition()!=0)
            oldTicket.setAddition(newTicket.getAddition());
        
        if (newTicket.getNbCouvert()!=0)
            oldTicket.setNbCouvert(newTicket.getNbCouvert());
      

        ticketRepository.save(oldTicket);

        return mapp.map(oldTicket,TicketRes.class);
    }
// les tables les plus réservées : 
    public TableRes mostReservedTable(){
        Map<Long,Integer> listTableWithkey=new HashMap<>();
        List<Table> tables=tableRepository.findAll();
        for(Table table:tables){
            listTableWithkey.put(table.getId(),table.getTickets().size());
        }
        Long toptable= listTableWithkey.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();

        Table table=tableRepository.findById(toptable).get();
        return mapp.map(table,TableRes.class);
       }
//Retourner le revenu par jour, semaine et mois :
       public String RevenueDerniere(){
        List<Ticket> tickets=ticketRepository.findAll();
        double revenueJours=0,revenueSemaine=0,revenuemois=0;
        for (Ticket ticket:tickets){
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(30)))){
                revenuemois=revenuemois+ticket.getAddition();
            }
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(7)))){
                revenueSemaine=revenueSemaine+ticket.getAddition();
            }
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(1)))){
                revenueJours=revenueJours+ticket.getAddition();
            }
        }

      return "Revenue moins derniere :"+revenuemois+"\n Revenue semaine derniere :"+revenueSemaine+"\n "
		+ "Revenue jour derniere :"+revenueJours;
       }

// le plat le plus acheté :
    public MetRes mostBuyedPlat(Instant begin,Instant end){
        List<Ticket> tickets=ticketRepository.findAll();
        List<Long> idList=new ArrayList<>();
        for (Ticket ticket:tickets){
            //voir si la ticket est dans cette période de temps : 
            if(ticket.getDate().isAfter(begin)&&ticket.getDate().isBefore(end)){

                for (Met met:ticket.getMets()){
                    //voir les plat qui sont dans la list des mets : 
                    if(met instanceof Plat){
                        idList.add(met.getId());
                    }
                }
            }
        }
                Long metid= idList.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        Met met=metRepository.findById(metid).get();
        return mapp.map(met,MetRes.class);
    }

    //rechercher les tickets par son id :
      public TicketRes searchById(int num){
          Optional<Ticket> ticketOpt=ticketRepository.findById(num);
          Ticket ticket;
          if(ticketOpt.isPresent())
              ticket=ticketOpt.get();
          else
              throw new NoSuchElementException("ticket avec num ("+num+") introuvable ;");

          return mapp.map(ticket,TicketRes.class);

      }
  //voir les client lees plus fideles : 
    public Client ClientplusFidel(Instant debutperiode,Instant finperiode){
        List<Ticket>tickets=ticketRepository.findAll();
        List<Ticket>ticketss=new ArrayList<>();

        for(Ticket ticket:tickets){
            if(ticket.getDate().isAfter(debutperiode)&&ticket.getDate().isBefore(finperiode)){
                ticketss.add(ticket);
            }
        }
        List<Client> cl=  ticketss.stream().map(tic->tic.getClient()).collect(Collectors.toList());

        Client fidel=cl.stream().collect(Collectors.groupingBy(l->l, Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        return fidel;
    }

	public Instant JourPlusReserveduClient(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Double RevenuePeriod(Instant begin, Instant end) {
		// TODO Auto-generated method stub
		return null;
	}





}
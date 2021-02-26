package dev.nasim.doas;

import dev.nasim.entities.Client;

import java.util.Set;

public interface ClientDAO {

    //READ
         Set<Client> getAllClient();
         Client getClientById(int id);
    //CREATE
         Client createClient(Client c);
    //UPDATE
         Client updateClient(Client c);
    //DELETE
         boolean deleteClient(int id);
    }




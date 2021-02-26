package dev.nasim.services;

import dev.nasim.entities.Client;

import java.util.Set;

public interface ClientService {


    Client registerClient(Client c);

    Set<Client> getAllClient();

    Client getClientById(int id);

    Client updatClient(Client c);

    boolean deleteClientById(int id);
}

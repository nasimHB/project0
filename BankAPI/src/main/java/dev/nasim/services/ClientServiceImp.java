package dev.nasim.services;

import dev.nasim.doas.ClientDAO;
import dev.nasim.entities.Client;
import org.apache.log4j.Logger;

import java.util.Set;

public class ClientServiceImp implements ClientService{

    private static Logger logger = Logger.getLogger(ClientServiceImp.class.getName());

    private ClientDAO cdao;

    public ClientServiceImp(ClientDAO clientDAO){
        this.cdao = clientDAO;
    }

    @Override
    public Client registerClient(Client c) {
        this.cdao.createClient(c);
        return c;
    }

    @Override
    public Set<Client> getAllClient() {
        return this.cdao.getAllClient();
    }

    @Override
    public Client getClientById(int id) {
        return this.cdao.getClientById(id);
    }

    @Override
    public Client updatClient(Client c) {
        this.cdao.updateClient(c);
        return c;
    }

    @Override
    public boolean deleteClientById(int id) {
        return this.cdao.deleteClient(id);
    }
}

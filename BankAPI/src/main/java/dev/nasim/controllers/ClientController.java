package dev.nasim.controllers;

import com.google.gson.Gson;
import dev.nasim.doas.ClientDaoPostgres;
import dev.nasim.entities.Client;
import dev.nasim.services.ClientService;
import dev.nasim.services.ClientServiceImp;
import io.javalin.http.Handler;

import java.util.Set;

public class ClientController {

    private ClientService clientService = new ClientServiceImp(new ClientDaoPostgres());


    public Handler getAllClientHandler = (ctx) ->{

            Set<Client> allClient = this.clientService.getAllClient();
            Gson gson = new Gson();
            String clientsJSON = gson.toJson(allClient);
            ctx.result(clientsJSON);
            ctx.status(200);

    };

    // GET /client/4
    public Handler getClientByIdHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        Client client = this.clientService.getClientById(id);
        if(client == null){
            ctx.result("Client not found");
            ctx.status(404);
        }else{
            Gson gson = new Gson();
            String clientJSON = gson.toJson(client);
            ctx.result(clientJSON);
            ctx.status(200);
        }

    };

    // POST /client
    public Handler createClientHandler = (ctx) ->{
        String body = ctx.body();
        Gson gson = new Gson();
        Client client = gson.fromJson(body,Client.class);
        this.clientService.registerClient(client);
        String json = gson.toJson(client);
        ctx.result(json);
        ctx.status(201);
    };

    // PUT /client
    public Handler updateClientHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        String body = ctx.body();
        Gson gson = new Gson();
        Client client = gson.fromJson(body,Client.class);
        client.setClient_id(id);
        this.clientService.updatClient(client);
    };
    //DELETE /client
    public Handler deleteClientHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = this.clientService.deleteClientById(id);
        if(deleted){
            ctx.result("Client was deleted");
        }else{
            ctx.result("Oh no could not delete");
        }
    };
}


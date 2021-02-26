package dev.nasim.controllers;

import com.google.gson.Gson;
import dev.nasim.doas.AccountDaoPostgres;
import dev.nasim.doas.ClientDaoPostgres;
import dev.nasim.entities.Account;
import dev.nasim.services.AccountService;
import dev.nasim.services.AccountServiceImp;
import dev.nasim.services.ClientService;
import dev.nasim.services.ClientServiceImp;

import dev.nasim.entities.Client;

import io.javalin.http.Handler;

import java.util.Set;


public class AccountController {

    private AccountService accountServices = new AccountServiceImp(new AccountDaoPostgres());

    private ClientService clientServices = new ClientServiceImp(new ClientDaoPostgres());

    public Handler getAllAccountsByClientHandler = ctx -> {

        String balanceHigher = ctx.queryParam("amountLessThan", "NONE");
        String balanceLower = ctx.queryParam("amountGreaterThan", "NONE");

        int id = Integer.parseInt((ctx.pathParam("cid")));
        Set<Account> allAccountsByClient = this.accountServices.getAllAccountsByClientId(id);
        System.out.println(allAccountsByClient);
        Client client = this.clientServices.getClientById(id);
        if (balanceLower.equals("NONE") && balanceHigher.equals("NONE")) {

            if (client == null) {
                ctx.result("Client not found");
                ctx.status(404);
            } else {
                Gson gson = new Gson();
                String accountsJSON = gson.toJson(allAccountsByClient);
                ctx.result(accountsJSON);
                ctx.status(200);
            }
        } else {
            int higherBalance = Integer.parseInt(ctx.queryParam("amountLessThan"));
            System.out.println(ctx.queryParam("amountGreaterThan"));

            int lowerBalance = Integer.parseInt(ctx.queryParam("amountGreaterThan"));
            Set<Account> accounts = this.accountServices.getAccountsByBalance(id, lowerBalance, higherBalance);
            if (client == null) {
                ctx.result("Client not found");
                ctx.status(404);
            } else {
                Gson gson = new Gson();
                String selectedAccountsJSON = gson.toJson(accounts);
                ctx.result(selectedAccountsJSON);
                ctx.status(200);
            }


        }

    };

    public Handler getAccountByIdHandler = ctx -> {

        int clientId = Integer.parseInt(ctx.pathParam("cid"));
        int id = Integer.parseInt(ctx.pathParam("aid"));
        Account account = this.accountServices.getAccountById(id,clientId);

        if(account == null){
            ctx.result("Account not found");
            ctx.status(404);
        }else{
            Gson gson = new Gson();
            String accountJson = gson.toJson(account);
            ctx.result(accountJson);
        }

    };



    public Handler createAccountHandler = ctx -> {

        int clientId = Integer.parseInt(ctx.pathParam("cid"));
        String body = ctx.body();
        Gson gson = new Gson();

        Account account = gson.fromJson(body,Account.class);

        this.accountServices.createAccount(clientId,account);

        ctx.result("You created a new account for your client!");
        ctx.status(201);

    };

    public Handler updateAccountHandler = ctx -> {

        int clientId = Integer.parseInt(ctx.pathParam("cid"));
        int id = Integer.parseInt(ctx.pathParam("aid"));

        Account account = this.accountServices.getAccountById(id,clientId);



        if (account == null){
            ctx.result("Account not found");
            ctx.status(404);

        }else{
            String body = ctx.body();
            Gson gson = new Gson();
            Account account1 = gson.fromJson(body,Account.class);
            account1.setAccount_id(id);
            this.accountServices.updateAccountById(clientId,account1);
            //String json = gson.toJson(account1);
            //ctx.result(json);

        }

    };


    public Handler deleteAccountHandler = ctx -> {
        int clientId = Integer.parseInt(ctx.pathParam("cid"));
        int id = Integer.parseInt(ctx.pathParam("aid"));

      /*  Account account = this.accountServices.getAccountById(id,clientId);

        if(account == null){
            ctx.result("Account not found, unable to delete");
            ctx.status(404);
        }else {*/
            boolean result = this.accountServices.deleteAccountById(id,clientId);
            if (result ==true) {
                ctx.result("Client was successfully deleted.");
            }else{
                ctx.result("Unable to delete client successfully.");

            }




    };


}




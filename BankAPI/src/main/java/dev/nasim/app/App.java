package dev.nasim.app;

import dev.nasim.controllers.AccountController;
import dev.nasim.controllers.ClientController;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {

        Javalin app = Javalin.create();

        ClientController clientController = new ClientController();
        AccountController accountController= new AccountController();

        // GET /client
        app.get("/clients",clientController.getAllClientHandler);

        // GET /client/id
        app.get("/clients/:id",clientController.getClientByIdHandler);

        // POST /client
        app.post("/clients",clientController.createClientHandler);

        // PUT /client/id
        app.put("/clients/:id", clientController.updateClientHandler);

        // DELETE /client/id
        app.delete("/clients/:id", clientController.deleteClientHandler);

        ///

        // POST /clients/5/accounts =>creates a new account for client with the id of 5

        app.post("/clients/:cid/accounts", accountController.createAccountHandler);

        // GET /clients/7/accounts => get all accounts for client 7

        app.get("/clients/:cid/accounts",accountController.getAllAccountsByClientHandler);

        // GET /clients/7/accounts?amountLessThan=2000&amountGreaterThan400 => get all accounts for client 7 between 400 and 2000
       app.get("/clients/:cid/accounts?amountLessThan&amountGreaterThan",accountController.getAllAccountsByClientHandler);

        // GET /clients/9/accounts/4 => get account 4 for client 9

        app.get("/clients/:cid/accounts/:aid", accountController.getAccountByIdHandler);

        // PUT /clients/10/accounts/3 => update account  with the id 3 for client 10

        app.put("/clients/:cid/accounts/:aid",accountController.updateAccountHandler);

        // DELETE /clients/15/accounts/6 => delete account 6 for client 15

        app.delete("/clients/:cid/accounts/:aid",accountController.deleteAccountHandler);

        app.start();
        // starts web server
    }
}



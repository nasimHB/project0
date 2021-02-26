package dev.nasim.doas;

import dev.nasim.entities.Account;
import dev.nasim.entities.Client;

import java.util.Set;

public interface AccountDAO {

    //READ
    Set<Account> getAllAccountsByClientId(int id)  ;
    Account getAccountById(int aid,int cid );
    //CREATE
    Account createAccount(int id,Account a);
    //UPDATE
    Account updateAccount(int id,Account a);
    //DELETE
    boolean deleteAccount(int aid,int cid );
}



package dev.nasim.services;

import dev.nasim.entities.Account;
import dev.nasim.entities.Client;

import java.util.Set;

public interface AccountService {

    Account createAccount(int id,Account a);

    Set<Account>getAccountsByBalance(int id, int bal1, int bal2);

    Account getAccountById(int aid, int cid);

    Set<Account> getAllAccountsByClientId(int id);

    Account updateAccountById(int id,Account a);

    boolean deleteAccountById(int aid,int cid);
}


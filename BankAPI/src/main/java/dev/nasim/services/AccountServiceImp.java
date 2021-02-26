package dev.nasim.services;

import dev.nasim.doas.AccountDAO;
import dev.nasim.doas.ClientDAO;
import dev.nasim.entities.Account;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class AccountServiceImp implements AccountService {


    private static Logger logger = Logger.getLogger(ClientServiceImp.class.getName());

    private AccountDAO adao;

    public AccountServiceImp(AccountDAO accountDAO){
        this.adao = accountDAO;
    }
    @Override
    public Account createAccount(int id, Account a) {

        return this.adao.createAccount(id,a);
    }

    @Override
    public Set<Account> getAccountsByBalance(int id, int bal1, int bal2) {
        Set<Account> allAccounts = this.getAllAccountsByClientId(id);
        Set<Account> selectedAccounts = new HashSet<Account>();

        for(Account a : allAccounts){

            if(bal1 < a.getBalance() && a.getBalance() < bal2){
                selectedAccounts.add(a);

            }
        }
        return selectedAccounts;

    }

    @Override
    public Account getAccountById(int aid, int cid) {
        return this.adao.getAccountById(aid,cid);
    }

    @Override
    public Set<Account> getAllAccountsByClientId(int id) {
        return this.adao.getAllAccountsByClientId(id);
    }

    @Override
    public Account updateAccountById(int id, Account a) {
        return this.adao.updateAccount(id,a);
    }

    @Override
    public boolean deleteAccountById(int aid, int cid) {
        return this.adao.deleteAccount(aid,cid);
    }
}

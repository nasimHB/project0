package dev.nasim.daotest;


import dev.nasim.doas.AccountDAO;
import dev.nasim.doas.AccountDaoPostgres;
import dev.nasim.doas.ClientDAO;
import dev.nasim.doas.ClientDaoPostgres;
import dev.nasim.entities.Account;
import dev.nasim.entities.Client;
import org.junit.jupiter.api.*;

import java.util.Set;
import java.util.logging.Logger;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountDaoTest {

    private static final AccountDAO adao = new AccountDaoPostgres();
    private static final ClientDAO cdao = new ClientDaoPostgres();
    private static final Account testaccount = null;

    @Test
    @Order(1)
    void create_account() {

        Account a1= new Account("cheeking", 200, 2);
        adao.createAccount(2,a1);
        System.out.println(a1);

        Assertions.assertNotEquals(4, a1.getAccount_id());
    }

    @Test
    @Order(2)
    void get_all_accounts(){

        Account a1 = new Account( 0,"cheeking",200,20);
        Account a2 = new Account( 0,"saving",400,20);
        Account a3 = new Account( 0,"cheeking",600,20);

        adao.createAccount(20,a1);
        adao.createAccount(20,a2);
        adao.createAccount(20,a3);

        Set<Account> allaccount = adao.getAllAccountsByClientId(20);
        Assertions.assertTrue(allaccount.size()>2);
    }
}


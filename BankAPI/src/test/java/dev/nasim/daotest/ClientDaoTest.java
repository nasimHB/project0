package dev.nasim.daotest;
import dev.nasim.doas.ClientDAO;
import dev.nasim.doas.ClientDaoPostgres;
import dev.nasim.entities.Client;
import org.junit.jupiter.api.*;

import java.util.Set;
import java.util.logging.Logger;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientDaoTest {

    private static ClientDAO cdao = new ClientDaoPostgres();
    private static Client testClient = null;

    @Test
    @Order(1)
    void create_client() {

        Client nasim = new Client("nasimh", "heybay", "nasim", "789");
        cdao.createClient(nasim);
        System.out.println(nasim);
        testClient = nasim;
        Assertions.assertNotEquals(4, nasim.getClient_id());
    }


    @Test
    @Order(2)
    void get_client_by_id(){
        int id = testClient.getClient_id();
        Client client = cdao.getClientById(id);
        Assertions.assertEquals(testClient.getUsername(), client.getUsername());
    }




    @Test
    @Order(3)
    void update_client(){
        Client nasim = new Client("nasimh", "heybay", "nasim", "789");
        nasim.setFirstname("nasimhb");
        cdao.updateClient(nasim);
        Assertions.assertEquals("nasimhb",nasim.getFirstname());

    }

   @Test
    @Order(4)
    void get_all_clients(){

        Client c1 = new Client(0, "tom", "robert","tm","t123");
       Client c2 = new Client(0, "sam", "robert","sm","s123");
       Client c3 = new Client(0, "jak", "robert","jm","j123");

        cdao.createClient(c1);
       cdao.createClient(c2);
       cdao.createClient(c3);

        Set<Client> allclient = cdao.getAllClient();
        Assertions.assertTrue(allclient.size()>2);
    }

    @Test
    @Order(5)
    void delete_client_by_id(){
        Client c1 = new Client(0, "tom", "robert","tm","t123");
        int id = c1.getClient_id();
        boolean result = cdao.deleteClient(id);
        Assertions.assertTrue(result);
    }
}
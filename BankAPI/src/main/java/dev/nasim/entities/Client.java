package dev.nasim.entities;

public class Client {

    private int client_id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;

    //Constructors
    public Client() {
        super();
    }
    public Client(int client_id, String firstname, String lastname, String username, String password) {
        super();
        this.client_id = client_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }
    public Client(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public Client( String firstname, String lastname, String username, String password) {
        super();

        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    //Setters and Getters
    public int getClient_id() {
        return client_id;
    }
    public void setClient_id(int client_id ){
        this.client_id = client_id;
    }
    public String getFirstname() {

        return firstname;
    }
    public void setFirstname(String firstname) {

        this.firstname = firstname;
    }
    public String getLastname() {

        return lastname;
    }
    public void setLastname(String lastname) {

        this.lastname = lastname;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + client_id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

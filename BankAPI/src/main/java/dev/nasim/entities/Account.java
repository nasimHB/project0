package dev.nasim.entities;

public class Account {


    private int account_id;
    String typ;
    private int balance;
    private int c_id;


    //Constructors
    public Account() {
        super();
    }
    public Account(int account_id,String typ, int balance, int c_id) {
        super();
        this.account_id = account_id;
        this.typ = typ;
        this.balance = balance;
        this.c_id = c_id;
    }
    public Account(int c_id) {
        this.c_id = c_id;
    }

    public Account(String typ, int balance, int c_id) {
        this.typ = typ;
        this.balance = balance;
        this.c_id = c_id;
    }

    //Setters and Getters
    public int getAccount_id() {

        return account_id;
    }
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String type) {
        this.typ = type;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getC_id() {

        return c_id;
    }
    public void setC_id(int c_id) {

        this.c_id = c_id;
    }

}

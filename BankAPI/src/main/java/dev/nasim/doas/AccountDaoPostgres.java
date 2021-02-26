package dev.nasim.doas;

import dev.nasim.entities.Account;
import dev.nasim.entities.Client;
import dev.nasim.util.ConnectionUtil;
import org.jetbrains.annotations.NotNull;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AccountDaoPostgres implements AccountDAO{


    private Logger logger = Logger.getLogger(ClientDaoPostgres.class.getName());

    private static Map<Integer, Account> accountTable = new HashMap<Integer, Account>();
    @Override
    public Set<Account> getAllAccountsByClientId(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from account where c_id= ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            Set<Account> accounts= new HashSet<Account>();

            while (rs.next()){
                Account a = new Account();

                a.setAccount_id(rs.getInt("account_id"));
                a.setTyp(rs.getString("typ"));
                a.setBalance(rs.getInt("balance"));
                a.setC_id(rs.getInt("c_id"));
                accounts.add(a);
            }
            return  accounts;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to get all accounts",sqlException);
            return null;
        }
    }


    @Override
    public Account getAccountById(int aid,int cid) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from account where account_id = ? and c_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,aid);
            ps.setInt(2,cid);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Account a = new Account();
            a.setAccount_id(rs.getInt("account_id"));
            a.setTyp(rs.getString("typ"));
            a.setBalance(rs.getInt("balance"));
            a.setC_id(rs.getInt("c_id"));

            return  a;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to get account with accountid " + aid+"and clientid"+cid, sqlException);

            return null;
        }

    }

      @Override
      public Account createAccount(int id, Account a) {
          try(Connection conn = ConnectionUtil.createConnection()){
              String sql = "insert into account (typ,balance,c_id) values (?,?,?)";
              PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
              ps.setString(1, a.getTyp());
              ps.setInt(2,a.getBalance());
              ps.setInt(3,id);

              ps.execute(); // execute the sql statement

              ResultSet rs = ps.getGeneratedKeys();
              rs.next();
              int key = rs.getInt("account_id");
              a.setAccount_id(key);
              return  a;

          }catch (SQLException sqlException){
              sqlException.printStackTrace();
              logger.error("unable to create Account",sqlException);
              return null;
          }
      }

    @Override
    public Account updateAccount(int id,Account a) {
        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "update account set typ = ?, balance = ? where account_id = ? and  c_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,a.getTyp());
            ps.setInt(2, a.getBalance());
            ps.setInt(3, a.getAccount_id());
            ps.setInt(4, id);
            ps.executeUpdate();

            return  a;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to update Account" +a.getAccount_id()+"for Client" + id,sqlException);
            return null;
        }

    }

    @Override
    public boolean deleteAccount(int aid,int cid) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from account where account_id=? and  c_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,aid);
            ps.setInt(2,cid);
            ps.execute();
            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to delete Account  with id " + cid +"for client"+cid ,sqlException);
            return false;
        }

    }
    }


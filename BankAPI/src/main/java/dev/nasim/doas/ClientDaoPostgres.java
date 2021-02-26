package dev.nasim.doas;

import dev.nasim.entities.Client;
import dev.nasim.util.ConnectionUtil;
import org.apache.log4j.Logger;
import java.util.HashSet;

import java.sql.*;
import java.util.Set;


public class ClientDaoPostgres implements ClientDAO {

    private Logger logger = Logger.getLogger(ClientDaoPostgres.class.getName());

    @Override
    public Set<Client> getAllClient() {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from client";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Set<Client> clients= new HashSet<Client>();

            while (rs.next()){
                Client c = new Client();
                c.setClient_id(rs.getInt("client_id"));
                c.setFirstname(rs.getString("firstname"));
                c.setLastname(rs.getString("lastname"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                clients.add(c);
            }
            return  clients;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to get all clients",sqlException);
            return null;
        }
    }

    @Override
    public Client getClientById(int id) {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from client where client_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();  // move cursor to first actual book;

            Client c = new Client();
            c.setClient_id(rs.getInt("client_id"));
            c.setFirstname(rs.getString("firstname"));
            c.setLastname(rs.getString("lastname"));
            c.setUsername(rs.getString("username"));
            c.setPassword(rs.getString("password"));

            return c;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to get client with id " + id, sqlException);
            return null;
        }

    }


    @Override
    public Client createClient(Client c) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into client (firstname,lastname,username,password) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getFirstname());
            ps.setString(2,c.getLastname());
            ps.setString(3,c.getUsername());
            ps.setString(4,c.getPassword());
            ps.execute(); // execute the sql statement

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("client_id");
            c.setClient_id(key);
            return  c;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
          logger.error("unable to create client",sqlException);
            return null;
        }

    }


    @Override
    public Client updateClient(Client c) {

        try(Connection conn = ConnectionUtil.createConnection()){
            // sql using nice prepared statements

            String sql = "update client set firstname = ?, lastname = ?, username = ?, password= ? where client_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,c.getFirstname());
            ps.setString(2,c.getLastname());
            ps.setString(3,c.getUsername());
            ps.setString(4,c.getPassword());
            ps.setInt(5, c.getClient_id());
            ps.executeUpdate();
            return c;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to update client" + c.getClient_id(),sqlException);
            return null;
        }


    }

    @Override
    public boolean deleteClient(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from client where client_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to delete book with id " + id,sqlException);
            return false;
        }

    }
    }


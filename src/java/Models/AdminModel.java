/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DBConnection.DBC;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author abdo
 */
public class AdminModel {

    public AdminModel() {
    }
    
    public HashMap<String, String> login(String email, String password){
        
        HashMap<String, String> user = new HashMap<>();
        
        Connection con = DBC.getActiveConnection();
        String query = "select * from admin where `email` = ? and `password` = ?;";
        try {
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, email);
            p.setString(2, password);
            
            ResultSet row = p.executeQuery();
            if (row.next()){
                user.put("userId", String.valueOf(row.getInt("id")));
                user.put("name", row.getString("name"));
                user.put("email", row.getString("email"));
                user.put("password", row.getString("password"));
                return user;
            }
            row.close();
            p.close();
            DBC.closeConnection();
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        DBC.closeConnection();
        return null;
    }
    public HashMap<String, String> signUp(String name, String email, String password){
        Connection con = DBC.getActiveConnection();
        HashMap<String, String> user = new HashMap<>();
        String query = "insert into admin(name, email, password) values(?, ?, ?);";
        String lastId = "SELECT LAST_INSERT_ID()";
        try {

            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, name);
            p.setString(2, email);
            p.setString(3, password);
            p.executeUpdate();
            p = con.prepareStatement(lastId);
            ResultSet res = p.executeQuery();
            res.next();
            user.put("userId", String.valueOf(res.getInt(1)));
            user.put("name", name);
            user.put("email", email);
            user.put("password", password);
            p.close();
            DBC.closeConnection();
            return user;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        DBC.closeConnection();
        return null;
        
    }
    
    public HashMap<String, String> showSettings(int id){

        HashMap<String,String> user = new HashMap<>();
        String name="", email="", password="";        
        
        Connection con = DBC.getActiveConnection();
        String query="Select * from admin where id=?";
        try {
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(1, id);
            
            ResultSet row = p.executeQuery();
            

            if(row.next()){
                name = row.getString("name");
                email = row.getString("email");
                password = row.getString("password");
            }
            

            if(email != null){
                user.put("name", name);
                user.put("email", email);
                user.put("id", String.valueOf(id));
                user.put("password", password);
            }else{
                user.put("Message","Admin not found");
            }
            DBC.closeConnection();
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBC.closeConnection();
        return null;
    }
    
    public boolean updateSettings(int id, String name, String email, String password, String newPassword){

        HashMap<String, String> user = new AdminModel().showSettings(id);
        if(!user.get("password").equals(password)){
           return false; 
        }
        Connection con = DBC.getActiveConnection();

        String query="update admin set name= ? , email = ? , password = ? where id = ? ;";
        try {
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(4, id);
            p.setString(1, name);
            p.setString(2, email);
            p.setString(3, newPassword);
            p.executeUpdate();
            DBC.closeConnection();
        
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBC.closeConnection();
        return false;
    }
    
    public HashMap<String, HashMap<String, String> > getLateUsers(int movieId){
        
        java.util.Date utilDate = new java.util.Date();
        Date todayDate = new Date(utilDate.getTime());
        System.out.println(todayDate);
        HashMap<String, String> lateUsers = new HashMap<>();
        
        Connection con = DBC.getActiveConnection();
        
        String query = "select * from movie_user_rent where endDate < ? and idMovie = ?;";
        
        try {
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setDate(1, todayDate);
            p.setInt(2, movieId);
            System.out.println(p);
            ResultSet res = p.executeQuery();
            
            ArrayList<Integer> users = new ArrayList<>();
            ArrayList<Integer> filtered_user = new ArrayList<>();
            //get late users ids for this movie
            while(res.next()){
                users.add(res.getInt("idUser"));
            }
            System.out.println(users.size());
            query = "select * from late_users where idUser = ? and idMovie = ?;";
            for(int i=0 ; i < users.size(); i++){
                p = (PreparedStatement) con.prepareStatement(query);
                p.setInt(1, users.get(i));
                p.setInt(2, movieId);
                res = p.executeQuery();
                if(!(res.next())){
                    filtered_user.add(users.get(i));
                }
            }
            
            HashMap<String, HashMap<String, String> > not_ack_users = new HashMap<>();
            UserModel user_model = new UserModel();
            for(int i=0 ; i < filtered_user.size(); i++){
                not_ack_users.put(String.valueOf(i), user_model.showSettings(filtered_user.get(i)));
            }
            
            
            DBC.closeConnection();
        
            return not_ack_users;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBC.closeConnection();
        
        
        return null;
    }
    
    public void sendMail(int userId, int movieId, int adminId){
        
        UserModel user_model = new UserModel();
        HashMap<String, String> user = user_model.showSettings(userId);
        MovieModel movie_model = new MovieModel();
        HashMap<String, String> movie = movie_model.showMovie(movieId, -1);
        AdminModel admin_model = new AdminModel();
        HashMap<String, String> admin = admin_model.showSettings(adminId);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); /// set autintication = true
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); /// this is STMP server address
        props.put("mail.smtp.port", "587"); /// port for STMP server 

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication("fake2wy2wy@gmail.com","fake12321");
                        }
                });

        try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("fake2wy2wy@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(user.get("email")));
                message.setSubject("Late for "+movie.get("name"));
                message.setText("Dear "+user.get("name")+",\n you are late for paying this movie.\nThanks,\n"+admin.get("name")+"\n"+"\n");

                Transport.send(message);

        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }

        Connection con = DBC.getActiveConnection();
        String query = "insert into late_users (idUser,idAdmin,idMovie)values(?,?,?);";
        try {
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(1, userId);
            p.setInt(2, adminId);
            p.setInt(3, movieId);
            p.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

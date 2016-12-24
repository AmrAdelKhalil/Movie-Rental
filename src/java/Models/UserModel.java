package Models;

import DBConnection.DBC;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserModel {
    
    public UserModel(){
        
    }
    
    public HashMap<String, String> login(String email, String password){
        
        HashMap<String, String> user = new HashMap<>();
        
        Connection con = DBC.getActiveConnection();
        String query = "select * from user where `email` = ? and `password` = ?;";
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
                user.put("credit", row.getString("creditCard"));
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
    
    public boolean signUp(String name, String email, String password, String creditCard){
        Connection con = DBC.getActiveConnection();
        String query = "insert into user(name, email, password, creditCard) values(?, ?, ?, ?);";
        try {

            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, name);
            p.setString(2, email);
            p.setString(3, password);
            p.setString(4, creditCard);
            p.executeUpdate();
            p.close();
            DBC.closeConnection();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        DBC.closeConnection();
        return false;
        
    }
    
    public HashMap<String, String> showSettings(int id){
        
        HashMap<String,String> user = new HashMap<>();
        String name="", email="", creditCard="", password="";        
        
        Connection con = DBC.getActiveConnection();
        String query="Select * from user where id=?";
        try {
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(1, id);
            
            ResultSet row = p.executeQuery();
            

            if(row.next()){
                name = row.getString("name");
                email = row.getString("email");
                creditCard = row.getString("creditCard");
                password = row.getString("password");
            }
            

            if(email != null){
                user.put("name", name);
                user.put("email", email);
                user.put("creditCard", creditCard);
                user.put("id", String.valueOf(id));
                user.put("password", password);
            }else{
                user.put("Message","User not found");
            }
            DBC.closeConnection();
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBC.closeConnection();
        return null;
    }
    
    public boolean updateSettings(int id, String name, String email, String password, String creditCard, String newPassword){
        
        HashMap<String, String> user = new UserModel().showSettings(id);
        if(!user.get("password").equals(password)){
           return false; 
        }
        Connection con = DBC.getActiveConnection();
        String query="update user set name= ? , email = ? , password = ? , creditCard = ? where id = ? ;";
        try {
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(5, id);
            p.setString(1, name);
            p.setString(2, email);
            p.setString(3, newPassword);
            p.setString(4, creditCard);
            p.executeUpdate();
            DBC.closeConnection();
        
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBC.closeConnection();
        return false;
    }
    
    public boolean rentMovie(int userId, int movieId, Date startDate, Date endDate, float totalPrice){
        Connection con = DBC.getActiveConnection();
        String query="insert into movie_user_rent (idUser, idMovie, total_price, startDate, endDate) values (?, ?, ?, ?, ?);";
        try {
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(1, userId);
            p.setInt(2, movieId);
            p.setFloat(3, totalPrice);
            p.setDate(4, startDate);
            p.setDate(5, endDate);
            
            p.executeUpdate();
            DBC.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBC.closeConnection();
        return false;
    }
    
    public boolean extendRentingMovie(int userId, int movieId, int extendedPeriod, float extendedPrice) throws SQLException{
        try {
                float oldPrice = 0;
                Date endDate;
                Connection con = DBC.getActiveConnection();
                String query="Select * from movie_user_rent where idUser=? and idMovie=?";
            
                PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
                p.setInt(1, userId);
                p.setInt(2, movieId);
            
                ResultSet row = p.executeQuery();
            
                if(row.next()){
                    endDate = row.getDate("endDate");
                    oldPrice = row.getFloat("total_price");
                    
                    java.util.Date utilDate = endDate;
                    Calendar c = Calendar.getInstance(); 
                    c.setTime(utilDate); 
                    c.add(Calendar.DATE, extendedPeriod);
                    utilDate = c.getTime();
                    endDate = new Date(utilDate.getTime());
                    
                    query="update movie_user_rent set endDate = ? , total_price = ? where idUser = ? and idMovie = ? ;";
                    p = (PreparedStatement) con.prepareStatement(query);
                    
                    p.setDate(1, endDate);
                    p.setFloat(2, oldPrice+extendedPrice);
                    p.setInt(3, userId);
                    p.setInt(4, movieId);
                    
                    p.executeUpdate();
                    DBC.closeConnection();
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
            }

            DBC.closeConnection();
            return false;
    }
}

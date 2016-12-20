package Models;

import DBConnection.DBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserModel {
    
    public UserModel(){
        
    }
    
    public HashMap<String, String> showSettings(int id){
        
        HashMap<String,String> user = new HashMap<>();
        String name="", email="", creditCard="";        
        
        Connection con = DBC.getActiveConnection();
        String query="Select * from User where id=?";
        try {
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(1, id);
            
            ResultSet row = p.executeQuery();
            
            if(row.next()){
                name = row.getString("name");
                email = row.getString("email");
                creditCard = row.getString("creditCard");
            }
            
            if(email != null){
                user.put("name", name);
                user.put("email", email);
                user.put("creditCard", creditCard);
            }else{
                user.put("Message","User not found");
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBC.closeConnection();
        return null;
    }
    
    public boolean updateSettings(int id, String name, String email, String password, String creditCard){
        
        Connection con = DBC.getActiveConnection();
        String query="update User set name= ? , email = ? , password = ? , creditCard = ? where id = ? ;";
        try {
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(5, id);
            p.setString(1, name);
            p.setString(2, email);
            p.setString(3, password);
            p.setString(4, creditCard);
            p.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBC.closeConnection();
        return false;
    }
    
    public boolean rentMovie(int userId, int movieId, float rentPeriod, float totalPrice){
        Connection con = DBC.getActiveConnection();
        String query="insert into movie_user_rent (idUser, idMovie, rent_period, total_price) values (?, ?, ?, ?);";
        try {
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(1, userId);
            p.setInt(2, movieId);
            p.setFloat(3, rentPeriod);
            p.setFloat(4, totalPrice);
            p.executeUpdate();
            DBC.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBC.closeConnection();
        return false;
    }
    
    public boolean extendRentingMovie(int userId, int movieId, float extendedPeriod, float extendedPrice){
        try {
            
            float oldPeriod = 0 , oldPrice = 0;
            Connection con = DBC.getActiveConnection();
            String query="Select * from movie_user_rent where idUser=? and idMovie=?";
            
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(1, userId);
            p.setInt(2, movieId);
            
            ResultSet row = p.executeQuery();
            
            if(row.next()){
                oldPeriod = row.getFloat("rent_period");
                oldPrice = row.getFloat("total_price");
                
                query="update movie_user_rent set rent_period= ? , total_price = ? where idUser = ? and idMovie = ? ;";
                p = (PreparedStatement) con.prepareStatement(query);
                p.setFloat(1, oldPeriod+extendedPeriod);
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

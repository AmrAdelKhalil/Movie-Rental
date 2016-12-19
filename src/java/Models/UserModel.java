package Models;

import DBConnection.DBC;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
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
            
            while(row.next()){
                name = row.getString("name");
                email = row.getString("email");
                creditCard = row.getString("creditCard");
            }
            
            user.put("name", name);
            user.put("email", email);
            user.put("creditCard", creditCard);
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
}

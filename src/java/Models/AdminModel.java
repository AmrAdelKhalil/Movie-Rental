/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DBConnection.DBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public boolean signUp(String name, String email, String password){
        Connection con = DBC.getActiveConnection();
        String query = "insert into admin(name, email, password) values(?, ?, ?);";
        try {

            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, name);
            p.setString(2, email);
            p.setString(3, password);
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
    
    
    
}

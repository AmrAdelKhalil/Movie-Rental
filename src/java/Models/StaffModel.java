package Models;

import DBConnection.DBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaffModel {
    
    public StaffModel(){
        
    }
    
    public HashMap<String, String> getStaff(int idMovie){
        
        HashMap<String, String> staff_members = new HashMap<>();
        
        Connection con = DBC.getActiveConnection();
        String query="select name, role from staff_member INNER JOIN movie_staff ON staff_member.id = movie_staff.idStaff WHERE movie_staff.idMovie = ?;";
        
        try {
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(1, idMovie);
            
            ResultSet result = p.executeQuery();
            
            while(result.next()){
                staff_members.put(result.getString("name"), result.getString("role"));
            }
            DBC.closeConnection();
        
            return staff_members;
            
        } catch (SQLException ex) {
            Logger.getLogger(StaffModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        DBC.closeConnection();

                
        return null;
    }
}
